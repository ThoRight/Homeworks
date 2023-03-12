public class interaction{
    private static int interactionidnum=1;
    private int interactionId;
    private int accountId;
    private int postId;
    
    public interaction(int accountId, int postId){
        this.interactionId = interactionidnum;
        this.accountId = accountId;
        this.postId = postId;
        ++interactionidnum;
    }
    public int getaccountId(){
        return accountId;
    }
    public int getpostId(){
        return postId;
    }
}
