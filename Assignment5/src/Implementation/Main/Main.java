package Implementation.Main;

import Implementation.Participants.Participant;
import Implementation.Participants.Requestor;
import Implementation.Participants.Service;
import Implementation.messg.MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        List<Service> services = participants(queue,5,Service::new);
        List<Requestor> requestors = participants(queue,5,Requestor::new);
        start(requestors);
        start(services);
    }

    private static <TParticipant extends Participant> void start(List<TParticipant> list){
        list.stream().forEach(e -> new Thread(e).start());
    }

    private static <TParticipant extends Participant> List<TParticipant> participants
            (MessageQueue messageQueue, int pCount, Function<MessageQueue,TParticipant> create){
        List<TParticipant> list = new ArrayList<>();
        for (int i = 0; i < pCount; i++) {
            TParticipant participant = create.apply(messageQueue);
            list.add(participant);
        }
        return list;
    }
}
