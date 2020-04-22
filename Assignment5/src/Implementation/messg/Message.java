package Implementation.messg;

import Implementation.Participants.Requestor;

import java.util.Date;

public abstract class Message implements Comparable<Message> {

    private static int ID = 0;

    protected final int id;

    private final Requestor requestor;
    private final MessagePriority messagepriority;
    private final Date date;
    protected Message(Requestor requestor, MessagePriority messagePriority){
        id = getID();
        this.requestor = requestor;
        this.messagepriority = messagePriority;
        this.date = new Date();
    }

    private synchronized static int getID(){
        return ID++;
    }

    public Requestor getRequestor() {
        return requestor;
    }

    public MessagePriority getMessagePriority() {
        return messagepriority;
    }

    public Date getDate() {
        return date;
    }

    public MessageQueue getResponceQueue(){
        return getRequestor().getResponceQueue();
    }

    @Override
    public int compareTo(Message o) {
        int res;
        if(o == null){
            return  -1;
        }
        res = getMessagePriority().compareTo(o.getMessagePriority());
        if(res == 0){
            res = getDate().compareTo(o.getDate());
        }
        if(res == 0){
            return id - o.id;
        }
        return res;
    }

    @Override
    public String toString() {
        return getClass().getName() + "-id: " + id + " PRIORITY " + getMessagePriority();
    }
}
