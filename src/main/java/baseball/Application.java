package baseball;

import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    private static String initRandom() {
        return String.valueOf(Randoms.pickNumberInRange(123, 987));
    }

    private static boolean validateUserInput(String userInput) {
        Set<Integer> numbers = new HashSet<>();
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException nfe) {
            return false;
        }

        for (String s: userInput.split("")) {
            numbers.add(Integer.parseInt(s));
        }

        return userInput.length() == 3 && numbers.size() == 3;
    }

    private static String getUserInput() {
        return Console.readLine();
    }

    public static void main(String[] args) {

    }
}
