import java.util.regex.*;

public class PasswordCorrect {
    public static void main(String[] args) {
        String password = "Qwerty120";

        try {
            Matcher matcher = Pattern.compile(".{8,16}").matcher(password);
            boolean InRange = matcher.matches();
            matcher = Pattern.compile("([A-Z]{1,}[a-z]{1,}[0-9]{1,})").matcher(password);
            boolean IsWord = matcher.matches();

            if (InRange && IsWord)
                System.out.println("Valid password");
            else
                System.out.println("Invalid password");
            
        } catch (PatternSyntaxException e) {
            System.out.println(e);
        }
    }
}

/*
 * Необходимо написать программу, которая будет проверять корректность 
ввода пароля. Пароль должен состоять из латинских букв и цифр, быть 
длиной от 8 до 16 символов и содержать хотя бы одну заглавную букву и 
одну цифру. При этом программа должна использовать регулярные 
выражения для проверки пароля и обрабатывать возможные ошибки.
 */