#!/bin/bash

make jar
spark-submit --master "local[*]" --class co.gaffe.Runner spark-wasm-udf-assembly*.jar --wasm-path-or-url ./js_wasm_udf.wasm --no-chicory "$@"