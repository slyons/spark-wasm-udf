all: wasm

src/literal_pb.js:
	protoc ../proto/literal.proto --proto_path=../proto/ --js_out=import_style=commonjs,binary:src/

src/types_pb.js:
	protoc ../proto/types.proto --proto_path=../proto/ --js_out=import_style=commonjs,binary:src/

src/wasmudf_pb.js:
	protoc ../proto/wasmudf.proto --proto_path=../proto/ --js_out=import_style=commonjs,binary:src/

protobuf: src/literal_pb.js src/types_pb.js src/wasmudf_pb.js

wasm: protobuf
	npm run build
	cp dist/plugin.wasm ../js_wasm_udf.wasm

clean:
	rm -rf dist || true

clean_proto:
	rm src/*_pb.js