package ru.otus.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.service.DBServiceUser;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.CallbackRegistryImpl;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystemapp.dao.handlers.GetUserDtoRequestHandler;
import ru.otus.messagesystemapp.dao.handlers.GetUserListDtoRequestHandler;
import ru.otus.messagesystemapp.dao.handlers.SaveUserDtoRequestHandler;
import ru.otus.messagesystemapp.wsfront.WsFrontService;
import ru.otus.messagesystemapp.wsfront.WsFrontServiceImpl;
import ru.otus.messagesystemapp.wsfront.handlers.GetUserDtoResponseHandler;
import ru.otus.messagesystemapp.wsfront.handlers.GetUserListDtoResponseHandler;

@Configuration
public class MessageSystemConfig {

    private static final String WEBSOCKET_SERVICE_CLIENT_NAME = "websocketService";
    private static final String DATABASE_SERVICE_CLIENT_NAME  = "daoService";


    @Bean("requestHandlerDaoStore")
    HandlersStore requestHandlerDaoStore( DBServiceUser dbServiceUser){
        HandlersStore requestHandlerDaoStore = new HandlersStoreImpl();
        requestHandlerDaoStore.addHandler(MessageType.USER_DATA, new GetUserDtoRequestHandler(dbServiceUser));
        requestHandlerDaoStore.addHandler(MessageType.USER_LIST_DATA, new GetUserListDtoRequestHandler(dbServiceUser));
        requestHandlerDaoStore.addHandler(MessageType.USER_SAVE_DATA, new SaveUserDtoRequestHandler(dbServiceUser));
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
                              DBServiceUser dbServiceUser,
                              CallbackRegistry callbackRegistry) {
        MsClientImpl daoMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerDaoStore(dbServiceUser), callbackRegistry);
        messageSystem.addClient(daoMsClient);
        return daoMsClient;
    }

    @Bean
    WsFrontService getWsService( MessageSystem messageSystem,
                                 CallbackRegistry callbackRegistry) {

        MsClient frontMsClient = new MsClientImpl(WEBSOCKET_SERVICE_CLIENT_NAME,
                messageSystem, requestHandlerWebSocket(callbackRegistry), callbackRegistry);
        messageSystem.addClient(frontMsClient);
        return new WsFrontServiceImpl(frontMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }

}
