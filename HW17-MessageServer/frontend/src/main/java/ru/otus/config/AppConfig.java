package ru.otus.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc.RemoteMsServiceStub;

@Configuration
public class AppConfig {
    @Value("${grpc.port}")
    private int SERVER_PORT;

    @Value("${grpc.host}")
    private String SERVER_HOST;

    @Bean
    public RemoteMsServiceStub  remoteMsServiceStub() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
        return  RemoteMsServiceGrpc.newStub(channel);

    }


}
