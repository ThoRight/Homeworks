public class Post{
    private int postId;
    private int accountId;
    private Like[] likes;
    private Comment[] comments;
//    private interaction[] interactions;
    private String content;
    private int likeNumber=0;
    private int commentNumber=0;
    private int interactionNumber=0;

    public Post(int postid, Account acc, String content){
        this.postId = postid;
        this.accountId = acc.getaccountId();
        this.content = content;
        likes = new Like[10];
        comments = new Comment[10];
//        interactions = new interaction[10];
    }
    public String toString(){
        return "(Post ID: " + postId + "): " + content;
    }
    public int getpostId(){
        return postId;
    }
    public int getlikeNumber(){
        return likeNumber;
    }
    public int getcommentNumber(){
        return commentNumber;
    }
    public int getinteractionNumber(){
        return interactionNumber;
    }
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

/*    public void addInteraction(interaction interact){
        if(interactionNumber==interactions.length){
            interaction[] tempInteractions = new interaction[interactions.length*2];
            for(int i=0;i<interactions.length;++i){
                tempInteractions[i] = interactions[i];
            }
            interactions = tempInteractions;
        }
        interactions[interactionNumber] = interact;
        ++interactionNumber;
        if(interact instanceof like) ++likeNumber;
        else if(interact instanceof comment) ++commentNumber;
    }
*/
    public Like getonelike(int index){
        return likes[index];
    }
    public Comment getonecomment(int index){
        return comments[index];
    }
 
/*    public interaction getoneinteraction(int index){
        return interactions[index];
    }
*/

}