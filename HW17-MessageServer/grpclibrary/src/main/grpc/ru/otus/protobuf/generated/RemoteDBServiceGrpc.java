package ru.otus.protobuf.generated;

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
    value = "by gRPC proto compiler (version 1.30.2)",
    comments = "Source: RemoteDBService.proto")
public final class RemoteDBServiceGrpc {

  private RemoteDBServiceGrpc() {}

  public static final String SERVICE_NAME = "ru.otus.protobuf.generated.RemoteDBService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.UserMessage> getSaveUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveUser",
      requestType = ru.otus.protobuf.generated.UserMessage.class,
      responseType = ru.otus.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.UserMessage> getSaveUserMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.UserMessage> getSaveUserMethod;
    if ((getSaveUserMethod = RemoteDBServiceGrpc.getSaveUserMethod) == null) {
      synchronized (RemoteDBServiceGrpc.class) {
        if ((getSaveUserMethod = RemoteDBServiceGrpc.getSaveUserMethod) == null) {
          RemoteDBServiceGrpc.getSaveUserMethod = getSaveUserMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "saveUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteDBServiceMethodDescriptorSupplier("saveUser"))
              .build();
        }
      }
    }
    return getSaveUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.Empty> getFindAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findAllUsers",
      requestType = ru.otus.protobuf.generated.UserMessage.class,
      responseType = ru.otus.protobuf.generated.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.Empty> getFindAllUsersMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.Empty> getFindAllUsersMethod;
    if ((getFindAllUsersMethod = RemoteDBServiceGrpc.getFindAllUsersMethod) == null) {
      synchronized (RemoteDBServiceGrpc.class) {
        if ((getFindAllUsersMethod = RemoteDBServiceGrpc.getFindAllUsersMethod) == null) {
          RemoteDBServiceGrpc.getFindAllUsersMethod = getFindAllUsersMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteDBServiceMethodDescriptorSupplier("findAllUsers"))
              .build();
        }
      }
    }
    return getFindAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.IdMessage> getFindByIdUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByIdUser",
      requestType = ru.otus.protobuf.generated.UserMessage.class,
      responseType = ru.otus.protobuf.generated.IdMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.IdMessage> getFindByIdUserMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.IdMessage> getFindByIdUserMethod;
    if ((getFindByIdUserMethod = RemoteDBServiceGrpc.getFindByIdUserMethod) == null) {
      synchronized (RemoteDBServiceGrpc.class) {
        if ((getFindByIdUserMethod = RemoteDBServiceGrpc.getFindByIdUserMethod) == null) {
          RemoteDBServiceGrpc.getFindByIdUserMethod = getFindByIdUserMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.IdMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByIdUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.IdMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteDBServiceMethodDescriptorSupplier("findByIdUser"))
              .build();
        }
      }
    }
    return getFindByIdUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteDBServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceStub>() {
        @java.lang.Override
        public RemoteDBServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteDBServiceStub(channel, callOptions);
        }
      };
    return RemoteDBServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteDBServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceBlockingStub>() {
        @java.lang.Override
        public RemoteDBServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteDBServiceBlockingStub(channel, callOptions);
        }
      };
    return RemoteDBServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteDBServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteDBServiceFutureStub>() {
        @java.lang.Override
        public RemoteDBServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteDBServiceFutureStub(channel, callOptions);
        }
      };
    return RemoteDBServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RemoteDBServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> saveUser(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getSaveUserMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> findAllUsers(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.Empty> responseObserver) {
      return asyncUnimplementedStreamingCall(getFindAllUsersMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> findByIdUser(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.IdMessage> responseObserver) {
      return asyncUnimplementedStreamingCall(getFindByIdUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveUserMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.UserMessage,
                ru.otus.protobuf.generated.UserMessage>(
                  this, METHODID_SAVE_USER)))
          .addMethod(
            getFindAllUsersMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.UserMessage,
                ru.otus.protobuf.generated.Empty>(
                  this, METHODID_FIND_ALL_USERS)))
          .addMethod(
            getFindByIdUserMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.UserMessage,
                ru.otus.protobuf.generated.IdMessage>(
                  this, METHODID_FIND_BY_ID_USER)))
          .build();
    }
  }

  /**
   */
  public static final class RemoteDBServiceStub extends io.grpc.stub.AbstractAsyncStub<RemoteDBServiceStub> {
    private RemoteDBServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteDBServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteDBServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> saveUser(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSaveUserMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> findAllUsers(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.Empty> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getFindAllUsersMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> findByIdUser(
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.IdMessage> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getFindByIdUserMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RemoteDBServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RemoteDBServiceBlockingStub> {
    private RemoteDBServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteDBServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteDBServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class RemoteDBServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RemoteDBServiceFutureStub> {
    private RemoteDBServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteDBServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteDBServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SAVE_USER = 0;
  private static final int METHODID_FIND_ALL_USERS = 1;
  private static final int METHODID_FIND_BY_ID_USER = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RemoteDBServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RemoteDBServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_USER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.saveUser(
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage>) responseObserver);
        case METHODID_FIND_ALL_USERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.findAllUsers(
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.Empty>) responseObserver);
        case METHODID_FIND_BY_ID_USER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.findByIdUser(
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.IdMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RemoteDBServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteDBServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.otus.protobuf.generated.RemoteDBServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteDBService");
    }
  }

  private static final class RemoteDBServiceFileDescriptorSupplier
      extends RemoteDBServiceBaseDescriptorSupplier {
    RemoteDBServiceFileDescriptorSupplier() {}
  }

  private static final class RemoteDBServiceMethodDescriptorSupplier
      extends RemoteDBServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RemoteDBServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RemoteDBServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteDBServiceFileDescriptorSupplier())
              .addMethod(getSaveUserMethod())
              .addMethod(getFindAllUsersMethod())
              .addMethod(getFindByIdUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
