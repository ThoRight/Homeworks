/**
 * @author MuratErbilici
 * @since 17.03.2023
 */


public class Account{
    private int accountId;
    private String username;
    private String birthdate;
    private String location;

    private Post[] posts;
    private Message[] messages;
    private Account[] followings;
    private Account[] followers;
    private Account[] blocks;

    private int messageNumber=0;
    private int postNumber=0;
    private int followingNumber=0;
    private int followerNumber=0;
    private int blockNumber=0;
    
    private int loggedId = -1;
    private boolean isviewingProfile=false;

    /**
     * Constructor with 4 parameters.
     * @param accId for account id.
     * @param usern for username.
     * @param bd for birthdate.
     * @param loc for location.
     */
    public Account(int accId, String usern, String bd, String loc){
        accountId = accId;
        username = usern;
        birthdate = bd;
        location = loc;
        posts = new Post[10];
        messages = new Message[10];
        followings = new Account[10];
        followers = new Account[10];
        blocks = new Account[10];
    }

    /**
     * for login.
     * When the account logs in, loggedId is account id of the account that logs in currently.
     */
    public void login(){
        this.loggedId = this.accountId;
    }

    /**
     * for logout.
     * First check if someone logged in or not.
     * When the account logs out, loggedId is -1 which means no one logs in currently.
     */
    public void logout(){
        if(loggedId == -1)
            System.out.println("You haven't already logged in.");
        else
            this.loggedId = -1;
    }

    /**
     * checks if someone logs in or not.
     * @return information of someone logs in or not.
     */
    public boolean isLogin(){
        if(this.loggedId == this.accountId)
            return true;
        else
            return false;
    }

    /**
     * make account share post
     * @param sharedPost the post that the account wants to share. 
     */
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

    /**
     * getter for postNumber
     */
    public int getpostNumber(){
        return postNumber;
    }

    /**
     * make account send a message to another account.
     * @param acc the account that we want to send a message.
     * @param sentMessage the message that we want to send.
     */
    public void sendMessage(Account acc, Message sentMessage){
        if(!isLogin()){
            System.out.println("You haven't logged in to send a message.");
        }
        else if(!isfollowing(acc)){
            System.out.println("You can't send a message to account you dont follow.");
        }
        else if(acc.isblocked(this)){
            System.out.printf("You can't send a message because you are blocked by %s.\n", acc.getUsername());
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
        }
    }

    /**
     * after sending message, adding same message to receiver's mail.
     * it helps us make two-sided operations.
     */
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

    /**
     * checking if we are following the account or not.
     * @param acc the account that we want to check.
     * @return true if we follows, false otherwise.
     */
    public boolean isfollowing(Account acc){
        for(int i=0;i<followingNumber;++i){
            if(followings[i] == acc)
                return true;
        }
        return false;
    }

    /**
     * checking if we block the account or not.
     * @param acc the account that we want to check.
     * @return true if we blocks, false otherwise.
     */
    public boolean isblocked(Account acc){
        for(int i=0;i<blockNumber;++i){
            if(blocks[i] == acc)
                return true;
        }
        return false;
    }

    /**
     * make account follow the other acc.
     * @param acc the account that we want to follow.
     */
    public void follow(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to follow someone.");
        }
        else if(isalreadyfollow(acc)){
            System.out.printf("You already followed %s.\n", acc.getUsername());
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

    /**
     * after following, it adds the same information to otherside.
     * it help us make two-sided operations.
     */
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

    /**
     * make account block other accounts.
     * @param acc the account that we want to block.
     */
    public void block(Account acc){
        if(blockNumber == blocks.length){
            Account[] tempBlocks = new Account[blocks.length*2];
            for(int i=0; i<blocks.length;++i){
                tempBlocks[i] = blocks[i];
            }
            blocks = tempBlocks;
        }
        blocks[blockNumber] = acc;
        ++blockNumber;
    }

    @Override
    public String toString(){
        return "UserID: " + accountId + "\nUsername: " + username + "\nLocation: " + location
                + "\nBirth Date: " + birthdate + "\n" + username + " is following " + followingNumber
                + " account(s) " + "and has "+ followerNumber + " follower(s)";
    }

    /**
     * to view to someone's profile.
     * @param acc the account that we want to view.
     */
    public void viewProfile(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to view someone's profile.");
        }
        else if(acc.isblocked(this)){
            System.out.printf("You can't view the profile because you are blocked by %s.\n", acc.getUsername());
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

    /**
     * setter for isviewingProfile.
     */
    public void setisviewingProfile(boolean b){
        isviewingProfile = b;
    }

    /**
     * getter for accountId.
     */
    public int getaccountId(){
        return accountId;
    }

    /**
     * getter for userName.
     */
    public String getUsername(){
        return username;
    }

    /**
     * getter for followerNumber.
     */
    public int getfollowerNumber(){
        return followerNumber;
    }

    /**
     * getter for one element of followers array.
     */
    public Account getonefollower(int index){
        return followers[index];
    }

    /**
     * getter for followingNumber.
     */
    public int getfollowingNumber(){
        return followingNumber;
    }

    /**
     * getter for one element of followings array.
     */
    public Account getonefollowing(int index){
        return followings[index];
    }

    /**
     * getter for one element of posts array.
     */
    public Post getonepost(int index){
        return posts[index];
    }

    /**
     * checks if the account has already followed or not.
     * @param acc the account that we want to check.
     * @return true if the account has already followed, false otherwise.
     */
    public boolean isalreadyfollow(Account acc){
        for(int i=0;i<followingNumber;++i){
            if(this.followings[i].getaccountId()==acc.getaccountId())
                return true;
        }
        return false;
    }

    /**
     * to view someone's posts.
     * @param acc the account that we want to view posts.
     */
    public void viewPosts(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to view Posts.");
        }
        else if(!isviewingProfile){
            System.out.printf("You have to first view %s's profile before viewing posts.\n", acc.getUsername());
        }
        else{
            System.out.printf("Viewing %s's posts...\n", acc.getUsername());
            for(int i=0;i<acc.getpostNumber();++i){
                System.out.printf("%s\n", acc.getonepost(i));
            }
        }
    }

    /**
     * to view someone's posts interactions.
     * @param accs[] in order to print information with accounts' usernames.
     * @param target the account that we want to view posts interactions.
     */
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

    /**
     * to check outbox and print how many messages in there.
     */
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

    /**
     * to check inbox and print how many messages in there.
     */
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

    /**
     * to view inbox of currently logged account.
     * @param accs[] to print information with accounts' usernames.
     */
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

    /**
     * to find accounts' usernames in accounts array.
     * @param accs[] the array that keeps all registered account.
     * @param id the id number that we want to find account's username.
     * @return account's username.
     */
    public String findaccname(Account accs[], int id){
        for(int i=1;i<accs.length;++i){ // starts from 1 because accounts start with index of 1 in accs[].
            if(accs[i].getaccountId() == id)
                return accs[i].getUsername();
        }
        return "-1";
    }

    /**
     * to view outbox of currently logged account.
     * @param accs[] to print information with accounts' usernames.
     */
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

    /**
     * make account like post.
     * firstly, it checks login situation, then according to existence of post, it adds like object to posts array.
     * @param acc the account that has the post.
     * @param l like object.
     */
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

    /**
     * make account add comment to post.
     * firstly, it checks login situation, then according to existence of post, it adds comment object to posts array.
     * @param acc the account that has the post.
     * @param com comment object.
     */
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

}
