```shell
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/request.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/response.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/base.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/position.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/expressions.proto
protoc -I=dart2kotlin_protocol/protocol --java_out=src/main/java --kotlin_out=src/main/kotlin dart2kotlin_protocol/protocol/statements.proto 
protoc -I=D:\proto3\include\google\protobuf --java_out=src/main/java --kotlin_out=src/main/kotlin D:\proto3\include\google\protobuf\any.proto
```