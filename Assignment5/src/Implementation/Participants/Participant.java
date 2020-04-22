package Implementation.Participants;

import Implementation.messg.MessageQueue;

public abstract class Participant implements Runnable{

    private static int ID = 0;

    private static int getID(){
        return ID++;
    }

    private final int id;
    private final MessageQueue messageQueue;

    protected Participant(MessageQueue queue){
        id = getID();
        messageQueue = queue;
    }

    public int getId() {
        return id;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    @Override
    public String toString() {
        return getClass() + "-id{ " + getId() + " }";
    }

    protected void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
