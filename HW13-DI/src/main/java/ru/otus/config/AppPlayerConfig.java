package ru.otus.config;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.services.*;

import java.util.Scanner;

@AppComponentsContainerConfig(order =1)
public class AppPlayerConfig {

    @AppComponent(order = 0, name = "playerService")
    public PlayerService playerService(IOService ioService) {
        return new PlayerServiceImpl(ioService);
    }

}
