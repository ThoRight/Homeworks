/**
 * @author MuratErbilici
 * @since 13.04.2023
 */

import java.util.Stack;

public class TestClass{
    public static void main(String[] args){
        Username u = new Username();
        Password1 pw1 = new Password1();
        Password2 pw2 = new Password2();

        String [] usernames = new String[16];
        String [] password1s = new String[16];
        int [] password2s= new int[16];

        int [] denominations = {4,17,29};
        System.out.printf("Denominations: ");
        for(int i=0;i<denominations.length;++i){
        System.out.printf("%d, ", denominations[i]);
        }
        System.out.printf("\n");

        usernames[0] = "sibelgulmez";
        password1s[0] = "{[(ecarcar)]}";  // racecar (palindrome).
        password2s[0] = 74;

        usernames[1] = "";
        password1s[1] = "[rac()ecar]";
        password2s[1] = 74;

        usernames[2] = "sibel1";
        password1s[2] = "[rac()ecar]";
        password2s[2] = 74;

        usernames[3] = "sibel";
        password1s[3] = "pass[]";
        password2s[3] = 74;

        usernames[4] = "sibel";
        password1s[4] = "abcdabcd";
        password2s[4] = 74;

        usernames[5] = "sibel";
        password1s[5] = "[[[[]]]]";
        password2s[5] = 74;

        usernames[6] = "sibel";
        password1s[6] = "[no](no)";
        password2s[6] = 74;

        usernames[7] = "sibel";
        password1s[7] = "[rac()ecar]]";
        password2s[7] = 74;

        usernames[8] = "sibel";
        password1s[8] = "[rac()ecars]";
        password2s[8] = 74;

        usernames[9] = "sibel";
        password1s[9] = "[rac()ecar]";
        password2s[9] = 5;

        usernames[10] = "sibel";
        password1s[10] = "[rac()ecar]";
        password2s[10] = 35;

        usernames[11] = "murat";
        password1s[11] = "4[wsl]()a";
        password2s[11] = 37;

        usernames[12] = "murat";
        password1s[12] = "qw[(er)]t";
        password2s[12] = 92;

        usernames[13] = "murat";
        password1s[13] = "qwerttrewq";
        password2s[13] = 33;

        usernames[14] = "murat";
        password1s[14] = "((x){r}(r)[c])c"; // crxrc (palindrome).
        password2s[14] = 38;

        usernames[15] = "**murat**";
        password1s[15] = "qe(we)";
        password2s[15] = 35;
        
        for(int i=0;i<usernames.length;++i){
            System.out.printf("\nUsername: '%s', Password1: '%s', Password2: '%d'\n", usernames[i], password1s[i], password2s[i]);
            if(!u.checkIfValidUsername(usernames[i],usernames[i].length()-1))
                ;
            else if(!pw1.checkpassword1(password1s[i]))
                ;
            else if(!u.containsUserNameSpirit(usernames[i],password1s[i]))
                ;
            else if(!pw1.isBalancedPassword(password1s[i]))
                ;
            else if(!pw1.isPalindromePossible(password1s[i],0,password1s[i].length()-1,1,false))
                ;
            else if(!pw2.checkpassword2(password2s[i]))
                ;
            else if(!pw2.isExactDivision(password2s[i],denominations,0)){
                System.out.println("The password2 is invalid. It is not compatible with the denominations.");
                // Print is not in recursive function because two functions is returned in recursive case. We don't want there is more than one prints.
            }
            else{
                System.out.println("The username and passwords are valid. The door is opening, please wait..");
            }
        }

    }
}