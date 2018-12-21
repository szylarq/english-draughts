import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String coinToss(String optionOne, String optionTwo){

        return Math.random() < 0.5 ? optionOne : optionTwo;
    }

    public static boolean isInputCorrect(String input){

        if(input.length() == 10 || input.length() == 15) {

            String resultString = "";
            String regex = "\\([0-9],[0-9]\\)";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {

                resultString += matcher.group();
            }
            System.out.println(resultString);

            return resultString.equals(input);
        }
        else{

            return false;
        }
    }

    public static char[] extractDigitsFromInput(String input){

        return input.replaceAll("\\D+","").toCharArray();
    }

    public static int[] decrementDigits(char[] values){

        int[] resultArray = new int[values.length];

        for (int i = 0; i < values.length; i++) {

            resultArray[i] = Character.getNumericValue(values[i]) - 1;
        }

        return resultArray;
    }
}
