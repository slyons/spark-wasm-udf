all: jar

jar: target/scala-2.12/spark-wasm-udf-assembly*.jar
	cp target/scala-2.12/spark-wasm-udf-assembly*.jar ../

clean:
	sbt clean

clean_proto:
	rm src/main/java/co/gaffe/proto/* || true

src/main/java/co/gaffe/proto/Types.java:
	protoc ../proto/types.proto --proto_path=../proto/ --java_out=lite:src/main/java

src/main/java/co/gaffe/proto/Literal.java:
	protoc ../proto/literal.proto --proto_path=../proto/ --java_out=lite:src/main/java

src/main/java/co/gaffe/proto/Wasmudf.java:
	protoc ../proto/wasmudf.proto --proto_path=../proto/ --java_out=lite:src/main/java

protobuf: src/main/java/co/gaffe/proto/Types.java src/main/java/co/gaffe/proto/Literal.java src/main/java/co/gaffe/proto/Wasmudf.java

target/scala-2.12/spark-wasm-udf*.jar: protobuf
	sbt assembly