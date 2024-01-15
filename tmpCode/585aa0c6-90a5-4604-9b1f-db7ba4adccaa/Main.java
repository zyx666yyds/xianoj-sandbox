public class Main{
    public boolean isPalindrome(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }
    
    public static void main(String[] args){
        System.out.println(isPalindrome(args[0]));
    }
}