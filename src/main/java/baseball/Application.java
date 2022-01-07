package baseball;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {

    enum GameMode {
        ONGOING(0), RENEW(1), STOP(2);
        final int value;

        GameMode(int enumType) {
            this.value = enumType;
        }
    }

    private static String initRandom() {
        String ret = "";

        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() != 3) {
            int random = Randoms.pickNumberInRange(0, 9);
            if (!numbers.contains(random)) {
                numbers.add(random);
                ret = ret.concat(String.valueOf(random));
            }
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

        for (int i = 0; i < origin.length(); i++) {
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
        GameMode mode = GameMode.ONGOING;
        String random = initRandom();

        while (mode != GameMode.STOP) {

            if (mode == GameMode.RENEW) {
                random = initRandom();
                mode = GameMode.ONGOING;
            }

            System.out.print("숫자를 입력해주세요 : ");
            String userInput = getUserInput();

            // Validation
            if (!validateUserInput(userInput)) {
                System.out.println("[ERROR] 잘못된 입력입니다.");
            } else {
                // Comparison
                HashMap<String, Integer> resultMap = compare(random, userInput);

                int strike = resultMap.get("strike");
                int ball = resultMap.get("ball");
                int nothing = resultMap.get("nothing");

                if (nothing == 3) {
                    System.out.println("낫싱");
                } else {
                    if (strike == 3) {
                        System.out.println("3스트라이크");
                        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                        String willDo = Console.readLine();
                        if (willDo.equals("2")) {
                            mode = GameMode.STOP;
                            continue;
                        } else if (willDo.equals("1")) {
                            mode = GameMode.RENEW;
                        }
                    } else if (strike == 1 || strike == 2) {
                        System.out.print(strike + "스트라이크 ");
                    }

                    if (ball > 0) {
                        System.out.print(ball + "볼 ");
                    }

                    System.out.println();
                }
            }
        }

        System.out.println("게임 끝");
    }
}
