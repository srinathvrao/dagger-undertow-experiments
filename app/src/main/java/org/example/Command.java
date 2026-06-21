package org.example;
import java.util.List;

interface Command {
    String key();

    Result handleInput(List<String> input);

    static final class Result {
        private final Status status;

        private Result(Status status) {
            this.status = status;
        }

        static Result invalid() {
            return new Result(Status.INVALID);
        }

        static Result handled() {
            return new Result(Status.HANDLED);
        }

        Status status(){
            return status;
        }

        enum Status {
            INVALID,
            HANDLED
        }

    }
}
