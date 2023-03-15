
public class Account{
    private int accountId;
    private String username;
    private String birthdate;
    private String location;

    private Post[] posts;
    private Message[] messages;
    private Account[] followings;
    private Account[] followers;
  
    private int messageNumber=0;
    private int postNumber=0;
    private int followingNumber=0;
    private int followerNumber=0;
    
    int loggedId = -1;
    boolean isviewingProfile=false;

    public Account(int accId, String usern, String bd, String loc){
        accountId = accId;
        username = usern;
        birthdate = bd;
        location = loc;
        posts = new Post[10];
        messages = new Message[10];
        followings = new Account[10];
        followers = new Account[10];
    }

    public void login(){
        this.loggedId = this.accountId;
    }
    public void logout(){
        if(loggedId == -1)
            System.out.println("You haven't already logged in.");
        else
            this.loggedId = -1;
    }
    public boolean isLogin(){
        if(this.loggedId == this.accountId)
            return true;
        else
            return false;
    }

    public void sharePost(Post sharedPost){
        if(!isLogin()){
            System.out.println("You haven't logged in to share post.");
        }
        else{

        if(postNumber == posts.length){
            Post[] tempPosts = new Post[posts.length*2];
            for(int i=0; i<posts.length;++i){
                tempPosts[i] = posts[i];
            }
            posts = tempPosts;
        }

        posts[postNumber] = sharedPost;
        ++postNumber;

        }

    }

    public int getpostNumber(){
        return postNumber;
    }

    public boolean sendMessage(Account acc, Message sentMessage){
        if(!isLogin()){
            System.out.println("You haven't logged in to send message.");
            return false;
        }
        else{

        if(messageNumber == messages.length){
            Message[] tempMessages = new Message[messages.length*2];
            for(int i=0; i<messages.length;++i){
                tempMessages[i] = messages[i];
            }
            messages = tempMessages;
        }

        messages[messageNumber] = sentMessage;
        ++messageNumber;
        acc.addMessagetoother(sentMessage);
        return true;

        }
    }
    public void addMessagetoother(Message sentMessage){
        if(messageNumber == messages.length){
            Message[] tempMessages = new Message[messages.length*2];
            for(int i=0; i<messages.length;++i){
                tempMessages[i] = messages[i];
            }
            messages = tempMessages;
        }

        messages[messageNumber] = sentMessage;
        ++messageNumber;
    }

    public void takeMessage(Message sentMessage){
        if(messageNumber == messages.length){
            Message[] tempMessages = new Message[messages.length*2];
            for(int i=0; i<messages.length;++i){
                tempMessages[i] = messages[i];
            }
            messages = tempMessages;
        }

        messages[messageNumber] = sentMessage;
        ++messageNumber;
    }

    public void follow(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to follow someone.");
        }
        else if(isalreadyfollow(acc)){
            System.out.println("You already followed the account.");
        }
        else{

        if(followingNumber == followings.length){
            Account[] tempFollowings = new Account[followings.length*2];
            for(int i=0; i<followings.length;++i){
                tempFollowings[i] = followings[i];
            }
            followings = tempFollowings;
        }
        followings[followingNumber] = acc;
        ++followingNumber;
        acc.addFollowerotherside(this);

        }
    }

    public void addFollowerotherside(Account acc){
        if(followerNumber == followers.length){
            Account[] tempFollowers = new Account[followers.length*2];
            for(int i=0; i<followers.length;++i){
                tempFollowers[i] = followers[i];
            }
            followers = tempFollowers;
        }
        followers[followerNumber] = acc;
        ++followerNumber;
    }

    public String toString(){
        return "UserID: " + accountId + "\nUsername: " + username + "\nLocation: " + location
                + "\nBirth Date: " + birthdate + "\n" + username + " is following " + followingNumber
                + " account(s) " + "and has "+ followerNumber + " follower(s)";
    }

    public void viewProfile(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to view someone's profile.");
        }
        else{
        isviewingProfile = true;
        System.out.printf("Viewing %s's profile...\n-----------------\n", acc.getUsername());
        System.out.println(acc);
        System.out.printf("The followers of %s are : ", acc.getUsername());
        for(int i=0;i<acc.getfollowerNumber();++i){
            System.out.printf("%s, ", acc.getonefollower(i).getUsername());
        }
        System.out.printf("\n%s is following: ", acc.getUsername());
        for(int i=0;i<acc.getfollowingNumber();++i){
            System.out.printf("%s, ", acc.getonefollowing(i).getUsername());
        }
        System.out.printf("\n%s has %d post(s).\n", acc.getUsername(), acc.getpostNumber());

        }
    }
    public void setisviewingProfile(boolean b){
        isviewingProfile = b;
    }

    public int getaccountId(){
        return accountId;
    }

    public String getUsername(){
        return username;
    }
    public int getfollowerNumber(){
        return followerNumber;
    }
    public Account getonefollower(int index){
        return followers[index];
    }
    public int getfollowingNumber(){
        return followingNumber;
    }
    public Account getonefollowing(int index){
        return followings[index];
    }
    public Post getonepost(int index){
        return posts[index];
    }
    public boolean isalreadyfollow(Account acc){
        for(int i=0;i<followingNumber;++i){
            if(this.followings[i].getaccountId()==acc.getaccountId())
                return true;
        }
        return false;
    }

    public void viewPosts(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to view someone's Posts.");
        }
        else if(!isviewingProfile){
            System.out.println("You have to first view profile before viewing posts.");
        }
        else{
        System.out.printf("Viewing %s's posts...\n", acc.getUsername());
        for(int i=0;i<acc.getpostNumber();++i){
            System.out.printf("%s\n", acc.getonepost(i));
        }

        }
    }

    public void viewInteractions(Account accs[], Account target){
        if(!isLogin()){
            System.out.println("You haven't logged in to view interactions.");
        }
        else{
        
        System.out.printf("Viewing %s's interactions...\n-------------\n", target.getUsername());
        for(int i=0;i<target.getpostNumber();++i){
            System.out.printf("%s\n", target.posts[i]);
            if(target.posts[i].getlikeNumber()==0){
                System.out.println("The post has no likes.");
            }
            else{
                System.out.printf("The post was liked by the following account(s): ");
                for(int j=0;j<target.posts[i].getlikeNumber();++j){
                    System.out.printf("%s, ", findaccname(accs,target.posts[i].getonelike(j).getaccountId()));  
                }
                System.out.printf("\n");
            }
            if(target.posts[i].getcommentNumber()==0){
                System.out.println("The post has no comments.");
            }
            else{
                System.out.printf("The post has %d comment(s)...\n", target.posts[i].getcommentNumber());
                for(int j=0;j<target.posts[i].getcommentNumber();++j){
                    System.out.printf("Comment %d: '%s' said '%s', ", target.posts[i].getcommentNumber(),
                    findaccname(accs,target.posts[i].getonecomment(j).getaccountId()), target.posts[i].getonecomment(j));  
                }
                System.out.printf("\n");
            }

        }

        }
    }
/*    public void incpostNumber(){
        ++this.postNumber; 
    } */

    public void checkOutbox(){
        if(!isLogin()){
            System.out.println("You haven't logged in to check outbox.");
        }
        else{

        int count=0;
        for(int i=0;i<messageNumber;++i){
            if(this.messages[i].getSenderId()==this.getaccountId()){
                ++count;
            }
        }
        System.out.printf("Checking outbox.....\n");
        System.out.printf("There is/are %d message(s) in the outbox\n", count);

        }
        
    }

    public void checkInbox(){
        if(!isLogin()){
            System.out.println("You haven't logged in to check inbox.");
        }
        else{

        int count=0;
        for(int i=0;i<messageNumber;++i){
            if(this.messages[i].getReceiverId()==this.getaccountId()){
                ++count;
            }
        }
        System.out.printf("Checking inbox.....\n");
        System.out.printf("There is/are %d message(s) in the inbox\n", count);

        }
        
    }

    public void viewInbox(Account accs[]){
        if(!isLogin()){
            System.out.println("You haven't logged in to view inbox.");
        }
        else{
        
        System.out.printf("Viewing inbox...\n----------------\n");
        for(int i=0;i<messageNumber;++i){
            if(messages[i].getReceiverId()==this.getaccountId()){
                System.out.printf("Message ID: %d\n", messages[i].getMessageId());
                System.out.printf("From: %s\n", findaccname(accs, messages[i].getSenderId()));
                System.out.printf("To: %s\n", this.getUsername());
                System.out.printf("Message: %s\n", messages[i]);
            }
        }

        }
        
    }
    public String findaccname(Account accs[], int id){
        for(int i=1;i<accs.length;++i){ // starts from 1 because accounts start with index of 1 in accs[].
            if(accs[i].getaccountId() == id)
                return accs[i].getUsername();
        }
        return "-1";
    }

    public void viewOutbox(Account accs[]){
        if(!isLogin()){
            System.out.println("You haven't logged in to view outbox.");
        }
        else{

        System.out.printf("Viewing outbox...\n----------------\n");
        for(int i=0;i<messages.length;++i){    
            if(messages[i].getSenderId()==this.getaccountId()){
                System.out.printf("Message ID: %d\n", messages[i].getMessageId());
                System.out.printf("From: %s\n", this.getUsername());
                System.out.printf("To: %s\n", findaccname(accs, messages[i].getReceiverId()));
                System.out.printf("Message: %s\n", messages[i]);
            }
        }

        }
        
    }

    public void likePost(Account acc, Like l){
        boolean postExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to like someone's post.");
        }
        else{

        for(int i=0;i<acc.getpostNumber();++i){
            if(acc.getonepost(i).getpostId()==l.getpostId()){
                postExist = true;
                acc.posts[i].addLike(l);
                break;
            }
        }
        if(!postExist){
            System.out.println("Post couldn't be liked because post doesn't exist.");
        }

        }
    }
    public void addComment(Account acc, Comment com){
        boolean commentExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to comment to someone's post.");
        }
        else{

        for(int i=0;i<acc.getpostNumber();++i){
            if(acc.getonepost(i).getpostId()==com.getpostId()){
                commentExist = true;
                acc.posts[i].addComment(com);
                break;
            }
        }
        if(!commentExist){
            System.out.println("Post couldn't be commented because post doesn't exist.");
        }

        }
    } 

/*    public void addInteraction(interaction interact){
        for(int i=0;i<postNumber;++i){
            if(posts[i].getpostId()==interact.getpostId()){
                posts[i].addInteraction(interact);
                break;
            }
        }
    }
*/

}
