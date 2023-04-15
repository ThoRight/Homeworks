/**
 * @author MuratErbilici
 * @since 13.04.2023
 */

import java.util.Stack;

public class Password2{

    /**
     * Recursive function to decide if there is exact division according to denominations array.
     * @param password2 the password2 that we want to check.
     * @param denominations that contains divisors.
     * @param index for base case, starts from 0 to length of array.
     * @return true if there is, false otherwise.
     */
    public boolean isExactDivision(int password2, int [] denominations, int index){
        if (password2 == 0)
            return true;
        if(index >= denominations.length)
            return false;
        if (denominations[index] <= password2)
            return isExactDivision(password2 - denominations[index], denominations, index) || isExactDivision(password2, denominations, index + 1);
        else
            return isExactDivision(password2, denominations, index + 1);
    }

    /**
     * checking the boundry of password2
     * @param pw2 the password2 that we want to check.
     * @return true if it is in the boundry, false otherwise.
     */
    public boolean checkpassword2(int pw2){
        if(pw2 > 10 && pw2 < 10000) return true;
        else{
            System.out.println("The password2 is invalid. It should be between 10 and 10,000.");
            return false;
        }
    }
}