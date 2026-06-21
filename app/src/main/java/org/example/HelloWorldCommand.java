package org.example;
import javax.inject.Inject;
import java.util.List;

final class HelloWorldCommand implements Command {
  @Inject
  HelloWorldCommand() {}

  @Override
  public String key() {
    return "hello";
  }

  @Override
  public Result handleInput(List<String> input) {
    if (!input.isEmpty()) {
      return Result.invalid();
    }
    System.out.println("world!");
    return Result.handled();
  }
}
