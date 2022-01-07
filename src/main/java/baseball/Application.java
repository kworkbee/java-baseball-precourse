package baseball;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    private static String initRandom() {
        String ret = "";

        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() != 3) {
            int random = Randoms.pickNumberInRange(0, 9);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        for (int number: numbers) {
            ret = ret + number;
        }

        return ret;
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
        while (true) {
            System.out.print("숫자 야구 게임 - 숫자를 입력하세요: ");
            System.out.println();

            // Initialize Data
            String random = initRandom();
            String userInput = getUserInput();

            // Validation
            if (!validateUserInput(userInput)) {
                System.out. println("잘못된 입력입니다.");
            } else {
                // Comparison
                HashMap<String, Integer> resultMap = compare(random, userInput);

                int strike = resultMap.get("strike");
                int ball = resultMap.get("ball");
                int nothing = resultMap.get("nothing");

                if (nothing > 0) {
                    System.out.print("낫싱");
                } else {
                    if (strike > 0) {
                        System.out.print(strike + "스트라이크 ");
                        if (strike == 3) {
                            break;
                        }
                    }

                    if (ball > 0) {
                        System.out.print(ball + "볼 ");
                    }
                }

                System.out.println();
            }
        }

        System.out.println("게임 끝");
    }
}
