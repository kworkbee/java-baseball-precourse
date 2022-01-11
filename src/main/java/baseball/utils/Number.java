package baseball.utils;

import baseball.entity.Ball;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nextstep.utils.Randoms;

/**
 * Number Util
 * 볼에 들어가는 숫자 생성 관련
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Number {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    private Number() {}

    /**
     * 게임 볼에 필요한 난수 생성
     * @param REQUIRE_BALLS 필요 볼 개수
     * @return List<Ball> Typed - randomized balls
     */
    public static List<Ball> generateNumbers(long REQUIRE_BALLS) {
        List<Integer> balls = new ArrayList<>();
        while (balls.size() < REQUIRE_BALLS) {
            int random = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
            if (!balls.contains(random)) {
                balls.add(random);
            }
        }

        return convertToBalls(balls);
    }

    /**
     * 플레이어 입력 야구 숫자 타입 변환 (String -> List<Integer>)
     *
     * @param sNumbers 입력 String
     * @return List<Integer> Typed - balls that a player suggested
     * @throws NumberFormatException 포맷 오류
     */
    public static List<Integer> parseNumbers(String sNumbers) throws NumberFormatException {
        return Arrays.stream(sNumbers.split(""))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    /**
     * 숫자 Ball Type 변환
     *
     * @param intBalls Integer 타입의 야구볼
     * @return List<Ball> Typed
     */
    public static List<Ball> convertToBalls(List<Integer> intBalls) {
        return IntStream.range(1, intBalls.size() + 1)
            .mapToObj(i -> new Ball(i, intBalls.get(i - 1)))
            .collect(Collectors.toList());
    }
}
