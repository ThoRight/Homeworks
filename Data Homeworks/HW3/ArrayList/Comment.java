/**
 * @author MuratErbilici
 * @since 03.04.2023
 */
 

import java.util.*;

public class Comment extends Interaction{
    private String content;

    /**
     * constructor with 4 parameters.
     * it invokes the super class(Interaction) to set 3 of 4 parameters.
     * @param interactionid for interactionid.
     * @param accountId for accountId.
     * @param postId for postId.
     * @param content for content.
     */
    public Comment(int interactionid, int accountId, int postId, String content){
        super(interactionid, accountId, postId);
        this.content = content;
    }


    @Override
    public String toString(){
        return content;
    }
}
