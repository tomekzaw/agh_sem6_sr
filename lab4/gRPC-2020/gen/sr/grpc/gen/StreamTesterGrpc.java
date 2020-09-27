package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0)",
    comments = "Source: streaming.proto")
public final class StreamTesterGrpc {

  private StreamTesterGrpc() {}

  public static final String SERVICE_NAME = "streaming.StreamTester";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Task,
      sr.grpc.gen.Number> getGeneratePrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GeneratePrimeNumbers",
      requestType = sr.grpc.gen.Task.class,
      responseType = sr.grpc.gen.Number.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Task,
      sr.grpc.gen.Number> getGeneratePrimeNumbersMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Task, sr.grpc.gen.Number> getGeneratePrimeNumbersMethod;
    if ((getGeneratePrimeNumbersMethod = StreamTesterGrpc.getGeneratePrimeNumbersMethod) == null) {
      synchronized (StreamTesterGrpc.class) {
        if ((getGeneratePrimeNumbersMethod = StreamTesterGrpc.getGeneratePrimeNumbersMethod) == null) {
          StreamTesterGrpc.getGeneratePrimeNumbersMethod = getGeneratePrimeNumbersMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Task, sr.grpc.gen.Number>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GeneratePrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Task.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Number.getDefaultInstance()))
              .setSchemaDescriptor(new StreamTesterMethodDescriptorSupplier("GeneratePrimeNumbers"))
              .build();
        }
      }
    }
    return getGeneratePrimeNumbersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.Number,
      sr.grpc.gen.Report> getCountPrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CountPrimeNumbers",
      requestType = sr.grpc.gen.Number.class,
      responseType = sr.grpc.gen.Report.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.Number,
      sr.grpc.gen.Report> getCountPrimeNumbersMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.Number, sr.grpc.gen.Report> getCountPrimeNumbersMethod;
    if ((getCountPrimeNumbersMethod = StreamTesterGrpc.getCountPrimeNumbersMethod) == null) {
      synchronized (StreamTesterGrpc.class) {
        if ((getCountPrimeNumbersMethod = StreamTesterGrpc.getCountPrimeNumbersMethod) == null) {
          StreamTesterGrpc.getCountPrimeNumbersMethod = getCountPrimeNumbersMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.Number, sr.grpc.gen.Report>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CountPrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Number.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.Report.getDefaultInstance()))
              .setSchemaDescriptor(new StreamTesterMethodDescriptorSupplier("CountPrimeNumbers"))
              .build();
        }
      }
    }
    return getCountPrimeNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamTesterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamTesterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamTesterStub>() {
        @java.lang.Override
        public StreamTesterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamTesterStub(channel, callOptions);
        }
      };
    return StreamTesterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamTesterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamTesterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamTesterBlockingStub>() {
        @java.lang.Override
        public StreamTesterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamTesterBlockingStub(channel, callOptions);
        }
      };
    return StreamTesterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamTesterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamTesterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamTesterFutureStub>() {
        @java.lang.Override
        public StreamTesterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamTesterFutureStub(channel, callOptions);
        }
      };
    return StreamTesterFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StreamTesterImplBase implements io.grpc.BindableService {

    /**
     */
    public void generatePrimeNumbers(sr.grpc.gen.Task request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Number> responseObserver) {
      asyncUnimplementedUnaryCall(getGeneratePrimeNumbersMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<sr.grpc.gen.Number> countPrimeNumbers(
        io.grpc.stub.StreamObserver<sr.grpc.gen.Report> responseObserver) {
      return asyncUnimplementedStreamingCall(getCountPrimeNumbersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGeneratePrimeNumbersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                sr.grpc.gen.Task,
                sr.grpc.gen.Number>(
                  this, METHODID_GENERATE_PRIME_NUMBERS)))
          .addMethod(
            getCountPrimeNumbersMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                sr.grpc.gen.Number,
                sr.grpc.gen.Report>(
                  this, METHODID_COUNT_PRIME_NUMBERS)))
          .build();
    }
  }

  /**
   */
  public static final class StreamTesterStub extends io.grpc.stub.AbstractAsyncStub<StreamTesterStub> {
    private StreamTesterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamTesterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamTesterStub(channel, callOptions);
    }

    /**
     */
    public void generatePrimeNumbers(sr.grpc.gen.Task request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.Number> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGeneratePrimeNumbersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<sr.grpc.gen.Number> countPrimeNumbers(
        io.grpc.stub.StreamObserver<sr.grpc.gen.Report> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCountPrimeNumbersMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StreamTesterBlockingStub extends io.grpc.stub.AbstractBlockingStub<StreamTesterBlockingStub> {
    private StreamTesterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamTesterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamTesterBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.Number> generatePrimeNumbers(
        sr.grpc.gen.Task request) {
      return blockingServerStreamingCall(
          getChannel(), getGeneratePrimeNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StreamTesterFutureStub extends io.grpc.stub.AbstractFutureStub<StreamTesterFutureStub> {
    private StreamTesterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamTesterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamTesterFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GENERATE_PRIME_NUMBERS = 0;
  private static final int METHODID_COUNT_PRIME_NUMBERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StreamTesterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StreamTesterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_PRIME_NUMBERS:
          serviceImpl.generatePrimeNumbers((sr.grpc.gen.Task) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Number>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COUNT_PRIME_NUMBERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.countPrimeNumbers(
              (io.grpc.stub.StreamObserver<sr.grpc.gen.Report>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StreamTesterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamTesterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.StreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamTester");
    }
  }

  private static final class StreamTesterFileDescriptorSupplier
      extends StreamTesterBaseDescriptorSupplier {
    StreamTesterFileDescriptorSupplier() {}
  }

  private static final class StreamTesterMethodDescriptorSupplier
      extends StreamTesterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StreamTesterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StreamTesterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamTesterFileDescriptorSupplier())
              .addMethod(getGeneratePrimeNumbersMethod())
              .addMethod(getCountPrimeNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
