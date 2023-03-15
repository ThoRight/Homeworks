public class Comment extends Interaction{
    String content;
    public Comment(int interactionid, int accountId, int postId, String content){
        super(interactionid, accountId, postId);
        this.content = content;
    }
    public String toString(){
        return content;
    }
}