package org.example;

import com.google.common.collect.ImmutableMap;

import dagger.Component;
import io.undertow.Undertow;

@Component(modules={AddModule.class, HelloWorldModule.class})
interface UndertowServerFactory {
    UndertowServer server();
}

public class App {

    public static void main(String[] args) {

        UndertowServerFactory undertowFactory = DaggerUndertowServerFactory.create();
        Undertow server = undertowFactory.server().build();
        server.start();

        /*
            // now, you can create more files like AddCommand.java / HelloWorldCommand.java
            // and Dagger generates these functions:

            Map<String, Command> mapOfStringAndCommand() {
                return ImmutableMap.<String, Command>of("add", new AddCommand(), "hello", new HelloWorldCommand());
            }

            CommandRouter commandRouter() {
                return new CommandRouter(mapOfStringAndCommand());
            }

            @Override
            public UndertowServer server() {
                return new UndertowServer(commandRouter());
            }
        */

    }
}
