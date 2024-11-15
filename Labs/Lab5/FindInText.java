import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FindInText {
    public static void main(String[] args) {
        try {
            String text = "Jake B.N. qUicKly croSsed THE Street to catch the bus";
            String text2 = "Джейк Б.Н. быстро пеРесёк улицу, чтОбы успеть на автобус";
            
            
            Pattern pattern = Pattern.compile("([а-я])([А-Я])");
            Pattern pattern2 = Pattern.compile("([a-z])([A-Z])");
            
            Matcher matcher = pattern.matcher(text2);
            Matcher matcher2 = pattern2.matcher(text);
            
            String result = matcher2.replaceAll("$1!$2!");
            String result2 = matcher.replaceAll("$1!$2!");
            
            System.out.println(result);
            System.out.println(result2);

        } catch (PatternSyntaxException e) {
            System.out.println(e);
        }
    }
}

/*
 * Необходимо написать программу, которая будет находить все случаи в 
тексте, когда сразу после строчной буквы идет заглавная, без какого-либо 
символа между ними, и выделять их знаками «!» с двух сторон. 
 */