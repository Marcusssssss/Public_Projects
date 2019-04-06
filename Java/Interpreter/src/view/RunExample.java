package view;
import controller.Controller;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage()); //here you must treat the exceptions that can not be solved in the controller
        }
    }

    public Controller getController(){
        return ctr;
    }
}