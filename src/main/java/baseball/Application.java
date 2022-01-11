package baseball;

import baseball.entity.GameMode;
import baseball.entity.Player;
import baseball.service.BaseballGame;
import baseball.utils.Message;

/**
 * 숫자 야구 게임
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Application {

    /**
     * 숫자 야구 게임 메인 메서드 (비즈니스 로직)
     */
    public static void main(String[] args) {
        BaseballGame game = new BaseballGame(new Player());
        while (game.mode() != GameMode.STOP) {
            game.play();
        }
        Message.printGameEnds();
    }
}
