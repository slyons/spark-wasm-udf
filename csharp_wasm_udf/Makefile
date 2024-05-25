all: wasm

clean:
	rm -r bin || true
	rm -r obj || true

Types.cs:
	protoc ../proto/types.proto --proto_path=../proto/ --csharp_out=.

Literal.cs:
	protoc ../proto/literal.proto --proto_path=../proto/ --csharp_out=.

Wasmudf.cs:
	protoc ../proto/wasmudf.proto --proto_path=../proto/ --csharp_out=.

wasm: protobuf
	dotnet run
	cp bin/Debug/net8.0/wasi-wasm/dotnet.wasm ../csharp_wasm_udf.wasm

protobuf: Types.cs Literal.cs Wasmudf.cs

clean_proto:
	rm Types.cs Literal.cs Wasmudf.cs || true
