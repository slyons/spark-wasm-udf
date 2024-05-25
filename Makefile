all: js_wasm_udf.wasm rust_wasm_udf.wasm spark-wasm-udf*.jar

js_wasm_udf.wasm:
	cd js_wasm_udf && make

rust_wasm_udf.wasm:
	cd rust_wasm_udf && make

spark-wasm-udf*.jar:
	cd wasm_spark && make

jar: spark-wasm-udf*.jar
	
host: all
	python -m http.server

clean:
	cd csharp_wasm_udf && make clean
	cd js_wasm_udf && make clean
	cd rust_wasm_udf && make clean
	cd wasm_spark && make clean

clean_proto:
	cd csharp_wasm_udf && make clean_proto
	cd js_wasm_udf && make clean_proto
	cd rust_wasm_udf && make clean_proto
	cd wasm_spark && make clean_proto

protobuf:
	cd csharp_wasm_udf && make protobuf
	cd js_wasm_udf && make protobuf
	cd rust_wasm_udf && make protobuf
	cd wasm_spark && make protobuf