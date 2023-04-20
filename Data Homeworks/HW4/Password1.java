/**
 * @author MuratErbilici
 * @since 13.04.2023
 */


import java.util.Stack;

public class Password1{

    /**
     * Function that checks if brackets are balanced or not by using stack.
     * @param password1 the password1 that we want to check.
     * @return true if it is balanced, false otherwise.
     */
    public boolean isBalancedPassword(String password1){
        int bracketcount = 0;
        boolean lettersexist = false;
        Stack<Character> pw1stack = new Stack<Character>();
        Stack<Character> controlstack = new Stack<Character>();
        for(int i=password1.length()-1;i>=0;--i){ 
            if(isparanthesis(password1,i)){          // add only brackets to stack.
                pw1stack.push(password1.charAt(i));
            }
        }

        char openbracket;
        char closebracket;
        while(!pw1stack.isEmpty()){
            ++bracketcount;
            if(isopen(pw1stack.peek())){
                controlstack.push(pw1stack.peek());
            }
            else if(isclose(pw1stack.peek())){
                if(!controlstack.isEmpty()){
                    openbracket = controlstack.pop();
                    closebracket = pw1stack.peek();
                    if(!ismatch(openbracket, closebracket)){
                        System.out.println("The password1 is invalid. It should be balanced.");
                        return false;
                    }
                }
                else{
                    System.out.println("The password1 is invalid. It should be balanced.");
                    return false;
                }
            }
            pw1stack.pop();
        }
        if(bracketcount<2){
            System.out.println("The password1 is invalid. It should have at least 2 brackets.");
            return false;
        }
        else if(!controlstack.isEmpty()){
            System.out.println("The password1 is invalid. It should be balanced.");
            return false;
        }
        else 
            return true;


    }
    private boolean isparanthesis(String pw, int index){
        String brackets = new String("{[()]}");
        for(int i=0;i<6;++i){
            if(pw.charAt(index) == brackets.charAt(i))
                return true;
        }
        return false;
    }
    private boolean isopen(char c){
        return c =='{' || c == '[' || c == '(';
    }
    private boolean isclose(char c){
        return c == '}' || c == ']' || c == ')';
    }
    private boolean ismatch(char a, char b){
        if(a == '{' && b == '}') return true;
        else if (a == '[' && b == ']') return true;
        else if(a == '(' && b == ')') return true;
        return false;
    }

    /**
     * Recursive function to check if password1 is palindrome.
     * @param password1 the password1 that we want to check.
     * @param start to determine the current location from start.
     * @param end to determine the current location from end.
     * @param change while replacing characters, to determine if we check all possible replacing in string.
     * @param simplified to determine if it is simplified(means adding only letters to stack).
     * @param reverse this indicates wheter there is a letter end of the password which is middle of palindrome like 'e' in password 'aaaae'.
     * @return true if it is palindrome, false otherwise.
     */
    public boolean isPalindromePossible(String password1, int start, int end, int change, boolean simplified, boolean reverse){
        // simplified the string and send to function again with simplified version(means adding only letters to stack).
        if(!simplified){
            String simplifiedPw1 = onlyletters(password1);
            return isPalindromePossible(simplifiedPw1,0,simplifiedPw1.length()-1,1,true,false);
        }
        //change == 1 means that the method is called just after two letter is found which it is palindrome.
        if(start>=end && change==1) return true;

        //change != 1 means that it reaches the bound without finding two same letter which it is not palindrome.
        else if(start>=end && change!=1){
            System.out.println("The password1 is invalid. It should be possible to obtain a palindrome from the password1.");
            return false;
        }

        // means there is no replacing to obtain palindrome.
        else if(start+change>end){
            
            // this part checks again after replacing first character to another.
            // It is essential if there is a letter end of the password which is middle of palindrome like 'e' in password 'aaaae'
            // without this, it returns false because there is no match with 'e' in password 'aaaae'
            if(reverse){
                System.out.println("The password1 is invalid. It should be possible to obtain a palindrome from the password1.");
                return false;
            }
            else{
                char a = password1.charAt(start);
                char b = password1.charAt(start+1);
                StringBuilder sb = new StringBuilder(password1);  // replacing characters using StringBuilder.
                sb.setCharAt(start, b);
                sb.setCharAt(start+1, a);
                password1 = sb.toString();
                return isPalindromePossible(password1,start,end,1,true,true);
                
            }
        }
        else if(password1.charAt(start) == password1.charAt(end)){
            return isPalindromePossible(password1,start+1,end-1,1,true,reverse);
        }
        else{
            char a = password1.charAt(start);
            char b = password1.charAt(start+change);
            StringBuilder sb = new StringBuilder(password1);  // replacing characters using StringBuilder.
            sb.setCharAt(start, b);
            sb.setCharAt(start+change, a);
            password1 = sb.toString();
            return isPalindromePossible(password1,start,end,change+1,true,reverse);
        }
    }

    private String onlyletters(String password1){
        String simplifiedPw1 = "";
        for(int i=0;i<password1.length();++i){
            if(isletter(password1,i)){
                simplifiedPw1+=password1.charAt(i);
            }
        }
        return simplifiedPw1;
    }

    /**
     * Function to check if the password1 is valid for some cases.
     * @param pw1 the password1 that we want to check.
     * @return true if it is valid, false otherwise.
     */
    public boolean checkpassword1(String pw1){
        if(pw1.length()<8){
            System.out.println("The Password1 is invalid. It's length should be at least 8 characters including brackets.");
            return false;
        }
        boolean letterexist = false;
        int bracketcount=0;
        for(int i=0;i<pw1.length();++i){
            if(isparanthesis(pw1,i)){
                ++bracketcount;
            }
            else if(isletter(pw1,i)){
                letterexist = true;
            }
            else{
                System.out.println("The Password1 is invalid. It should have only letters and brackets.");
                return false;
            }
        }
        if(!letterexist){
            System.out.println("The Password1 is invalid. It should have letters.");
            return false;
        }
        else if(bracketcount<2){
            System.out.println("The Password1 is invalid. It should have at least two brackets.");
            return false;
        }
        else
            return true;
    }
    private boolean isletter(String pw1, int index){
        if(pw1.charAt(index) < 65 || (pw1.charAt(index) > 90 && pw1.charAt(index) < 97) || pw1.charAt(index) > 122){
            return false;
        }
        return true;
    }
}