package ru.otus.server;

import com.google.gson.Gson;
import org.eclipse.jetty.security.*;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import ru.otus.core.dao.UserDao;
import ru.otus.core.service.DBServiceUser;
import ru.otus.helpers.FileSystemHelper;
import ru.otus.services.DBInitialization;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.UserAuthService;
import ru.otus.servlet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UsersWebServerSimple implements UsersWebServer {
    private static final String START_PAGE_NAME = "index.html";
    private static final String COMMON_RESOURCES_DIR = "static";

    private final DBServiceUser serviceUser;
    private final Gson gson;
    protected final TemplateProcessor templateProcessor;
    private final DBInitialization dbInitialization;
    private final Server server;

    public UsersWebServerSimple(int port,
                                DBServiceUser serviceUser,
                                Gson gson,
                                TemplateProcessor templateProcessor,
                                DBInitialization dbInitialization) {
        this.serviceUser = serviceUser;
        this.gson = gson;
        this.templateProcessor = templateProcessor;
        this.dbInitialization = dbInitialization;
        server = new Server(port);
    }

    @Override
    public void start() throws Exception {
        if (server.getHandlers().length == 0) {
            initContext();
        }
        server.start();
    }

    @Override
    public void join() throws Exception {
        server.join();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    private Server initContext() {

        ResourceHandler resourceHandler = createResourceHandler();
        ServletContextHandler servletContextHandler = createServletContextHandler();

        HandlerList handlers = new HandlerList();
        handlers.addHandler(resourceHandler);
        handlers.addHandler(applySecurity(servletContextHandler, "/users", "/api/user/*", "/api/user/add"));


        server.setHandler(handlers);
        dbInitialization.Init();
        return server;
    }

    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        return servletContextHandler;
    }

    private ResourceHandler createResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{START_PAGE_NAME});
        resourceHandler.setResourceBase(FileSystemHelper.localFileNameOrResourceNameToFullPath(COMMON_RESOURCES_DIR));
        return resourceHandler;
    }

    private ServletContextHandler createServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new UsersServlet(templateProcessor, serviceUser)), "/users");
        servletContextHandler.addServlet(new ServletHolder(new UsersApiServlet(serviceUser, gson)), "/api/user/*");
        servletContextHandler.addServlet(new ServletHolder(new CreateUser(serviceUser, gson)), "/api/user/add");
        return servletContextHandler;
    }
}
