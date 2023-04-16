/**
 * @author MuratErbilici
 * @since 13.04.2023
 */


import java.util.Stack;

public class Username{

    /**
     * Recursive function to check if the username is valid.
     * @param username the username that we want to check.
     * @param index for base case, starts from legth of array-1 to 0.
     * @return true if it is valid, false otherwise.
     */
    public boolean checkIfValidUsername(String username, int lastindex){
        if(username.length()==0){
            System.out.println("The username is invalid. It should have at least 1 character.");
            return false;
        }
        else if(lastindex == -1){
            return true;
        }
        else if(!isletter(username,lastindex)){
            System.out.println("The username is invalid. It should have letters only.");
            return false;
        }
        else
            return checkIfValidUsername(username,lastindex-1);
    }

    /**
     * Recursive function to check if the password1 has at least one char from username.
     * @param username the username that we want to check.
     * @param password1 the password1 that we want to check.
     * @return true if it has, false otherwise.
     */
    public boolean containsUserNameSpirit(String username, String password1){
        Stack<Character> usernamestack = new Stack<Character>();
        Stack<Character> password1stack = new Stack<Character>();

        for(int i=0;i<username.length();++i){
            usernamestack.push(username.charAt(i));
        }
        for(int i=0;i<password1.length();++i){
            password1stack.push(password1.charAt(i));
        }
        while(!password1stack.isEmpty()){
            while(!usernamestack.isEmpty()){
                if(usernamestack.peek() == password1stack.peek()){
                    return true;
                }
                usernamestack.pop();
            }
            for(int i=0;i<username.length();++i){   // filling stack again to check previous element in stack
                usernamestack.push(username.charAt(i));
            }
            password1stack.pop();
        }
        System.out.println("The password1 is invalid. It should have at least 1 character from the username.");
        return false;
    }

    private boolean isletter(String username, int index){
        if(username.charAt(index) < 65 || (username.charAt(index) > 90 && username.charAt(index) < 97) || username.charAt(index) > 122){
            return false;
        }
        return true;
    }
}