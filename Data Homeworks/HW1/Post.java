/**
 * @author MuratErbilici
 * @since 17.03.2023
 */

public class Post{
    private int postId;
    private int accountId;
    private Like[] likes;
    private Comment[] comments;
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
        likes = new Like[10];
        comments = new Comment[10];
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
     * to add like to like array.
     * @param l the like object that we want to add.
     */
    public void addLike(Like l){
        if(likeNumber==likes.length){
            Like[] tempLikes = new Like[likes.length*2];
            for(int i=0;i<likes.length;++i){
                tempLikes[i] = likes[i];
            }
            likes = tempLikes;
        }
        likes[likeNumber] =  l;
        ++likeNumber;
    }

    /**
     * to add comment to comment array.
     * @param com the comment object that we want to add.
     */
    public void addComment(Comment com){
        if(commentNumber==comments.length){
            Comment[] tempComments = new Comment[comments.length*2];
            for(int i=0;i<comments.length;++i){
                tempComments[i] = comments[i];
            }
            comments = tempComments;
        }
        comments[commentNumber] =  com;
        ++commentNumber;
    } 

    /**
     * getter for one element of likes array.
     */
    public Like getonelike(int index){
        return likes[index];
    }

    /**
     * getter for one element of comments array.
     */
    public Comment getonecomment(int index){
        return comments[index];
    }
}