#!/bin/bash

make jar
spark-submit --master "local[*]" --class co.gaffe.Runner spark-wasm-udf-assembly*.jar js_wasm_udf.wasm js_wasm_result