/**
 * @author MuratErbilici
 * @since 03.04.2023
 * Test Class for Scenario 4.
 */

import java.util.*;

public class TestClass4{
    public static void main(String args[]){
        
        System.out.printf("Starting scenario4...\n\n");
        System.out.printf("Creating Accounts.....\n");
        int viewedPostsid = 0; // it is to indicate which user's post we are looking.
        boolean sameusername = false;
        LDLinkedList<Account> accs = new LDLinkedList<Account>();
        Account test = new Account(-2,"test","test","test"); // to make user id's and indexes same.
        accs.add(test);
        Account loganroy = new Account(1,"LoganRoy1","15.04.1958","Dundee");
        accs.add(loganroy);
        System.out.printf("An account with username %s has been created.\n", accs.get(1).getUsername());
    

    //Because static is forbidden, i check individually wheter there is same username or not.
        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "SamWinchester2"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account samwinchester = new Account(2,"SamWinchester2","10.03.1985","Kansas");
            accs.add(samwinchester);
            System.out.printf("An account with username %s has been created.\n", accs.get(2).getUsername());
        }
        sameusername = false;
        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "DeanWinchester3"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account deanwinchester = new Account(3,"DeanWinchester3","12.06.1981","Lawrence");
            accs.add(deanwinchester);
            System.out.printf("An account with username %s has been created.\n", accs.get(3).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "Walter White4"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account walterwhite = new Account(4,"Walter White4","23.07.1971","Atlanta");
            accs.add(walterwhite);
            System.out.printf("An account with username %s has been created.\n", accs.get(4).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "JessePinkman5"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account jessepinkman = new Account(5,"JessePinkman5","28.12.1988","Albuquerque");
            accs.add(jessepinkman);
            System.out.printf("An account with username %s has been created.\n", accs.get(5).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "ElliotAlderson6"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account elliotalderson = new Account(6,"ElliotAlderson6","06.09.1994","Chicago");
            accs.add(elliotalderson);
            System.out.printf("An account with username %s has been created.\n", accs.get(6).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "SadieDunhill7"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account sadiedunhill = new Account(7,"SadieDunhill7","11.09.1943","Texas");
            accs.add(sadiedunhill);
            System.out.printf("An account with username %s has been created.\n", accs.get(7).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "MichealScott8"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account michealscott = new Account(8,"MichealScott8","12.06.1980","Scranton");
            accs.add(michealscott);
            System.out.printf("An account with username %s has been created.\n", accs.get(8).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "SeverusSnape9"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account severussnape = new Account(9,"SeverusSnape9","09.01.1975","Hogwarts");
            accs.add(severussnape);
            System.out.printf("An account with username %s has been created.\n", accs.get(9).getUsername());
        }
        sameusername = false;

        for(int i=1;i<accs.size();++i){
            if(accs.get(i).getUsername() == "Neo10"){
                sameusername = true;
            }
        }
        if(sameusername){
            System.out.printf("There is an account with same username!\n");
        }
        else{
            Account neo = new Account(10,"Neo10","27.05.1971","Usa");
            accs.add(neo);
            System.out.printf("An account with username %s has been created.\n", accs.get(10).getUsername());
        }
        sameusername = false;

        System.out.println("LoganRoy1 logs in.");
        accs.get(1).login();
        System.out.println(" Tries to send a message to SamWinchester2.");
        Message roymsgtries = new Message(1,1,2,"Hi, How is the family business.");
        accs.get(1).sendMessage(accs.get(2), roymsgtries);
        System.out.println(" Follows SamWinchester2.");
        accs.get(1).follow(accs.get(2));
        System.out.println(" Sends a message SamWinchester2. ('Hi, How is the family business.')");
        Message roymsg1 = new Message(1,1,2,"Hi, How is the family business.");
        accs.get(1).sendMessage(accs.get(2), roymsg1);
        System.out.println(" Shares a post. 'I am gonna win!'");
        Post roypost1 = new Post(1,accs.get(1),"I am gonna win!");
        accs.get(1).sharePost(roypost1);
        System.out.println(" Views history");
        accs.get(1).viewHistory();
        System.out.println(" Blocks WalterWhite4");
        accs.get(1).block(accs.get(4));
        System.out.println(" Shares a post. ('Eventually!')");
        Post roypost2 = new Post(2,accs.get(1),"Eventually!");
        accs.get(1).sharePost(roypost2);
        System.out.println(" Views history");
        accs.get(1).viewHistory();
        System.out.println(" Logs out.");
        accs.get(1).logout();
        
        System.out.println("SadieDunhill7 logs in.");
        accs.get(7).login();
        System.out.println(" Follows SamWinchester2.");
        accs.get(7).follow(accs.get(2));
        System.out.println(" Views SamWinchester2's profile.");
        accs.get(7).viewProfile(accs.get(2));
        System.out.println(" Views own profile.");
        accs.get(7).viewProfile(accs.get(7));
        accs.get(7).setisviewingProfile(false);
        System.out.println(" Unfollows SamWinchester2.");
        accs.get(7).setisviewingProfile(false);
        accs.get(7).unfollow(accs.get(2));
        System.out.println(" Views SamWinchester2's profile.");
        accs.get(7).viewProfile(accs.get(2));
        accs.get(7).setisviewingProfile(false);
        System.out.println(" Views own profile.");
        accs.get(7).viewProfile(accs.get(7));
        accs.get(7).setisviewingProfile(false);
        System.out.println(" Follows LoganRoy1.");
        accs.get(7).follow(accs.get(1));
        System.out.println(" Tries to follow LoganRoy1 again.");
        accs.get(7).follow(accs.get(1));
        System.out.println(" Views LoganRoy1's profile.");
        accs.get(7).viewProfile(accs.get(1));
        System.out.println(" Views LoganRoy1's posts.");
        accs.get(7).viewPosts(accs.get(1));
        viewedPostsid = 1;
        System.out.println(" Likes LoganRoy1's posts.");
        Like sadielike1 = new Like(1,7,1);
        Like sadielike2 = new Like(2,7,2);
        accs.get(7).likePost(accs.get(viewedPostsid), sadielike1);
        accs.get(7).likePost(accs.get(viewedPostsid), sadielike2);
        System.out.println(" Comments LoganRoy1's posts.");
        Comment sadeicomment3 = new Comment(3,7,1,"I don't think so.");
        Comment sadiecomment4 = new Comment(4,7,2,"Never.");
        accs.get(7).addComment(accs.get(viewedPostsid), sadeicomment3);
        accs.get(7).addComment(accs.get(viewedPostsid), sadiecomment4);
        System.out.println(" Views LoganRoy1's posts' interactions.");
        accs.get(7).viewInteractions(accs, accs.get(viewedPostsid));
        System.out.println(" Unlike LoganRoy1's post(id:1)");
        accs.get(7).unlikePost(accs.get(viewedPostsid), sadielike1);
        System.out.println(" Uncomment LoganRoy1's post(id:2)");
        accs.get(7).unComment(accs.get(viewedPostsid), sadiecomment4);
        System.out.println(" Views LoganRoy1's posts' interactions.");
        accs.get(7).viewInteractions(accs, accs.get(viewedPostsid));
        accs.get(7).setisviewingPosts(false);
        accs.get(7).setisviewingProfile(false);
        System.out.println(" Views History.");
        accs.get(7).viewHistory();
        System.out.println(" Logs out.");
        accs.get(7).logout();
        
        System.out.println("WalterWhite4 logs in.");
        accs.get(4).login();
        System.out.println(" Tries to view LoganRoy1's Profile.");
        accs.get(4).viewProfile(accs.get(1));
        System.out.println(" Tries to follow LoganRoy1.");
        accs.get(4).follow(accs.get(1));
        System.out.println(" Tries to send a message to LoganRoy1.");
        Message walterwhitemsg2 = new Message(2,4,1,"Did you block me???");
        accs.get(4).sendMessage(accs.get(1), walterwhitemsg2);
        System.out.println(" Blocks ElliotAlderson6");
        accs.get(4).block(accs.get(6));
        System.out.println(" Tries to view ElliotAlderson6's posts.");
        accs.get(4).viewPosts(accs.get(6));
        System.out.println(" Tries to view ElliotAlderson6's profile.");
        accs.get(4).viewProfile(accs.get(6));
        System.out.println(" Unblocks ElliotAlderson6");
        accs.get(4).unblock(accs.get(6));
        System.out.println(" Views ElliotAlderson6's profile.");
        accs.get(4).viewProfile(accs.get(6));
        System.out.println(" Logs out.");
        accs.get(4).logout();
        System.out.printf("\nEnding Scenario 4.............\n");

/*
        System.out.println("SeverusSnape9 Logs in.");
        accs.get(9).login();
        System.out.println(" Blocks other accounts.");
        for(int i=1;i<11;++i){
            if(i!=9)
                accs.get(9).block(accs.get(i));
        }
        System.out.println(" Views own profile.");
        accs.get(9).viewProfile(accs.get(9));
        System.out.println(" Unblocks other accounts.");
        for(int i=1;i<11;++i){
            if(i!=9)
                accs.get(9).unblock(accs.get(i));
        }
        System.out.println(" Views own profile.");
        accs.get(9).viewProfile(accs.get(9));
        System.out.println(" Views history.");
        accs.get(9).viewHistory();
        System.out.println(" Logs out.");
        accs.get(9).logout();
*/
















    }
}