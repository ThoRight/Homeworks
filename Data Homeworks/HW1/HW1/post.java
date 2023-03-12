public class post{
    private static int postidnum=1;
    private int postId;
    private int accountId;
//    private like[] likes;
//    private comment[] comments;
    private interaction[] interactions;
    private String content;
    private int likeNumber=0;
    private int commentNumber=0;
    private int interactionNumber=0;

    public post(account acc, String content){
        this.postId = postidnum;
        this.accountId = acc.getaccountId();
        this.content = content;
//        likes = new like[10];
//        comments = new comment[10];
        interactions = new interaction[10];
        ++postidnum;
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
/*    public void addLike(like l){
        if(likeNumber==likes.length){
            like[] tempLikes = new like[likes.length*2];
            for(int i=0;i<likes.length;++i){
                tempLikes[i] = likes[i];
            }
            likes = tempLikes;
        }
        likes[likeNumber] =  l;
        ++likeNumber;
    }
    public void addComment(comment com){
        if(commentNumber==comments.length){
            comment[] tempComments = new comment[comments.length*2];
            for(int i=0;i<comments.length;++i){
                tempComments[i] = comments[i];
            }
            comments = tempComments;
        }
        comments[commentNumber] =  com;
        ++commentNumber;
    } */

    public void addInteraction(interaction interact){
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
/*    public like getonelike(int index){
        return likes[index];
    }
    public comment getonecomment(int index){
        return comments[index];
    }*/
    public interaction getoneinteraction(int index){
        return interactions[index];
    }

}