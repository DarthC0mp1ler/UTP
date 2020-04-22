package Implementation.Participants;

import Implementation.messg.MessageQueue;
import Implementation.messg.Request;
import Implementation.messg.Response;

public class Service extends Participant{

    public Service(MessageQueue queue) {
        super(queue);
    }

    @Override
    public void run() {
        while (true){
            Request request = getMessageQueue().getRequestFromQueue();
            if(request != null) {
                Response response = new Response(request);
                System.out.println("REQUEST-RESPONSE: (" + request + ") , (" + response + ")");
                request.getResponceQueue().queueOffer(response);
            }
        }
    }
}
