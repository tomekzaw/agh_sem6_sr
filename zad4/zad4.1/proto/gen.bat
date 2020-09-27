@echo off
python -m grpc_tools.protoc -I. --python_out=../python/generated --grpc_python_out=../python/generated alerts.proto
protoc --java_out=../java/alerts/gen --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java.exe --grpc-java_out=../java/alerts/gen alerts.proto
