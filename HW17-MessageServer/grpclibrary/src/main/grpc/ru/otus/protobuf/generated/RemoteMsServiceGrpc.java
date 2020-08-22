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
public final class RemoteMsServiceGrpc {

  private RemoteMsServiceGrpc() {}

  public static final String SERVICE_NAME = "ru.otus.protobuf.generated.RemoteMsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.UserMessage> getSaveUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveUser",
      requestType = ru.otus.protobuf.generated.UserMessage.class,
      responseType = ru.otus.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage,
      ru.otus.protobuf.generated.UserMessage> getSaveUserMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.UserMessage> getSaveUserMethod;
    if ((getSaveUserMethod = RemoteMsServiceGrpc.getSaveUserMethod) == null) {
      synchronized (RemoteMsServiceGrpc.class) {
        if ((getSaveUserMethod = RemoteMsServiceGrpc.getSaveUserMethod) == null) {
          RemoteMsServiceGrpc.getSaveUserMethod = getSaveUserMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.UserMessage, ru.otus.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "saveUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteMsServiceMethodDescriptorSupplier("saveUser"))
              .build();
        }
      }
    }
    return getSaveUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.Empty,
      ru.otus.protobuf.generated.UserMessage> getFindAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findAllUsers",
      requestType = ru.otus.protobuf.generated.Empty.class,
      responseType = ru.otus.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.Empty,
      ru.otus.protobuf.generated.UserMessage> getFindAllUsersMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.Empty, ru.otus.protobuf.generated.UserMessage> getFindAllUsersMethod;
    if ((getFindAllUsersMethod = RemoteMsServiceGrpc.getFindAllUsersMethod) == null) {
      synchronized (RemoteMsServiceGrpc.class) {
        if ((getFindAllUsersMethod = RemoteMsServiceGrpc.getFindAllUsersMethod) == null) {
          RemoteMsServiceGrpc.getFindAllUsersMethod = getFindAllUsersMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.Empty, ru.otus.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteMsServiceMethodDescriptorSupplier("findAllUsers"))
              .build();
        }
      }
    }
    return getFindAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.otus.protobuf.generated.IdMessage,
      ru.otus.protobuf.generated.UserMessage> getFindByIdUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByIdUser",
      requestType = ru.otus.protobuf.generated.IdMessage.class,
      responseType = ru.otus.protobuf.generated.UserMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ru.otus.protobuf.generated.IdMessage,
      ru.otus.protobuf.generated.UserMessage> getFindByIdUserMethod() {
    io.grpc.MethodDescriptor<ru.otus.protobuf.generated.IdMessage, ru.otus.protobuf.generated.UserMessage> getFindByIdUserMethod;
    if ((getFindByIdUserMethod = RemoteMsServiceGrpc.getFindByIdUserMethod) == null) {
      synchronized (RemoteMsServiceGrpc.class) {
        if ((getFindByIdUserMethod = RemoteMsServiceGrpc.getFindByIdUserMethod) == null) {
          RemoteMsServiceGrpc.getFindByIdUserMethod = getFindByIdUserMethod =
              io.grpc.MethodDescriptor.<ru.otus.protobuf.generated.IdMessage, ru.otus.protobuf.generated.UserMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByIdUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.IdMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.otus.protobuf.generated.UserMessage.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteMsServiceMethodDescriptorSupplier("findByIdUser"))
              .build();
        }
      }
    }
    return getFindByIdUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteMsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceStub>() {
        @java.lang.Override
        public RemoteMsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteMsServiceStub(channel, callOptions);
        }
      };
    return RemoteMsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteMsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceBlockingStub>() {
        @java.lang.Override
        public RemoteMsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteMsServiceBlockingStub(channel, callOptions);
        }
      };
    return RemoteMsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteMsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteMsServiceFutureStub>() {
        @java.lang.Override
        public RemoteMsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteMsServiceFutureStub(channel, callOptions);
        }
      };
    return RemoteMsServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RemoteMsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void saveUser(ru.otus.protobuf.generated.UserMessage request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveUserMethod(), responseObserver);
    }

    /**
     */
    public void findAllUsers(ru.otus.protobuf.generated.Empty request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getFindAllUsersMethod(), responseObserver);
    }

    /**
     */
    public void findByIdUser(ru.otus.protobuf.generated.IdMessage request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getFindByIdUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.UserMessage,
                ru.otus.protobuf.generated.UserMessage>(
                  this, METHODID_SAVE_USER)))
          .addMethod(
            getFindAllUsersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.Empty,
                ru.otus.protobuf.generated.UserMessage>(
                  this, METHODID_FIND_ALL_USERS)))
          .addMethod(
            getFindByIdUserMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ru.otus.protobuf.generated.IdMessage,
                ru.otus.protobuf.generated.UserMessage>(
                  this, METHODID_FIND_BY_ID_USER)))
          .build();
    }
  }

  /**
   */
  public static final class RemoteMsServiceStub extends io.grpc.stub.AbstractAsyncStub<RemoteMsServiceStub> {
    private RemoteMsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteMsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteMsServiceStub(channel, callOptions);
    }

    /**
     */
    public void saveUser(ru.otus.protobuf.generated.UserMessage request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findAllUsers(ru.otus.protobuf.generated.Empty request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFindAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findByIdUser(ru.otus.protobuf.generated.IdMessage request,
        io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFindByIdUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RemoteMsServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RemoteMsServiceBlockingStub> {
    private RemoteMsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteMsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteMsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.otus.protobuf.generated.UserMessage saveUser(ru.otus.protobuf.generated.UserMessage request) {
      return blockingUnaryCall(
          getChannel(), getSaveUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<ru.otus.protobuf.generated.UserMessage> findAllUsers(
        ru.otus.protobuf.generated.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getFindAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<ru.otus.protobuf.generated.UserMessage> findByIdUser(
        ru.otus.protobuf.generated.IdMessage request) {
      return blockingServerStreamingCall(
          getChannel(), getFindByIdUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RemoteMsServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RemoteMsServiceFutureStub> {
    private RemoteMsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteMsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteMsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.otus.protobuf.generated.UserMessage> saveUser(
        ru.otus.protobuf.generated.UserMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveUserMethod(), getCallOptions()), request);
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
    private final RemoteMsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RemoteMsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_USER:
          serviceImpl.saveUser((ru.otus.protobuf.generated.UserMessage) request,
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage>) responseObserver);
          break;
        case METHODID_FIND_ALL_USERS:
          serviceImpl.findAllUsers((ru.otus.protobuf.generated.Empty) request,
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage>) responseObserver);
          break;
        case METHODID_FIND_BY_ID_USER:
          serviceImpl.findByIdUser((ru.otus.protobuf.generated.IdMessage) request,
              (io.grpc.stub.StreamObserver<ru.otus.protobuf.generated.UserMessage>) responseObserver);
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

  private static abstract class RemoteMsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteMsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.otus.protobuf.generated.RemoteDBServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteMsService");
    }
  }

  private static final class RemoteMsServiceFileDescriptorSupplier
      extends RemoteMsServiceBaseDescriptorSupplier {
    RemoteMsServiceFileDescriptorSupplier() {}
  }

  private static final class RemoteMsServiceMethodDescriptorSupplier
      extends RemoteMsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RemoteMsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RemoteMsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteMsServiceFileDescriptorSupplier())
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
