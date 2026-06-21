package org.example;
import java.util.List;

interface Command {
    String key();

    Result handleInput(List<String> input);

    static final class Result {
        private final Status status;
        private final String output;

        private Result(Status status, String res) {
            this.status = status;
            this.output = res;
        }

        static Result invalid(String error) {
            return new Result(Status.INVALID, error);
        }

        static Result handled(String output) {
            return new Result(Status.HANDLED, output);
        }

        Status status(){
            return status;
        }

        String output() {
            return output;
        }

        enum Status {
            INVALID,
            HANDLED
        }

    }
}
