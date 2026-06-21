package org.example;
import javax.inject.Inject;
import java.util.List;

final class AddCommand implements Command {

    @Inject 
    AddCommand() {}

    @Override
    public String key() {
        return "add";
    }

    @Override
    public Result handleInput(List<String> input) {
        if(input.isEmpty() || input.size() != 2 ) {
            System.out.println("add command needs numbers to add.");
            return Result.invalid();
        }

        Double op1 = 0.0;
        Double op2 = 0.0;

        try{
            op1 = Double.parseDouble(input.get(0));
            op2 = Double.parseDouble(input.get(1));
        } catch(Exception e){
            System.out.println("Error parsing number inputs: " + e.getMessage());
            return Result.invalid();
        }

        System.out.println("Result: " + (op1 + op2));
        return Result.handled();

    }
}
