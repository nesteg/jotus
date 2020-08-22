package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.app.dao.DbServiceUserImpl;
import ru.otus.app.dao.handlers.GetUserDtoRequestHandler;
import ru.otus.app.dao.handlers.GetUserListDtoRequestHandler;
import ru.otus.app.dao.handlers.SaveUserDtoRequestHandler;
import ru.otus.app.dao.mediator.BaseMediator;
import ru.otus.app.dao.mediator.HandlersMediator;
import ru.otus.app.wsfront.WsFrontServiceImpl;
import ru.otus.app.wsfront.handlers.GetUserDtoResponseHandler;
import ru.otus.app.wsfront.handlers.GetUserListDtoResponseHandler;
import ru.otus.client.CallbackRegistry;
import ru.otus.client.CallbackRegistryImpl;
import ru.otus.client.MsClient;
import ru.otus.client.MsClientImpl;
import ru.otus.core.HandlersStore;
import ru.otus.core.HandlersStoreImpl;
import ru.otus.core.MessageSystem;
import ru.otus.core.MessageSystemImpl;
import ru.otus.message.MessageType;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc.RemoteDBServiceImplBase;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc.RemoteMsServiceImplBase;

@Configuration
public class AppConfig {

    private static final String WEBSOCKET_SERVICE_CLIENT_NAME = "websocketService";
    private static final String DATABASE_SERVICE_CLIENT_NAME  = "daoService";

    @Bean
    BaseMediator handlersmediator(){
        return new HandlersMediator();
    }

    @Bean
    RemoteDBServiceImplBase remoteDBServiceImplBase(BaseMediator mediator){
        return new DbServiceUserImpl(mediator);
    }

    @Bean("requestHandlerDaoStore")
    HandlersStore requestHandlerDaoStore( BaseMediator mediator){
        HandlersStore requestHandlerDaoStore = new HandlersStoreImpl();
        requestHandlerDaoStore.addHandler(MessageType.USER_DATA, new GetUserDtoRequestHandler(mediator));
        requestHandlerDaoStore.addHandler(MessageType.USER_LIST_DATA, new GetUserListDtoRequestHandler(mediator));
        requestHandlerDaoStore.addHandler(MessageType.USER_SAVE_DATA, new SaveUserDtoRequestHandler(mediator));
        return requestHandlerDaoStore;
    };

    @Bean("requestHandlerWebSocket")
    HandlersStore requestHandlerWebSocket( CallbackRegistry callbackRegistry){
        HandlersStore requestHandlerWebSocket = new HandlersStoreImpl();
        requestHandlerWebSocket.addHandler(MessageType.USER_DATA, new GetUserDtoResponseHandler(callbackRegistry));
        requestHandlerWebSocket.addHandler(MessageType.USER_LIST_DATA, new GetUserListDtoResponseHandler(callbackRegistry));
        requestHandlerWebSocket.addHandler(MessageType.USER_SAVE_DATA, new GetUserDtoResponseHandler(callbackRegistry));
        return requestHandlerWebSocket;
    };

    @Bean
    MessageSystem getMessageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    CallbackRegistry getCallbackRegistry() {
        return new CallbackRegistryImpl();
    }

    @Bean
    MsClient databaseMsClient(MessageSystem messageSystem,
                              BaseMediator mediator,
                              CallbackRegistry callbackRegistry) {
        MsClientImpl daoMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDaoStore(mediator), callbackRegistry);
        messageSystem.addClient(daoMsClient);
        return daoMsClient;
    }

    @Bean
    RemoteMsServiceImplBase getWsService(MessageSystem messageSystem,
                                                             CallbackRegistry callbackRegistry) {

        MsClient frontMsClient = new MsClientImpl(WEBSOCKET_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerWebSocket(callbackRegistry), callbackRegistry);
        messageSystem.addClient(frontMsClient);
        return new WsFrontServiceImpl(frontMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }
}
