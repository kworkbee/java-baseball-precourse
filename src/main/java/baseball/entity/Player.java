package baseball.entity;

import baseball.utils.Number;
import baseball.utils.Message;
import java.util.List;
import java.util.NoSuchElementException;
import nextstep.utils.Console;

/**
 * 숫자 야구 게임 Player Class - 플레이어 행위 정의
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Player {

    /**
     * 사용자의 야구 숫자 추측값
     */
    private BallGroup guess;

    public Player() {
    }

    /**
     * 사용자의 추측값 콘솔 입력
     */
    public void getNumbers() throws NoSuchElementException, IllegalArgumentException {
        Message.printInputRequest();
        this.guess = new BallGroup(
            Number.convertToBalls(
                Number.parseNumbers(Console.readLine())
            )
        );
    }

    /**
     * 사용자의 추측값 반환
     *
     * @return 사용자의 추측값
     */
    public BallGroup guess() {
        return this.guess;
    }
}
