package Implementation.messg;

enum MessagePriority {
    HIGH_PRIORITY,NORMAL_PRIORITY,LOW_PRIORIRY;

    private static MessagePriority getI(int i){
        return MessagePriority.values()[i];
    }

    public static MessagePriority getRandomPriority(){
        return getI((int)(Math.random()*3));
    }


}
