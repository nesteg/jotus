package ru.otus.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.cachehw.HwCache;
import ru.otus.core.cachehw.MyCache;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.*;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc;
import ru.otus.protobuf.generated.RemoteDBServiceGrpc.RemoteDBServiceStub;


@Configuration
public class AppConfig {

    @Value("${grpc.port}")
    private int SERVER_PORT;

    @Value("${grpc.host}")
    private String SERVER_HOST;

    @Value("${dbinit.action}")
    private String DB_INIT;

    @Bean
    public <K, V> HwCache<K, V> cache() {
        return new MyCache<>();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtils.buildSessionFactory("hibernate.cfg.xml",
                User.class,
                AddressDataSet.class,
                PhoneDataSet.class
        );
    }

    @Bean
    RemoteDBServiceStub remoteDBServiceGrpc() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
       return RemoteDBServiceGrpc.newStub(channel);
    }

    @Bean(initMethod = "init")
    public DBInitialization dbInit(DBServiceUser dbServiceuser) {
       return new DBInitializationImpl(dbServiceuser,DB_INIT);
    }

}
