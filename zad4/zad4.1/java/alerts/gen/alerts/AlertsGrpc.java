package alerts;

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
    comments = "Source: alerts.proto")
public final class AlertsGrpc {

  private AlertsGrpc() {}

  public static final String SERVICE_NAME = "Alerts";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<alerts.Subscription,
      alerts.Notification> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = alerts.Subscription.class,
      responseType = alerts.Notification.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<alerts.Subscription,
      alerts.Notification> getSubscribeMethod() {
    io.grpc.MethodDescriptor<alerts.Subscription, alerts.Notification> getSubscribeMethod;
    if ((getSubscribeMethod = AlertsGrpc.getSubscribeMethod) == null) {
      synchronized (AlertsGrpc.class) {
        if ((getSubscribeMethod = AlertsGrpc.getSubscribeMethod) == null) {
          AlertsGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<alerts.Subscription, alerts.Notification>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  alerts.Subscription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  alerts.Notification.getDefaultInstance()))
              .setSchemaDescriptor(new AlertsMethodDescriptorSupplier("subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlertsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertsStub>() {
        @java.lang.Override
        public AlertsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertsStub(channel, callOptions);
        }
      };
    return AlertsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlertsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertsBlockingStub>() {
        @java.lang.Override
        public AlertsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertsBlockingStub(channel, callOptions);
        }
      };
    return AlertsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlertsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlertsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlertsFutureStub>() {
        @java.lang.Override
        public AlertsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlertsFutureStub(channel, callOptions);
        }
      };
    return AlertsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AlertsImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribe(alerts.Subscription request,
        io.grpc.stub.StreamObserver<alerts.Notification> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                alerts.Subscription,
                alerts.Notification>(
                  this, METHODID_SUBSCRIBE)))
          .build();
    }
  }

  /**
   */
  public static final class AlertsStub extends io.grpc.stub.AbstractAsyncStub<AlertsStub> {
    private AlertsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertsStub(channel, callOptions);
    }

    /**
     */
    public void subscribe(alerts.Subscription request,
        io.grpc.stub.StreamObserver<alerts.Notification> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AlertsBlockingStub extends io.grpc.stub.AbstractBlockingStub<AlertsBlockingStub> {
    private AlertsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertsBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<alerts.Notification> subscribe(
        alerts.Subscription request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AlertsFutureStub extends io.grpc.stub.AbstractFutureStub<AlertsFutureStub> {
    private AlertsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlertsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlertsFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AlertsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AlertsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((alerts.Subscription) request,
              (io.grpc.stub.StreamObserver<alerts.Notification>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AlertsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AlertsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return alerts.AlertsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Alerts");
    }
  }

  private static final class AlertsFileDescriptorSupplier
      extends AlertsBaseDescriptorSupplier {
    AlertsFileDescriptorSupplier() {}
  }

  private static final class AlertsMethodDescriptorSupplier
      extends AlertsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AlertsMethodDescriptorSupplier(String methodName) {
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
      synchronized (AlertsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlertsFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
