# WASM UDFs for Spark

This repo serves as an experiment of pairing [Extism](https://extism.org) with Apache Spark. There are a number of legacy data applications out there in the world with their business logic written in a any number of languages that aren't Python, Scala or R. I thought it'd be interesting to see if I could get some other languages (namely the ones supported by Extism) to work as UDF code. This is definitely an experiment in progress with lots of room for improvement. 

## Why Extism

Extism is a framework that I ran across that has been a lot of fun to play around with. Essentially it's a framework for creating your own Plugin architecture to run WASM plugins in a number of host languages/runtimes. You could write a UDF in C# and use the same resulting WASM code interchangeably in both a Spark pipeline as well as a DataFusion pipeline. 

## This Repo

This repo is divided up into several sub-projects:

* `csharp_wasm_udf`, `js_wasm_udf`, `rust_wasm_udf`, etc are the sources for the various Plugins in their respective languages. Currently missing the implementations for Assemblyscript, C, F#, Go and Typescript. 
* `wasm_spark` is the Spark application that actually runs the plugins

## Running the experiment

Everything is cobbled together using `Makefile`s. Assuming all of the assets are built and pre-requisites installed you should be able to use one of the prebuilt `run_with...` scripts to run one of the samples. For example:

```shell
> ./run_with_rust.sh
24/06/03 11:49:04 INFO SparkContext: Running Spark version 3.5.0
...
24/06/03 11:49:05 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, yggdrasil.lan, 61477, None)
CHICORY_WASM_UDF: Loading WASM file ./rust_wasm_udf.wasm
+---+--------+
| id|wasm_udf|
+---+--------+
|  0|       2|
|  1|       3|
|  2|       4|
|  3|       5|
|  4|       6|
|  5|       7|
|  6|       8|
|  7|       9|
|  8|      10|
|  9|      11|
| 10|      12|
| 11|      13|
| 12|      14|
| 13|      15|
| 14|      16|
| 15|      17|
| 16|      18|
| 17|      19|
| 18|      20|
| 19|      21|
+---+--------+
only showing top 20 rows

Final result
1 Runs of 100: 5095ms/5095ms/5095ms/20.0 (Min/Max/Avg/Avg Rows per second)
```

The extra options supported are:

* `--run-count` : How many times to run the experiment
* `--row-count` : How many rows to run through the UDF

## Runtime Engine

This repo is set up to use both the native Extism runtime and the (still under-development) Chicory runtime. Chicory is a [JVM-native WASM runtime](https://github.com/dylibso/chicory) made by the same folks who are actively developing Extism. The advantage of having a Java-native runtime when running a Spark project are pretty obvious, but I set up this repo to use both so that they could be compared side-by-side. There is a drawback, however. As of the time of writing, WASI support isn't complete in the Chicory runtime, and as such some of the examples (C# and JavaScript) can't yet run in it. The `run_with_csharp.sh` and `run_with_js.sh` scripts already include the `--no-chicory` flag to disable this Runtime and use the native Extism one instead.

## Writing a plugin UDF

Writing a plugin UDF was easy enough, and for this experiment I kept the UDFs to the trivial task of adding two columns and returning the value. The part that takes the most code (and the most CPU time) is the serialization/deserialization, which is currently implemented using Protocol Buffers. In a future version I'll replace this with Apache Arrow, much like Spark and DataFusion use already in many places.

The plugin can look as simple (relatively speaking) as this:

```rust
#[plugin_fn]
pub fn add_two(args: Prost<proto::UdfArgs>) -> FnResult<Prost<Literal>> {
    let udfargs = args.0;
    
    // Accept only two arguments
    if udfargs.args.len() == 2 {
        let first_arg = udfargs.args.first().unwrap();
        let second_arg = udfargs.args.last().unwrap();

        match (&first_arg.literal_type, &second_arg.literal_type) {
            // When both arguments are Integers
            (Some(LiteralType::Integer(first)), Some(LiteralType::Integer(second))) => {
                // Construct the result 
                let res = Literal { 
                    literal_type: Some(LiteralType::Integer(first + second))
                };
                Ok(Prost(res))
            }
            // When one or both of the arguments aren't Integers
            _ => {
                Err(WithReturnCode(anyhow::format_err!("Invalid arguments {:?}, {:?}", first_arg.literal_type, second_arg.literal_type), -1)) 
            }
        }
    } else {
        Err(WithReturnCode(anyhow::format_err!("Invalid argument count: {}", udfargs.args.len()), -1))
    }
}
```

Because the Spark rows are marshalled using Protobuf, it's possible to have as deeply nested data as you desire, of any of the types that Spark supports.

Calling the plugins from Spark is as easy as using the custom Expression that I've put together:

```scala
WASM_UDF(
    pathOrUrl,
    "add_two",
    IntegerType,
    col("id").cast(IntegerType),
    lit(2)
)
```

This expression takes the path or URL to the WASM file, the function name to call and the expected return type. Everything after that is any number of argument columns that you want to pass to the UDF. This is a bit of an inelegant approach, and one that I hope to improve in the future.

## Performance

The current performance isn't up to par, and that's really because of the Serialization/Deserialization step. For better performance I'm definitely going to explore using Apache Arrow to cut down on this time and even support something like vectorization.

## Next steps

1. Add plugin examples for the remaining languages
2. Explore using a `MapPartition` node instead of a per-row node
3. Migrate some of the examples to use Apache Arrow instead
4. Have the UDFs register during some sort of startup step so that return type and arguments can be checked by the Query planner.