
public class account{
    private static int accountNumber=1;
    private int accountId;
    private String username;
    private String birthdate;
    private String location;
    private post[] posts;
    private message[] messages;
    private account[] followings;
    private account[] followers;
  
    private int messageNumber=0;
    private int postNumber=0;
    private int followingNumber=0;
    private int followerNumber=0;
    

    public account(String usern, String bd, String loc){
        accountId = accountNumber;
        ++accountNumber;

        username = usern;
        birthdate = bd;
        location = loc;
        posts = new post[10];
        messages = new message[10];
        followings = new account[10];
        followers = new account[10];
    }

    public void sharePost(post sharedPost){
        if(postNumber == posts.length){
            post[] tempPosts = new post[posts.length*2];
            for(int i=0; i<posts.length;++i){
                tempPosts[i] = posts[i];
            }
            posts = tempPosts;
        }

        posts[postNumber] = sharedPost;
        ++postNumber;

    }

    public int getpostNumber(){
        return postNumber;
    }

    public void sendMessage(message sentMessage){
        if(messageNumber == messages.length){
            message[] tempMessages = new message[messages.length*2];
            for(int i=0; i<messages.length;++i){
                tempMessages[i] = messages[i];
            }
            messages = tempMessages;
        }

        messages[messageNumber] = sentMessage;
        ++messageNumber;
    }

    public void addFollowing(account acc){
        if(followingNumber == followings.length){
            account[] tempFollowings = new account[followings.length*2];
            for(int i=0; i<followings.length;++i){
                tempFollowings[i] = followings[i];
            }
            followings = tempFollowings;
        }

        followings[followingNumber] = acc;
        ++followingNumber;
    }

    public void addFollower(account acc){
        if(followerNumber == followers.length){
            account[] tempFollowers = new account[followers.length*2];
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

    public void viewProfile(){
        System.out.println(this);
        System.out.printf("The followers of %s are : ", this.getUsername());
        for(int i=0;i<followerNumber;++i){
            System.out.printf("%s, ", followers[i].getUsername());
        }
        System.out.printf("\n%s is following: ", this.getUsername());
        for(int i=0;i<followingNumber;++i){
            System.out.printf("%s, ", followings[i].getUsername());
        }
        System.out.printf("\n%s has %d post(s).\n", this.getUsername(), this.postNumber);
    }

    public int getaccountId(){
        return accountId;
    }

    public String getUsername(){
        return username;
    }
    
    public void viewPosts(){
        System.out.printf("Viewing %s's posts...\n", this.getUsername());
        for(int i=0;i<postNumber;++i){
            System.out.printf("%s\n", this.posts[i]);
        }
    }

    public void viewInteractions(){
        for(int i=0;i<postNumber;++i){
            System.out.printf("%s\n", this.posts[i]);
            System.out.printf("The post was liked by the following account(s): ");
            for(int j=0;j<posts[i].getlikeNumber();++j){
                
            }
        }
    }
/*    public void incpostNumber(){
        ++this.postNumber; 
    } */

    public void checkOutbox(){
        int count=0;
        for(int i=0;i<messageNumber;++i){
            if(this.messages[i].getSenderId()==this.getaccountId()){
                ++count;
            }
        }
        System.out.printf("Checking outbox.....\n");
        System.out.printf("There is/are %d message(s) in the outbox\n", count);
        
    }

    public void checkInbox(){
        int count=0;
        for(int i=0;i<messageNumber;++i){
            if(this.messages[i].getReceiverId()==this.getaccountId()){
                ++count;
            }
        }
        System.out.printf("Checking inbox.....\n");
        System.out.printf("There is/are %d message(s) in the inbox\n", count);
        
    }

    public void viewInbox(int msgNum, String senderName){
        if(messages[msgNum].getReceiverId()==this.getaccountId()){
            System.out.printf("Message ID: %d\n", messages[msgNum].getMessageId());
            System.out.printf("From: %s\n", senderName);
            System.out.printf("To: %s\n", this.getUsername());
            System.out.printf("Message: %s\n", messages[msgNum]);
        }
        
    }

    public void viewOutbox(int msgNum, String receiverName){
        if(messages[msgNum].getSenderId()==this.getaccountId()){
            System.out.printf("Message ID: %d\n", messages[msgNum].getMessageId());
            System.out.printf("From: %s\n", this.getUsername());
            System.out.printf("To: %s\n", receiverName);
            System.out.printf("Message: %s\n", messages[msgNum]);
        }
        
    }

/*    public void likePost(like l, int postId){
        for(int i=0;i<postNumber;++i){
            if(posts[i].getpostId()==postId){
                posts[i].addLike(l);
                break;
            }
        }
    }
    public void addComment(comment com, int postId){
        for(int i=0;i<postNumber;++i){
            if(posts[i].getpostId()==postId){
                posts[i].addComment(com);
                break;
            }
        }
    } */

    public void addInteraction(interaction interact){
        for(int i=0;i<postNumber;++i){
            if(posts[i].getpostId()==interact.getpostId()){
                posts[i].addInteraction(interact);
                break;
            }
        }
    }
    public post getonepost(int index){
        return posts[index];
    }

}
