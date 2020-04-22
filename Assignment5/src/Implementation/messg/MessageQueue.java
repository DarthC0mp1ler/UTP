package Implementation.messg;

import Implementation.Participants.Requestor;

import java.util.PriorityQueue;
import java.util.Queue;

public class MessageQueue {

    private final Queue<Message> priorityQueue;

    public MessageQueue() {
        priorityQueue = new PriorityQueue<>();
    }

    public synchronized void queueOffer(Message m) {
        priorityQueue.offer(m);
    }

    public synchronized Request getRequestFromQueue() {
        Request request = null;

//        Message message = priorityQueue.peek();
//        if (message instanceof Request) {
          request = (Request) priorityQueue.poll();
//        }

        return request;
    }

    public synchronized Response getResponceGromQueue() {

        Response response = null;
//        priorityQueue.stream().forEach(e -> {Message message = priorityQueue.peek();
//            if(message instanceof Response && message.getRequestor() == requestor){
//                response = (Response) priorityQueue.poll();
//            }});
//        for (Message m : priorityQueue) {
//            if (m instanceof Response && m.getRequestor() == requestor) {
//                response = (Response) m;
//                priorityQueue.remove(m);
//                break;
//            }
//        }
//        if (priorityQueue.peek() instanceof Response && priorityQueue.peek().getRequestor() == requestor) {
            response = (Response) priorityQueue.poll();
//        }
        return response;
    }


    @Override
    public synchronized String toString() {
        return priorityQueue.toString();
    }
}
