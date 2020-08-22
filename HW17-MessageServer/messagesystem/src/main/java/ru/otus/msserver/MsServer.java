package ru.otus.msserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc.RemoteDBServiceImplBase;
import ru.otus.protobuf.generated.RemoteMsServiceGrpc.RemoteMsServiceImplBase;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Order(value = 1)
public class MsServer implements CommandLineRunner {

    public static final int SERVER_PORT = 8090;
    private final RemoteDBServiceImplBase remoteDBService;
    private final RemoteMsServiceImplBase remoteMsService;

    public MsServer(RemoteDBServiceImplBase remoteDBService, RemoteMsServiceImplBase remoteMsService) {
        this.remoteDBService = remoteDBService;
        this.remoteMsService = remoteMsService;
    }

    @Override
    public void run(String... args) throws Exception {
        Server server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(remoteMsService)
                .addService(remoteDBService)
                .build();
        server.start();
    }
}
