public class message{
    private static int messageNumber=1;
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;

    public message(int senderId, int receiverId, String content){
        this.messageId = messageNumber;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        ++messageNumber;
    }

    public int getMessageId(){
        return messageId;
    }
    public int getReceiverId(){
        return receiverId;
    }
    public int getSenderId(){
        return senderId;
    }
    public String toString(){
        return content;
    }
}