public class Interaction{
    private int interactionId;
    private int accountId;
    private int postId;
    
    public Interaction(int interactionid, int accountId, int postId){
        this.interactionId = interactionid;
        this.accountId = accountId;
        this.postId = postId;
    }
    public int getaccountId(){
        return accountId;
    }
    public int getpostId(){
        return postId;
    }
}
