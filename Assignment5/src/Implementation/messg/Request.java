package Implementation.messg;

import Implementation.Participants.Requestor;

public class Request extends Message{

    private final double component1;
    private final double component2;

    public Request(Requestor re){
        super(re, MessagePriority.getRandomPriority());
        component1 = (int)(Math.random()*100);
        component2 = (int)(Math.random()*100);
    }

    public double getComponent1() {
        return component1;
    }

    public double getComponent2() {
        return component2;
    }

    @Override
    public String toString() {
        return super.toString() + " { " + component1 + " , " + component2 + " }";
    }
}
