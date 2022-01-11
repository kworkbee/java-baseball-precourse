package baseball.service;

import baseball.entity.Ball;
import baseball.entity.BallGroup;
import baseball.utils.Message;
import baseball.utils.Number;
import baseball.entity.GameMode;
import baseball.entity.Player;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nextstep.utils.Console;

/**
 * GameManager
 * 게임 Turn 진행 관리 객체
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class BaseballGame {

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
    private final Player PLAYER;
    /**
     * 컴퓨터 생성 난수
     */
    private BallGroup RANDOM_BALLS;

    /**
     * 사용자의 입력으로부터 스트라이크 / 볼 / 낫싱 개수 조회
     */
    private long STRIKE;
    private long BALL;
    private long NOTHING;

    /**
     * 게임 진행 위한 플레이어 객체 주입
     * 초기 상태 셋업
     *
     * @param player 플레이어 객체
     */
    public BaseballGame(Player player) {
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
        this.RANDOM_BALLS = new BallGroup(Number.generateNumbers(this.BALLS));
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
     * 볼 수 계산
     *
     * @param idx ith index
     * @param value 볼 숫자
     * @return 볼 수
     */
    private long compareBall(int idx, Ball value) {
        return IntStream.range(1, BALLS + 1)
            .filter(i -> i != idx)
            .filter(i -> RANDOM_BALLS.value(i - 1).number() == value.number())
            .count();
    }

    /**
     * 값 비교
     * - 컴퓨터 생성 난수
     * - 사용자 입력 추측값
     *
     * @param playerBalls       비교대상 (플레이어 추측값)
     */
    private void compare(BallGroup playerBalls) {
        STRIKE = IntStream.range(1, BALLS + 1)
            .filter(i -> RANDOM_BALLS.value(i - 1).number() == playerBalls.value(i - 1).number())
            .count();
        BALL = IntStream.range(1, BALLS + 1)
            .map(i -> (int) compareBall(i, playerBalls.value(i - 1)))
            .sum();
        if (STRIKE == 0 && BALL == 0) {
            NOTHING = 3;
        }
    }

    /**
     * 한 턴이 끝났을 때 플레이어 처리
     */
    private void endOneTurn() {
        String willDo = Console.readLine();
        String COMMAND_RENEW = "1";
        String COMMAND_STOP = "2";
        if (willDo.equals(COMMAND_RENEW)) {
            MODE = GameMode.RENEW;
        }
        else if (willDo.equals(COMMAND_STOP)) {
            MODE = GameMode.STOP;
        }
    }

    /**
     * 플레이어 추측값에 따른 게임 진행 판단
     */
    private void judgement() {
        compare(PLAYER.guess());

        if (NOTHING == 3) {
            Message.printNothing();
        } else if (STRIKE == 3) {
            Message.printFullStrike(BALLS);
            endOneTurn();
        } else if (STRIKE == 1 || STRIKE == 2) {
            Message.printStrike(STRIKE);
        }

        if (this.MODE == GameMode.ONGOING && BALL > 0) {
            Message.printBall(BALL);
        }

        System.out.println();
    }

    /**
     * 게임 진행 핵심 로직
     *
     * - 스트라이크 결과를 만들 때까지 반복 진행
     */
    public void play() {
        // Reset Balls
        resetBalls();

        // Renew the game when the game ends
        if (MODE == GameMode.RENEW) {
            initState();
        }

        try {
            // Get Player Input
            PLAYER.getNumbers();

            // Comparison & Judgement
            judgement();
        } catch (NumberFormatException nfe) {
            Message.printInvalidInputError();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    /**
     * GameMode 반환
     *
     * @return GameMode
     */
    public GameMode mode() {
        return MODE;
    }
}
