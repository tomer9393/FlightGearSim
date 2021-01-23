package Model.Interpreter.Commands;

import Model.Interpreter.Expressions.*;


public class SleepCommand implements Command {
    @Override
    public void executeCommand(String[] array) {
        try {
            Thread.sleep((long)MathExpression.calc(array[1]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
