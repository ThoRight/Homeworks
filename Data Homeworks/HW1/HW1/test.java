public class test{
    public static void main(String[] args){
        account[] accounts = new account[4];
        accounts[1] = new account("gizemsungu","1.1.1999","Kocaeli");
        accounts[2] = new account("sibelgulmez","10.03.1995","Ankara");
        accounts[3] = new account("gokhankaya","12.11.1994","Çanakkale");
        accounts[1].viewProfile();
        accounts[1].addFollower(accounts[2]);
        accounts[1].viewProfile();
        post paylas = new post(accounts[1], "havalarda soğuk gidiyor");
        accounts[1].sharePost(paylas);
        post paylas2 = new post(accounts[1], "üşürsün sen bilirim");
        accounts[1].sharePost(paylas2);
        accounts[1].viewProfile();
        accounts[1].viewPosts();
        comment comment1 = new comment(accounts[1].getaccountId(), 2, "evet bilirsin");
        accounts[1].addInteraction(comment1);
        System.out.printf("Viewing %s's posts' interactions...\n", accounts[1].getUsername());
        for(int i=0;i<accounts[1].getpostNumber();++i){
            System.out.println(accounts[1].getonepost(i));
            if(accounts[1].getonepost(i).getlikeNumber()==0)
                System.out.printf("The post has no likes.\n");
            else{
                System.out.printf("The post was liked by the following account(s):");
                for(int j=0;j<accounts[1].getonepost(i).getinteractionNumber();++j){
                    if(accounts[1].getonepost(i).getoneinteraction(i) instanceof like)
                        System.out.printf("%s, ", accounts[1].getUsername());
                }
            }
            if(accounts[1].getonepost(i).getcommentNumber()==0)
                System.out.printf("The post has no comments.\n");
            else{
                System.out.printf("The post has %d comment(s)...\n", accounts[1].getonepost(i).getcommentNumber());
                for(int j=0;j<accounts[1].getonepost(i).getinteractionNumber();++j){
                    if(accounts[1].getonepost(i).getoneinteraction(j) instanceof comment)
                    System.out.printf("Comment %d: '%s' said '%s'\n", j+1, accounts[accounts[1].getonepost(i).getoneinteraction(j).getaccountId()].getUsername()
                    , accounts[1].getonepost(i).getoneinteraction(j));
                }
            }
            

            
        }





/*        for(int i=0;i<accounts[1].getpostNumber();++i){
            System.out.println(accounts[1].getonepost(i));
            if(accounts[1].getonepost(i).getlikeNumber() != 0){
                System.out.printf("The post was liked by the following account(s):");
                for(int j=0;j<accounts[1].getonepost(i).getlikeNumber();++j){
                    System.out.printf("%s, ", accounts[accounts[1].getonepost(i).getonelike(j).getaccountId()].getUsername());
                }
            }
            else{
                System.out.printf("The post has no likes.\n");
            }
            if(accounts[1].getonepost(i).getcommentNumber() != 0){
                System.out.printf("The post has %d comment(s)...\n", accounts[1].getonepost(i).getcommentNumber());
                for(int j=0;j<accounts[1].getonepost(i).getcommentNumber();++j){
                    System.out.printf("Comment %d: '%s' said '%s'\n", j+1, accounts[accounts[1].getonepost(i).getonecomment(j).getaccountId()].getUsername()
                    , accounts[1].getonepost(i).getonecomment(j));
                }
            }
            else{
                System.out.printf("The post has no comments.\n");
            }
            
        } */
        

    }
}