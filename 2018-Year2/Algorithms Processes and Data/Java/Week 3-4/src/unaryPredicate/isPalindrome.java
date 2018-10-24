package unaryPredicate;

/**
 * Determines whether given string is Palindrome or not
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-24
 */
public class isPalindrome implements UnaryPredicate<String> {

    /**
     * Checks whether given string is palindromic
     *
     * @param string checked if its palindrome
     * @return true if given string is palindrome
     */
    @Override
    public boolean test(String string) {

        string = validateString(string); // validates string
        char[] charArray = string.toCharArray(); // converts string to char array
        int endOfString = string.length() - 1; // get the size of array
        for(int i = 0; i < string.length()/2; i++) { // check only until middle
            if(charArray[i] != charArray[endOfString]) { // check if [i] and [size-1] are not the same
                return false; // if not = they are not palindrome
            }
            else { // otherwise continue
                endOfString--;
            }
        }
        System.out.println("(" + string + ")"); // output the string
        return true; // string is palindrome
    }

    /**
     * Leaves only alphanumeric lowercase symbols in string
     *
     * @param string string to be validated
     * @return string only containing alphanumeric symbols and all lowercase
     */
    private String validateString(String string) {
        string = string.toLowerCase(); // make string lowercase
        string = string.replaceAll("[^A-Za-z0-9]",""); // leave alphanumeric symbols only
        return string; // return
    }
}
