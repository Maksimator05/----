import java.util.regex.*;

public class FindLowCase {
    public static void main(String[] args) {
        try {
            // ДЛЯ КОДА В СКРИН УДАЛИ КОММЕНТАРИИ
            // String text = "Text flaG AD ljdgljb S.E. Si";
            // Matcher matcher = Pattern.compile("([a-z][A-Z])").matcher(text);
            // if (matcher.find())
            //     System.out.println(matcher.replaceAll("!"));
            // else
            //     System.out.println("Nothing");

            String text = "TexT flaG AD S.E.Simonov";
            
            Pattern pattern = Pattern.compile("([a-z])([A-Z])");
            Matcher matcher = pattern.matcher(text);
            
            String result = matcher.replaceAll("$1!$2!");
            System.out.println(result);

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