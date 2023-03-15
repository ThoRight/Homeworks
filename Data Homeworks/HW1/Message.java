public class Message{
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;

    public Message(int msgId, int senderId, int receiverId, String content){
        this.messageId = msgId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
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