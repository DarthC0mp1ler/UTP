package Implementation.messg;

public class Response extends Message{

    private final Request _request;
    private final double result;

    public Response(Request request){
        super(request.getRequestor(), request.getMessagePriority());
        _request = request;
        //result = Math.sqrt(Math.pow(request.getComponent1(),2) + Math.pow(request.getComponent2(),2));
        result = request.getComponent1() + request.getComponent2();
    }

    @Override
    public String toString() {
        return super.toString() + " { " + result + " } REQUEST ID: " + _request.id;
    }
}
