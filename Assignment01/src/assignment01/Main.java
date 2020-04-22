package assignment01;

public class Main {

    public Main(){
        boolean doingSomething = true;
            if(doingSomething){
                System.out.println("I dont know");
            }
            if(doingSomething){
                System.out.println("No OK");
                try {
                throw new  DontHaveTimeTaxiWaitException("Yes");
                } catch (DontHaveTimeTaxiWaitException d){
                    d.printStackTrace();
                }
            }

    }

    public static void main(String[] args) {
        new Main();
    }

}

class DontHaveTimeTaxiWaitException extends Exception{

    public DontHaveTimeTaxiWaitException(String mesg){
        super(mesg);
    }

    @Override
    public void printStackTrace() {
        System.err.println("Cannot find any acceptable soulution to be done.\nNo tak, I dont know doesnt work." +
                "\nHe is still asking me something.\nNo OK\nCast ultimate spell.");
        super.printStackTrace();
    }
}
