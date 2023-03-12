public class comment extends interaction{
    String content;
    public comment(int accountId, int postId, String content){
        super(accountId, postId);
        this.content = content;
    }
    public String toString(){
        return content;
    }
}