# Dart Language Frontend Engine

Module that supports the Kotlin language specifications and leverages [a dedicated protocol](dart2kotlin_protocol/README.md) for communication within the application.

## Main Components

- Ktor HTTP Server
- Protobuf
- ANTLR 4

## How to update Protobuf classes

```shell
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/request.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/response.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/base.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/position.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/expressions.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java  dart2kotlin_protocol/protocol/statements.proto 
protoc -I=D:\proto3\include\google\protobuf --java_out=src/main/java  D:\proto3\include\google\protobuf\any.proto
```