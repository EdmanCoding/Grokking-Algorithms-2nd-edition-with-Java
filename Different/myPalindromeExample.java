import java.util.*;

//My attempt to accomplish palindrome method test from interview quiz
//interview - https://www.youtube.com/watch?v=rdphm7NB2MY
//Looks like it works :)
public class myPalindromeExample {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("eve", "Radar", "Monkey","Step no on pets"));
        for(String s : list){
            System.out.print("The word \"" + s +  "\"");
            System.out.println(isPalindrome(s)?" is a palindrome":" is not a palindrome");
        }
    }
    public static boolean isPalindrome (String word){
        word = word.toLowerCase();
        for(int backword = word.length()-1, forward = 0; backword >= 0||forward<word.length(); backword--, forward++){
            if (word.charAt(backword) != word.charAt(forward)) return false;
        }
        return true;
    }
}
