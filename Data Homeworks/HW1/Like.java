/**
 * @author MuratErbilici
 * @since 17.03.2023
 */

public class Like extends Interaction{

    /**
     * constructor with 3 parameters.
     * it invokes the super class(Interaction) to set parameters.
     * @param interactionid for interactionid.
     * @param accountId for accountId.
     * @param postId for postId.
     */
    public Like(int interactionid, int accountId, int postId){
        super(interactionid, accountId, postId);
    }
}