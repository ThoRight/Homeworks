/**
 * @author MuratErbilici
 * @since 03.04.2023
 */


import java.util.*;

public class Account{
    private int accountId;
    private String username;
    private String birthdate;
    private String location;

    private LinkedList<Post> posts;
    private LinkedList<Message> messages;
    private LinkedList<Account> followings;
    private LinkedList<Account> followers;
    private LinkedList<Account> blocks;

    private LinkedList<String> actions;

    private int messageNumber=0;
    private int postNumber=0;
    private int followingNumber=0;
    private int followerNumber=0;
    private int blockNumber=0;
    
    private int loggedId = -1;  // to indicate if there is an account that logged in.
    private boolean isviewingProfile=false; // to prevent viewing post before viewing profile.
    private boolean isviewingPosts=false; // to prevent liking or commenting post before viewing post.

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
        posts = new LinkedList<Post>();
        messages = new LinkedList<Message>();
        followings = new LinkedList<Account>();
        followers = new LinkedList<Account>();
        blocks = new LinkedList<Account>();
        actions = new LinkedList<String>();
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
            posts.add(sharedPost);
            ++postNumber;
            String act = new String("You shared a post. Post id: "+sharedPost.getpostId());
            actions.add("You shared a post. Post id: "+sharedPost.getpostId());
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
        else if(acc.isblocked(this)){
            System.out.printf("You can't send a message because you are blocked by %s.\n", acc.getUsername());
        }
        else if(!isfollowing(acc)){
            System.out.println("You can't send a message to account you dont follow.");
        }
        else if(this.isblocked(acc)){
            System.out.printf("You can't send a message because you have blocked %s.\n", acc.getUsername());
        }
        else{
            messages.add(sentMessage);
            ++messageNumber;
            acc.addMessagetoother(sentMessage);
            String act = new String("You sent a message to "+acc.getUsername());
            actions.add(act);
        }
    }

    /**
     * after sending message, adding same message to receiver's mail.
     * it helps us make two-sided operations.
     */
    public void addMessagetoother(Message sentMessage){
        messages.add(sentMessage);
        ++messageNumber;
    }

    /**
     * checking if we are following the account or not.
     * @param acc the account that we want to check.
     * @return true if we follows, false otherwise.
     */
    public boolean isfollowing(Account acc){
        for(int i=0;i<followingNumber;++i){
            if(followings.get(i) == acc)
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
            if(blocks.get(i) == acc)
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
        else if(acc.isblocked(this)){
            System.out.printf("You can't follow %s because. You are blocked by %s.\n", acc.getUsername(), acc.getUsername());
        }
        else if(isalreadyfollow(acc)){
            System.out.printf("You already followed %s.\n", acc.getUsername());
        }
        else{
            if(this.isblocked(acc)){
                this.unblock(acc);
            }
            followings.add(acc);
            ++followingNumber;
            acc.addFollowerotherside(this);
            String act = new String("You followed "+acc.getUsername());
            actions.add(act);
        }
    }

    public void unfollow(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to follow someone.");
        }
        else{
            int index = followings.indexOf(acc);
            if(index == -1){
                System.out.printf("You already don't follow %s.\n", acc.getUsername());
            }
            else{
                followings.remove(index);
                --followingNumber;
                acc.removeFollowerotherside(this);
                String act = new String("You unfollowed "+acc.getUsername());
                actions.add(act);
            }
        }
    }

    /**
     * after following, it adds the same information to otherside.
     * it help us make two-sided operations.
     */
    public void addFollowerotherside(Account acc){
        followers.add(acc);
        ++followerNumber;
    }

    public void removeFollowerotherside(Account acc){
        followers.remove(followers.indexOf(acc));
        --followerNumber;
    }

    /**
     * make account block other accounts.
     * @param acc the account that we want to block.
     */
    public void block(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to block someone's account");
        }
        else{
            int index = blocks.indexOf(acc);
            if(index != -1){
                System.out.printf("%s is already blocked.\n", acc.getUsername());
            }
            else{
                int index2 = followings.indexOf(acc);
                if(index2 != -1){
                    this.unfollow(acc);
                }
                blocks.add(acc);
                ++blockNumber;
                String act = new String("You blocked "+acc.getUsername());
                actions.add(act);
            }
        }
    }

    public void unblock(Account acc){
        if(!isLogin()){
            System.out.println("You haven't logged in to block someone's account");
        }
        else{
            int index = blocks.indexOf(acc);
            if(index == -1){
                System.out.printf("%s is not blocked already.\n", acc.getUsername());
            }
            else{
                blocks.remove(index);
                --blockNumber;
                String act = new String("You unblocked "+acc.getUsername());
                actions.add(act);
            }
        }
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
        else if(this == acc){
            this.viewownProfile();
        }
        else if(acc.isblocked(this)){
            System.out.printf("You can't view the profile because you are blocked by %s.\n", acc.getUsername());
        }
        else if(this.isblocked(acc)){
            System.out.printf("You can't view the profile because you have blocked %s.\n", acc.getUsername());
        }
        else{
            isviewingProfile = true;
            System.out.printf("Viewing %s's profile...\n-----------------\n", acc.getUsername());
            System.out.println(acc);
            System.out.printf("The followers of %s are : ", acc.getUsername());
            for(int i=0;i<acc.getfollowerNumber();++i){
                System.out.printf("%s, ", acc.followers.get(i).getUsername());
            }
            System.out.printf("\n%s is following: ", acc.getUsername());
            for(int i=0;i<acc.getfollowingNumber();++i){
                System.out.printf("%s, ", acc.followings.get(i).getUsername());
            }
            System.out.printf("\n%s has %d post(s).\n", acc.getUsername(), acc.getpostNumber());
            String act = new String("You viewed "+acc.getUsername()+"'s profile");
            actions.add(act);
        }
    }

    public void viewownProfile(){
        isviewingProfile = true;
        System.out.printf("Viewing own profile...\n-----------------\n");
        System.out.println(this);
        System.out.printf("The followers of you are : ");
        for(int i=0;i<followers.size();++i){
            System.out.printf("%s, ", followers.get(i).getUsername());
        }
        System.out.printf("\nYou are following: ");
        for(int i=0;i<followings.size();++i){
            System.out.printf("%s, ", followings.get(i).getUsername());
        }
        System.out.printf("\nBlocked account(s): ");
        for(int i=0;i<blocks.size();++i){
            System.out.printf("%s, ", blocks.get(i).getUsername());
        }
        System.out.printf("\nYou have %d post(s).\n", getpostNumber());
        String act = new String("You viewed your own profile");
        actions.add(act);
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
     * getter for followingNumber.
     */
    public int getfollowingNumber(){
        return followingNumber;
    }

    /**
     * checks if the account has already followed or not.
     * @param acc the account that we want to check.
     * @return true if the account has already followed, false otherwise.
     */
    public boolean isalreadyfollow(Account acc){
        for(int i=0;i<followingNumber;++i){
            if(this.followings.get(i).getaccountId()==acc.getaccountId())
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
                System.out.printf("%s\n", acc.posts.get(i));
            }
            isviewingPosts = true;
            String act = new String("You viewed "+acc.getUsername()+"'s posts");
            actions.add(act);
        }
    }

    /**
     * to view someone's posts interactions.
     * @param accs[] in order to print information with accounts' usernames.
     * @param target the account that we want to view posts interactions.
     */
    public void viewInteractions(LinkedList<Account> accs, Account target){
        if(!isLogin()){
            System.out.println("You haven't logged in to view interactions.");
        }
        else if(!isviewingPosts){
            System.out.printf("You have to first view %s's posts before view interactions.\n", target.getUsername());
        }
        else{
            System.out.printf("Viewing %s's interactions...\n-------------\n", target.getUsername());
            for(int i=0;i<target.getpostNumber();++i){
                System.out.printf("%s\n", target.posts.get(i));
                if(target.posts.get(i).getlikeNumber()==0){
                    System.out.println("The post has no likes.");
                }
                else{
                    System.out.printf("The post was liked by the following account(s): ");
                    for(int j=0;j<target.posts.get(i).getlikeNumber();++j){
                        System.out.printf("%s, ", findaccname(accs,target.posts.get(i).getonelike(j).getaccountId()));  
                    }
                    System.out.printf("\n");
                }
                if(target.posts.get(i).getcommentNumber()==0){
                    System.out.println("The post has no comments.");
                }
                else{
                    System.out.printf("The post has %d comment(s)...\n", target.posts.get(i).getcommentNumber());
                    for(int j=0;j<target.posts.get(i).getcommentNumber();++j){
                        System.out.printf("Comment %d: '%s' said '%s', ", target.posts.get(i).getcommentNumber(),
                        findaccname(accs,target.posts.get(i).getonecomment(j).getaccountId()), target.posts.get(i).getonecomment(j));  
                    }
                    System.out.printf("\n");
                }
            }
            String act = new String("You viewed "+target.getUsername()+"'s posts interactions");
            actions.add(act);
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
                if(this.messages.get(i).getSenderId()==this.getaccountId()){
                    ++count;
                }
            }
            System.out.printf("Checking outbox.....\n");
            System.out.printf("There is/are %d message(s) in the outbox\n", count);
            String act = new String("You checked your outbox");
            actions.add(act);
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
                if(this.messages.get(i).getReceiverId()==this.getaccountId()){
                    ++count;
                }
            }
            System.out.printf("Checking inbox.....\n");
            System.out.printf("There is/are %d message(s) in the inbox\n", count);
            String act = new String("You checked your inbox");
            actions.add(act);
        }
    }

    /**
     * to view inbox of currently logged account.
     * @param accs[] to print information with accounts' usernames.
     */
    public void viewInbox(LinkedList<Account> accs){
        if(!isLogin()){
            System.out.println("You haven't logged in to view inbox.");
        }
        else{
            System.out.printf("Viewing inbox...\n----------------\n");
            for(int i=0;i<messageNumber;++i){
                if(messages.get(i).getReceiverId()==this.getaccountId()){
                    System.out.printf("Message ID: %d\n", messages.get(i).getMessageId());
                    System.out.printf("From: %s\n", findaccname(accs, messages.get(i).getSenderId()));
                    System.out.printf("To: %s\n", this.getUsername());
                    System.out.printf("Message: %s\n", messages.get(i));
                }
            }
            String act = new String("You viewed your inbox");
            actions.add(act);
        }
    }

    /**
     * to find accounts' usernames in accounts.
     * @param accs the LinkedList that keeps all registered account.
     * @param id the id number that we want to find account's username.
     * @return account's username.
     */
    public String findaccname(LinkedList<Account> accs, int id){
        for(int i=1;i<accs.size();++i){ // starts from 1 because accounts start with index of 1 in accs[].
            if(accs.get(i).getaccountId() == id)
                return accs.get(i).getUsername();
        }
        return "-1";
    }

    /**
     * to view outbox of currently logged account.
     * @param accs[] to print information with accounts' usernames.
     */
    public void viewOutbox(LinkedList<Account> accs){
        if(!isLogin()){
            System.out.println("You haven't logged in to view outbox.");
        }
        else{
            System.out.printf("Viewing outbox...\n----------------\n");
            for(int i=0;i<messages.size();++i){    
                if(messages.get(i).getSenderId()==this.getaccountId()){
                    System.out.printf("Message ID: %d\n", messages.get(i).getMessageId());
                    System.out.printf("From: %s\n", this.getUsername());
                    System.out.printf("To: %s\n", findaccname(accs, messages.get(i).getReceiverId()));
                    System.out.printf("Message: %s\n", messages.get(i));
                }
            }
            String act = new String("You viewed your outbox");
            actions.add(act);
        }  
    }

    /**
     * make account like post.
     * firstly, it checks login situation, then according to existence of post, it adds like object to posts.
     * @param acc the account that has the post.
     * @param l like object.
     */
    public void likePost(Account acc, Like l){
        boolean postExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to like someone's post.");
        }
        else if(!isviewingPosts){
            System.out.printf("You have to first view %s's posts before liking\n", acc.getUsername());
        }
        else{
            for(int i=0;i<acc.getpostNumber();++i){
                if(acc.posts.get(i).getpostId()==l.getpostId()){
                    if(acc.posts.get(i).isalreadyliked(l)){
                        System.out.println("You already liked the post.");
                    }
                    else{
                        postExist = true;
                        acc.posts.get(i).addLike(l);
                        String act = new String("You liked "+acc.getUsername()+"'s post. Post id: "+l.getpostId());
                        actions.add(act);
                        break;
                    }
                }
            }
            if(!postExist){
                System.out.println("Post couldn't be liked because post doesn't exist.");
            }
        }
    }

    public void unlikePost(Account acc, Like l){
        boolean postExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to like someone's post.");
        }
        else if(!isviewingPosts){
            System.out.printf("You have to first view %s's posts\n", acc.getUsername());
        }
        else{
            for(int i=0;i<acc.getpostNumber();++i){
                if(acc.posts.get(i).getpostId()==l.getpostId()){
                    postExist = true;
                    acc.posts.get(i).removeLike(l);
                    String act = new String("You unliked "+acc.getUsername()+"'s post. Post id: "+l.getpostId());
                    actions.add(act);
                    break;
                }
            }
            if(!postExist){
                System.out.println("Post couldn't be unliked because post doesn't exist.");
            }
        }
    }

    /**
     * make account add comment to post.
     * firstly, it checks login situation, then according to existence of post, it adds comment object to posts.
     * @param acc the account that has the post.
     * @param com comment object.
     */
    public void addComment(Account acc, Comment com){
        boolean commentExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to comment to someone's post.");
        }
        else if(!isviewingPosts){
            System.out.printf("You have to first view %s's posts before commenting.\n", acc.getUsername());
        }
        else{
            for(int i=0;i<acc.getpostNumber();++i){
                if(acc.posts.get(i).getpostId()==com.getpostId()){
                    commentExist = true;
                    acc.posts.get(i).addComment(com);
                    String act = new String("You commented "+acc.getUsername()+"'s post. Post id: "+com.getpostId());
                    actions.add(act);
                    break;
                }
            }
            if(!commentExist){
                System.out.println("Post couldn't be commented because post doesn't exist.");
            }
        }
    }

    public void unComment(Account acc, Comment com){
        boolean commentExist=false;
        if(!isLogin()){
            System.out.println("You haven't logged in to uncomment.");
        }
        else if(!isviewingPosts){
            System.out.printf("You have to first view %s's posts before uncommenting.\n", acc.getUsername());
        }
        else{
            for(int i=0;i<acc.getpostNumber();++i){
                if(acc.posts.get(i).getpostId()==com.getpostId()){
                    commentExist = true;
                    acc.posts.get(i).removeComment(com);
                    String act = new String("You uncommented "+acc.getUsername()+"'s post. Post id: "+com.getpostId());
                    actions.add(act);
                    break;
                }
            }
            if(!commentExist){
                System.out.println("Post couldn't be uncommented because post doesn't exist.");
            }
        }
    }

    public void setisviewingPosts(boolean b){
        isviewingPosts = b;
    }

    public void viewHistory(){
        System.out.printf("Viewing History......\n");
        for(int i=0;i<actions.size();++i){
            System.out.printf("%s\n", actions.get(i));
        }
    }

}
