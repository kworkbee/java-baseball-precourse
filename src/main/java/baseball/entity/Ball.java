package baseball.entity;

import java.util.Objects;

/**
 * Ball 객체
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Ball {

    private final int MIN_POSITION = 1;
    private final int MAX_POSITION = 3;
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 9;

    private final int position;
    private final int number;

    public Ball(int position, int number) {
        if (position < MIN_POSITION || position > MAX_POSITION) {
            throw new IllegalArgumentException();
        }

        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException();
        }

        this.position = position;
        this.number = number;
    }

    /**
     * 볼 숫자 반환
     * @return nth 볼 숫자 반환
     */
    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ball other = (Ball) obj;
        return Objects.equals(number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
