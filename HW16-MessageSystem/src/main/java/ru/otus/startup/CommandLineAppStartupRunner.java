package ru.otus.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.otus.core.service.DBInitialization;
import ru.otus.core.service.DBInitializationImpl;
import ru.otus.core.service.DBServiceUser;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    DBServiceUser dbServiceuser;

    @Override
    public void run(String...args) throws Exception {

    }
}