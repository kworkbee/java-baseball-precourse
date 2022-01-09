package baseball;

import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class GameManager {

    private final String COMMAND_RENEW = "1";
    private final String COMMAND_STOP = "2";
    private final int BALLS = 3;

    private GameMode MODE;
    private Player player;

    private String RANDOM_NUMBER;

    private int STRIKE;
    private int BALL;
    private int NOTHING;

    public GameManager(Player player) {
        this.player = player;
        resetBalls();
        initState();
    }

    private void initState() {
        this.MODE = GameMode.ONGOING;
        generateRandomNumber();
    }

    private void resetBalls() {
        this.STRIKE = 0;
        this.BALL = 0;
        this.NOTHING = 0;
    }

    private void generateRandomNumber() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < this.BALLS) {
            int random = Randoms.pickNumberInRange(0, 9);

            // Cannot locate 0 on first
            if (numbers.size() == 0 && random == 0) {
                continue;
            }

            if (!numbers.contains(random)) {
                numbers.add(random);
                sb.append(String.valueOf(random));
            }
        }

        this.RANDOM_NUMBER = sb.toString();
    }

    private String getRandomNumber() {
        return this.RANDOM_NUMBER;
    }

    private void compare(String origin, String comparisonTarget) {
        for (int i = 0; i < this.BALLS; i++) {
            if (origin.charAt(i) == comparisonTarget.charAt(i)) {
                this.STRIKE++;
            } else {
                if (origin.indexOf(comparisonTarget.charAt(i)) != -1) {
                    this.BALL++;
                } else {
                    this.NOTHING++;
                }
            }
        }
    }

    private void endOneTurn() {
        String willDo = Console.readLine();
        if (willDo.equals(COMMAND_RENEW)) {
            this.MODE = GameMode.RENEW;
        }
        else if (willDo.equals(COMMAND_STOP)) {
            this.MODE = GameMode.STOP;
        }
    }

    public void play() {
        // While player wants not to stop
        while (this.MODE != GameMode.STOP) {
            // Reset Balls
            resetBalls();

            // Renew the game when the game ends
            if (this.MODE == GameMode.RENEW) {
                initState();
            }

            this.player.getUserInput();

            // Validate Player Input
            if (!this.player.validateInput()) {
                System.out.println("[ERROR] 잘못된 입력입니다.");
            } else {
                // Comparison
                compare(this.getRandomNumber(), this.player.getUserGuess());

                if (this.NOTHING == 3) {
                    System.out.print("낫싱");
                } else if (this.STRIKE == 3) {
                    System.out.println("3스트라이크");
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                    endOneTurn();
                } else if (this.STRIKE == 1 || this.STRIKE == 2) {
                    System.out.print(this.STRIKE + "스트라이크 ");
                }

                if (this.MODE == GameMode.ONGOING && this.BALL > 0) {
                    System.out.print(this.BALL + "볼 ");
                }

                System.out.println();
            }
        }
        System.out.println("게임 끝");
    }
}
