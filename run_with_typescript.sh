#!/bin/bash

make jar
spark-submit --master "local[*]" --class co.gaffe.Runner spark-wasm-udf-assembly*.jar ts_wasm_udf.wasm ts_wasm_result