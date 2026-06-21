package org.example;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.example.Command.Result;
import org.example.Command.Result.Status;


final class CommandRouter {

    private final Map<String, Command> commands = new HashMap<>();

    @Inject
    CommandRouter(AddCommand addCmd, HelloWorldCommand hwCmd) {
        commands.put(addCmd.key(), addCmd);
        commands.put(hwCmd.key(), hwCmd);
    }

    Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        List<String> args = splitInput.subList(1, splitInput.size());
        Result result = command.handleInput(args);
        return result.status().equals(Status.INVALID)? invalidCommand(input) : result ;

    }

    private Result invalidCommand(String input) {
        System.out.println(
            String.format(
                "Could not understand \"%s\". Please try again.",
                input
            )
        );
        return Result.invalid();
    }

    private static List<String> split(String input) {
        // splits on whitespace..
        return Arrays.asList(input.trim().split("\\s+"));
    }

}
