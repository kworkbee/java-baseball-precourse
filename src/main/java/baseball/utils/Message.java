package baseball.utils;

/**
 * Message
 * Message 관리 객체
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Message {

    private static final String MSG_REQUEST_INPUT = "숫자를 입력해주세요 : ";
    private static final String MSG_ERROR_INVALID_INPUT = "[ERROR] 잘못된 입력입니다.";
    private static final String MSG_GAME_ENDS = "게임 끝";
    private static final String MSG_GAME_RESULT_NOTHING = "낫싱";
    private static final String MSG_GAME_RESULT_FULL_STRIKE_1 = "%d개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String MSG_GAME_RESULT_FULL_STRIKE_2 = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String MSG_GAME_RESULT_STRIKE_FORMAT = "%d스트라이크 ";
    private static final String MSG_GAME_RESULT_BALL_FORMAT = "%d볼 ";

    private Message() {}

    /**
     * 플레이어 입력 요청 메시지
     */
    public static void printInputRequest() {
        System.out.print(MSG_REQUEST_INPUT);
    }

    /**
     * 유효하지 않은 입력 오류 메시지
     */
    public static void printInvalidInputError() {
        System.out.println(MSG_ERROR_INVALID_INPUT);
    }

    /**
     * 게임 종료 메시지
     */
    public static void printGameEnds() {
        System.out.println(MSG_GAME_ENDS);
    }

    /**
     * 낫싱 출력 메시지
     */
    public static void printNothing() {
        System.out.print(MSG_GAME_RESULT_NOTHING);
    }

    /**
     * 3 STRIKE 메시지
     * @param TOTAL_BALLS 총 볼 개수
     */
    public static void printFullStrike(int TOTAL_BALLS) {
        Message.printStrike(TOTAL_BALLS);
        System.out.printf(MSG_GAME_RESULT_FULL_STRIKE_1, TOTAL_BALLS);
        System.out.println(MSG_GAME_RESULT_FULL_STRIKE_2);
    }

    /**
     * Strike 메시지
     * @param STRIKE 스트라이크 수
     */
    public static void printStrike(long STRIKE) {
        System.out.printf(MSG_GAME_RESULT_STRIKE_FORMAT, STRIKE);
    }

    /**
     * Ball 메시지
     * @param BALL 볼 수
     */
    public static void printBall(long BALL) {
        System.out.printf(MSG_GAME_RESULT_BALL_FORMAT, BALL);

    }
}
