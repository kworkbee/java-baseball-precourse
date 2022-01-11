package baseball.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 볼 List 객체
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class BallGroup {
    private final List<Ball> balls;

    public BallGroup(List<Ball> balls) {
        validateNumbers(balls);
        this.balls = balls;
    }

    /**
     * Ball 값 가젹오기
     *
     * @param i Get ith element from list
     * @return
     */
    public Ball value(int i) {
        return balls.get(i);
    }

    /**
     * 플레이어 입력 숫자의 유효성 검사
     *
     * @throws NumberFormatException
     */
    private void validateNumbers(List<Ball> balls) throws NumberFormatException {
        List<Ball> distinctBalls = balls.stream()
            .distinct()
            .collect(Collectors.toList());

        if (distinctBalls.size() != 3) {
            throw new NumberFormatException();
        }
    }

    @Override
    public String toString() {
        return "Ball{" +
            balls.stream()
                .map(Ball::number)
                .map(String::valueOf)
                .collect(Collectors.joining("")) +
            "}";
    }
}
