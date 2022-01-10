package baseball;

/**
 * 숫자 야구 게임
 *
 * @author G1.Jeon <g1.tommy.jeon@gmail.com>
 */
public class Application {

    /**
     * 숫자 야구 게임 메인 메서드 (비즈니스 로직)
     * @param args
     */
    public static void main(String[] args) {
        GameManager gm = new GameManager(new Player());
        gm.play();
    }
}
