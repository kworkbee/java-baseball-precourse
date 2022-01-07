package baseball;

import java.util.HashMap;
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

    private static HashMap<String, Integer> compare(String origin, String comparisonTarget) {
        HashMap<String, Integer> comparisonResult = new HashMap<>();

        int strike = 0;
        int ball = 0;
        int nothing = 0;

        for (int i = 0; i < comparisonTarget.length(); i++) {
            if (origin.charAt(i) == comparisonTarget.charAt(i)) {
                strike++;
            } else {
                if (origin.indexOf(comparisonTarget.charAt(i)) != -1) {
                    ball++;
                } else {
                    nothing++;
                }
            }
        }

        comparisonResult.put("strike", strike);
        comparisonResult.put("ball", ball);
        comparisonResult.put("nothing", nothing);

        return comparisonResult;
    }

    public static void main(String[] args) {
    }
}
