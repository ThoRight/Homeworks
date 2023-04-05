/**
 * @author MuratErbilici
 * @since 03.04.2023
 */


import java.util.*;

public class Message{
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;

    /**
     * constructor with 4 parameters.
     * @param msgId for messageId.
     * @param senderId for senderId.
     * @param receiverId for receiverId.
     * @param content for content.
     */
    public Message(int msgId, int senderId, int receiverId, String content){
        this.messageId = msgId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    /**
     * getter for messageId.
     */
    public int getMessageId(){
        return messageId;
    }

    /**
     * getter for receiverId.
     */
    public int getReceiverId(){
        return receiverId;
    }

    /**
     * getter for senderId.
     */
    public int getSenderId(){
        return senderId;
    }


    @Override
    public String toString(){
        return content;
    }
}
