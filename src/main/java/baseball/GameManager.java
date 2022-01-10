package baseball;

import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

/**
 * GameManager
 * 게임 Turn 진행 관리 객체
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class GameManager {

    /**
     * COMMAND_* : 한 턴이 끝난 시점에서 사용자의 계속 진행 여부 입력
     */
    private final String COMMAND_RENEW = "1";
    private final String COMMAND_STOP = "2";
    /**
     * BALLS : 숫자 야구에서 사용되는 전체 볼 수
     */
    private final int BALLS = 3;

    /**
     * 현재 게임 모드
     */
    private GameMode MODE;
    /**
     * 게임 진행 플레이어
     */
    private Player PLAYER;

    /**
     * 컴퓨터 생성 난수
     */
    private String RANDOM_NUMBER;

    /**
     * 사용자의 입력으로부터 스트라이크 / 볼 / 낫싱 개수 조회
     */
    private int STRIKE;
    private int BALL;
    private int NOTHING;

    /**
     * 게임 진행 위한 플레이어 객체 주입
     * 초기 상태 셋업
     *
     * @param player 플레이어 객체
     */
    public GameManager(Player player) {
        this.PLAYER = player;
        resetBalls();
        initState();
    }

    /**
     * 초기 상태 셋업
     * - 모드 설정
     * - 난수 생성
     */
    private void initState() {
        this.MODE = GameMode.ONGOING;
        generateRandomNumber();
    }

    /**
     * 게임 진행 위한 스트라이크 / 볼 / 낫싱 상태 초기화
     */
    private void resetBalls() {
        this.STRIKE = 0;
        this.BALL = 0;
        this.NOTHING = 0;
    }

    /**
     * 난수 생성
     */
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

    /**
     * 값 비교
     * - 컴퓨터 생성 난수
     * - 사용자 입력 추측값
     *
     * @param origin            컴퓨터 추측값
     * @param comparisonTarget  비교대상 (플레이어 추측값)
     */
    private void compare(String origin, String comparisonTarget) {
        for (int i = 0; i < this.BALLS; i++) {
            // Case when ith character equals each other
            if (origin.charAt(i) == comparisonTarget.charAt(i)) {
                this.STRIKE++;
            } else {
                // Case when ith character not equals each other, but both has an element
                if (origin.indexOf(comparisonTarget.charAt(i)) != -1) {
                    this.BALL++;
                } else {
                    this.NOTHING++;
                }
            }
        }
    }

    /**
     * 한 턴이 끝났을 때 플레이어 처리
     */
    private void endOneTurn() {
        String willDo = Console.readLine();
        if (willDo.equals(COMMAND_RENEW)) {
            this.MODE = GameMode.RENEW;
        }
        else if (willDo.equals(COMMAND_STOP)) {
            this.MODE = GameMode.STOP;
        }
    }

    /**
     * 플레이어 추측값에 따른 게임 진행 판단
     */
    private void judgement() {
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

    /**
     * 게임 진행 핵심 로직
     *
     * - 스트라이크 결과를 만들 때까지 반복 진행
     */
    public void play() {
        // While player wants not to stop
        while (this.MODE != GameMode.STOP) {
            // Reset Balls
            resetBalls();

            // Renew the game when the game ends
            if (this.MODE == GameMode.RENEW) {
                initState();
            }

            this.PLAYER.getUserInput();

            // Validate Player Input
            if (!this.PLAYER.validateInput()) {
                System.out.println("[ERROR] 잘못된 입력입니다.");
            } else {
                // Comparison
                compare(this.RANDOM_NUMBER, this.PLAYER.getUserGuess());

                // Judgement
                judgement();
            }
        }
        System.out.println("게임 끝");
    }
}
