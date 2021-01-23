package Model.Interpreter.Commands;

import Model.MainModel;

public class AutoRouteCommand implements Command {
    @Override
    public void executeCommand(String[] array) {
        MainModel.turn=false;
    }
}
