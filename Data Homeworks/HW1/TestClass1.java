public class TestClass1{
    public static void main(String[] args){
        System.out.printf("Starting scenario1...\n\n");
        System.out.printf("Creating Accounts.....\n");
        int viewedPostsid = 0;
        Account[] accs = new Account[4];
        
        accs[1] = new Account(1,"gizemsungu","13.01.1993","Kocaeli");
        System.out.printf("An account with username %s has been created.\n", accs[1].getUsername());

        accs[2] = new Account(2,"sibelgulmez","10.03.1995","Istanbul");
        System.out.printf("An account with username %s has been created.\n", accs[2].getUsername());


        accs[3] = new Account(3,"gokhankaya","12.06.1990","Gebze");
        System.out.printf("An account with username %s has been created.\n", accs[3].getUsername());


        System.out.printf("Logging into an account (username: %s)\n", accs[2].getUsername());
        accs[2].login();
        Post post1 = new Post(1,accs[2],"I like Java.");
        Post post2 = new Post(2,accs[2],"Java the coffee.");
        System.out.printf("Sharing two posts...\n");
        accs[2].sharePost(post1);
        accs[2].sharePost(post2);
        System.out.printf("Following %s and %s\n", accs[1].getUsername(), accs[3].getUsername());
        accs[2].follow(accs[1]);
        accs[2].follow(accs[3]);
        System.out.printf("Logging out from account '%s'\n", accs[2].getUsername());
        accs[2].logout();

        System.out.printf("Logging into another account (username: %s)\n", accs[3].getUsername());
        accs[3].login();
        accs[3].viewProfile(accs[2]);
        accs[3].viewPosts(accs[2]);
        viewedPostsid = 2;
        accs[3].setisviewingProfile(false);
        Like like1 = new Like(1,3,1);
        Comment comment1 = new Comment(1,3,1,"me too!");
        System.out.printf("Liking a post of %s\n", accs[viewedPostsid].getUsername());
        accs[3].likePost(accs[viewedPostsid],like1);
        System.out.printf("Adding a comment on a post of %s\n", accs[viewedPostsid].getUsername());
        accs[3].addComment(accs[viewedPostsid],comment1);

        System.out.printf("Following %s and %s\n", accs[1].getUsername(), accs[2].getUsername());
        accs[3].follow(accs[1]);
        accs[3].follow(accs[2]);
        Message message1 = new Message(1,3,1,"This homework is too easy!");
        System.out.printf("Sending a message to %s\n", accs[message1.getReceiverId()].getUsername());
        accs[3].sendMessage(accs[message1.getReceiverId()],message1);
        System.out.printf("Logging out from account '%s'\n", accs[3].getUsername());
        accs[3].logout();

        System.out.printf("Logging into another account (username: %s)\n", accs[1].getUsername());
        accs[1].login();
        accs[1].checkOutbox();
        accs[1].checkInbox();
        accs[1].viewInbox(accs);
        accs[1].viewProfile(accs[2]);
        accs[1].viewPosts(accs[2]);
        accs[1].viewInteractions(accs, accs[2]);
        viewedPostsid = 2;
        System.out.printf("Liking %s's posts...\n", accs[viewedPostsid].getUsername());
        Like like2 = new Like(2,1,1);
        Like like3 = new Like(3,1,2);
        accs[1].likePost(accs[viewedPostsid],like2);
        accs[1].likePost(accs[viewedPostsid],like3);
        accs[1].viewInteractions(accs, accs[2]);
        System.out.printf("Ending scenario1...\n\n");

    }
}