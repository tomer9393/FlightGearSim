package Model.Interpreter.Commands;

import Model.Interpreter.Expressions.*;
import Model.Interpreter.Interpret.*;

public class ReturnCommand implements Command {

    @Override
    public void executeCommand(String[] array) {

        StringBuilder exp = new StringBuilder();
        for (int i = 1; i < array.length; i++)
            exp.append(array[i]);
        CompParser.returnValue = MathExpression.calc(exp.toString());
    }

}
