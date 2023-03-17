/**
 * @author MuratErbilici
 * @since 17.03.2023
 */

public class Interaction{
    private int interactionId;
    private int accountId;
    private int postId;
    

    /**
     * constructor with 3 parameters.
     * @param interactionid for interactionid.
     * @param accountId for accountId.
     * @param postId for postId.
     */
    public Interaction(int interactionid, int accountId, int postId){
        this.interactionId = interactionid;
        this.accountId = accountId;
        this.postId = postId;
    }

    /**
     * getter for accountId.
     */
    public int getaccountId(){
        return accountId;
    }

    /**
     * getter for postId.
     */
    public int getpostId(){
        return postId;
    }
}
