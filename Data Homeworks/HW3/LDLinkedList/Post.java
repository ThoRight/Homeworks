/**
 * @author MuratErbilici
 * @since 03.04.2023
 */

import java.util.*;

public class Post{
    private int postId;
    private int accountId;
    private LDLinkedList<Like> likes;
    private LDLinkedList<Comment> comments;
    private String content;
    private int likeNumber=0;
    private int commentNumber=0;
    private int interactionNumber=0;

    /**
     * constructor with 3 parameters.
     * @param postid for postId.
     * @param acc the account that have posted.
     * @param content for content.
     */
    public Post(int postid, Account acc, String content){
        this.postId = postid;
        this.accountId = acc.getaccountId();
        this.content = content;
        likes = new LDLinkedList<Like>();
        comments = new LDLinkedList<Comment>();
    }


    @Override
    public String toString(){
        return "(Post ID: " + postId + "): " + content;
    }

    /**
     * getter for postId.
     */
    public int getpostId(){
        return postId;
    }

    /**
     * getter for likeNumber.
     */
    public int getlikeNumber(){
        return likeNumber;
    }

    /**
     * getter for commentNumber.
     */
    public int getcommentNumber(){
        return commentNumber;
    }

    /**
     * getter for interactionNumber.
     */
    public int getinteractionNumber(){
        return interactionNumber;
    }

    /**
     * to add like to likes.
     * @param l the like object that we want to add.
     */
    public void addLike(Like l){
        likes.add(l);
        ++likeNumber;
    }

    public void removeLike(Like l){
        likes.remove(l);
        --likeNumber;
    }

    /**
     * to add comment to comments.
     * @param com the comment object that we want to add.
     */
    public void addComment(Comment com){
        comments.add(com);
        ++commentNumber;
    }

    public void removeComment(Comment com){
        comments.remove(com);
        --commentNumber;

    }

    /**
     * getter for one element of likess.
     */
    public Like getonelike(int index){
        return likes.get(index);
    }

    /**
     * getter for one element of comments.
     */
    public Comment getonecomment(int index){
        return comments.get(index);
    }
    public LDLinkedList<Comment> getcomments(){
        return comments;
    }
    public LDLinkedList<Like> getlikes(){
        return likes;
    }

    public boolean isalreadyliked(Like l){
        for(int i=0;i<likeNumber;++i){
            if(likes.get(i)==l && likes.getNode(i).getisdeleted()==false) 
                return true;
        }
        return false;
    }
}
