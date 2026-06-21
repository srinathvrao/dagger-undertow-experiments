package org.example;
import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import java.util.List;

@Module
abstract class AddModule {

    @Binds
    @IntoMap
    @StringKey("add")
    abstract Command addCommand(AddCommand command);
}

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
            return Result.invalid("add command needs numbers to add.");
        }

        Double op1 = 0.0;
        Double op2 = 0.0;

        try{
            op1 = Double.parseDouble(input.get(0));
            op2 = Double.parseDouble(input.get(1));
        } catch(Exception e){
            return Result.invalid("Error parsing number inputs: " + e.getMessage());
        }

        Double res = op1 + op2;
        return Result.handled(res+"");

    }
}
