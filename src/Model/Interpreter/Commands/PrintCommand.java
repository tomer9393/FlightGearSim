package Model.Interpreter.Commands;

import Model.Interpreter.Interpret.*;

public class PrintCommand implements Command {
    @Override
    public void executeCommand(String[] array) {
       for (int i=1;i<array.length;i++)
       {
           if(CompParser.symbolTable.containsKey(array[i]))
                System.out.print(array[i]+CompParser.symbolTable.get(array[i]).getV());
           else
               System.out.print(array[i]);
       }
        System.out.println("");
    }
}
