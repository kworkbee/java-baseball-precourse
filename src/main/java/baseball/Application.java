package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    private static String initRandom() {
        return String.valueOf(Randoms.pickNumberInRange(123, 987));
    }

    private static boolean validateUserInput(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return userInput.length() == 3;
    }

    private static String getUserInput() {
        return Console.readLine();
    }

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
    }
}
