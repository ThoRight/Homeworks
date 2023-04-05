/**
 * @author MuratErbilici
 * @since 03.04.2023
 * Test Class for Scenario 3.
 */

import java.util.*;

public class TestClass3{
    public static void main(String[] args){
        System.out.printf("Starting scenario1...\n\n");
        System.out.printf("Creating Accounts.....\n");
        int viewedPostsid = 0; // it is to indicate which user's post we are looking.
        boolean sameusername = false;
        LinkedList<Account> accs = new LinkedList<Account>();
        Account test = new Account(-2,"test","test","test");
        accs.add(test);
        Account gizemsungu = new Account(1,"gizemsungu","13.01.1993","Kocaeli");
        accs.add(gizemsungu);
        System.out.printf("An account with username %s has been created.\n", accs.get(1).getUsername());
    

    //Because static is forbidden, i check individually wheter there is same username or not.
        for(int i=1;i<2;++i){
            if(accs.get(i).getUsername() == "sibelgulmez"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account sibelgulmez = new Account(2,"sibelgulmez","10.03.1995","Istanbul");
            accs.add(sibelgulmez);
            System.out.printf("An account with username %s has been created.\n", accs.get(2).getUsername());
        }
        sameusername = false;
        for(int i=1;i<3;++i){
            if(accs.get(i).getUsername() == "gokhankaya"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account gokhankaya = new Account(3,"gokhankaya","12.06.1990","Gebze");
            accs.add(gokhankaya);
            System.out.printf("An account with username %s has been created.\n", accs.get(3).getUsername());
        }
        sameusername = false;


        System.out.printf("Logging into an account (username: %s)\n", accs.get(2).getUsername());
        accs.get(2).login();
        Post post1 = new Post(1,accs.get(2),"I like Java.");
        Post post2 = new Post(2,accs.get(2),"Java the coffee.");
        System.out.printf("Sharing two posts...\n");
        accs.get(2).sharePost(post1);
        accs.get(2).sharePost(post2);
        System.out.printf("Following %s and %s\n", accs.get(1).getUsername(), accs.get(3).getUsername());
        accs.get(2).follow(accs.get(1));
        accs.get(2).follow(accs.get(3));
        System.out.printf("Logging out from account '%s'\n", accs.get(2).getUsername());
        accs.get(2).logout();

        System.out.printf("Logging into another account (username: %s)\n", accs.get(3).getUsername());
        accs.get(3).login();
        accs.get(3).viewProfile(accs.get(2));
        accs.get(3).viewPosts(accs.get(2));
        viewedPostsid = 2;
        Like like1 = new Like(1,3,1);
        Comment comment1 = new Comment(1,3,1,"me too!");
        System.out.printf("Liking a post of %s\n", accs.get(viewedPostsid).getUsername());
        accs.get(3).likePost(accs.get(viewedPostsid),like1);
        System.out.printf("Adding a comment on a post of %s\n", accs.get(viewedPostsid).getUsername());
        accs.get(3).addComment(accs.get(viewedPostsid),comment1);
        accs.get(3).setisviewingPosts(false);
        accs.get(3).setisviewingProfile(false);

        System.out.printf("Following %s and %s\n", accs.get(1).getUsername(), accs.get(2).getUsername());
        accs.get(3).follow(accs.get(1));
        accs.get(3).follow(accs.get(2));
        Message message1 = new Message(1,3,1,"This homework is too easy!");
        System.out.printf("Sending a message to %s\n", accs.get(message1.getReceiverId()).getUsername());
        accs.get(3).sendMessage(accs.get(message1.getReceiverId()),message1);
        System.out.printf("Logging out from account '%s'\n", accs.get(3).getUsername());
        accs.get(3).logout();

        System.out.printf("Logging into another account (username: %s)\n", accs.get(1).getUsername());
        accs.get(1).login();
        accs.get(1).checkOutbox();
        accs.get(1).checkInbox();
        accs.get(1).viewInbox(accs);
        accs.get(1).viewProfile(accs.get(2));
        accs.get(1).viewPosts(accs.get(2));
        accs.get(1).viewInteractions(accs, accs.get(2));
        viewedPostsid = 2;
        System.out.printf("Liking %s's posts...\n", accs.get(viewedPostsid).getUsername());
        Like like2 = new Like(2,1,1);
        Like like3 = new Like(3,1,2);
        accs.get(1).likePost(accs.get(viewedPostsid),like2);
        accs.get(1).likePost(accs.get(viewedPostsid),like3);
        accs.get(1).viewInteractions(accs, accs.get(2));
        accs.get(1).setisviewingPosts(false);
        accs.get(1).setisviewingProfile(false);
        accs.get(1).logout();
        
        System.out.printf("Ending scenario1...\n\n----------\n\n\n\n\n");

        System.out.printf("Starting scenario3.........\n\n");
        System.out.printf("%s logs in.\n", accs.get(1).getUsername());          // gizemsungu
        accs.get(1).login();
        System.out.printf("blocks %s\n", accs.get(2).getUsername());            // blocks sibelgulmez
        accs.get(1).block(accs.get(2));
        System.out.printf("logs out.\n");
        accs.get(1).logout();
        System.out.printf("%s logs in.\n", accs.get(2).getUsername());          // sibelgulmez
        accs.get(2).login();
        System.out.printf("Tries to view the profile of '%s'\n", accs.get(1).getUsername());
        accs.get(2).viewProfile(accs.get(1));
        accs.get(2).setisviewingProfile(false);
        Message message3 = new Message(3,2,1,"Hey what's up?");
        System.out.printf("Tries to send a message to '%s'\n", accs.get(1).getUsername());
        accs.get(2).sendMessage(accs.get(1),message3);
    }
}
