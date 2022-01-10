package baseball;

import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;

/**
 * 숫자 야구 게임 Player Class
 * - 플레이어 행위 정의
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Player {

    /**
     * 사용자의 야구 숫자 추측값
     */
    private String guess;

    public Player() {}

    /**
     * 사용자의 추측값 콘솔 입력
     */
    public void getUserInput() {
        System.out.print("숫자를 입력해주세요 : ");
        this.guess = Console.readLine();
    }

    /**
     * 사용자의 추측값 반환
     *
     * @return 사용자의 추측값
     */
    public String getUserGuess() {
        return this.guess;
    }

    /**
     * 사용자의 추측값 입력의 유효성 여부 반환
     *
     * @return 사용자의 입력값 유효성 여부
     */
    public boolean validateInput() {
        Set<Integer> numbers = new HashSet<>();
        try {
            Integer.parseInt(this.guess);
        } catch (NumberFormatException nfe) {
            return false;
        }

        for (String s: this.guess.split("")) {
            numbers.add(Integer.parseInt(s));
        }

        return this.guess.length() == 3 && numbers.size() == 3;
    }
}
