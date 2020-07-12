package ru.otus;

import ru.otus.appcontainer.AppComponentsContainerImpl;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.services.GameProcessor;

/*
В классе AppComponentsContainerImpl реализовать обработку, полученной в конструкторе конфигурации,
основываясь на разметке аннотациями из пакета appcontainer. Так же необходимо реализовать методы getAppComponent.
В итоге должно получиться работающее приложение. Менять можно только класс AppComponentsContainerImpl.

PS Приложение представляет из себя тренажер таблицы умножения)
*/
/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./HW13-DI/build/libs/Otus-0.1.jar
 *
 * To build:
 * ./gradlew build
 */
public class App {

    public static void main(String[] args) throws Exception {
        AppComponentsContainer container = new AppComponentsContainerImpl("ru.otus.config");
        GameProcessor gameProcessor = container.getAppComponent(GameProcessor.class);
        gameProcessor.startGame();
    }
}
