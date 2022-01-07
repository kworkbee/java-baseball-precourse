package baseball;

import nextstep.utils.Randoms;

public class Application {

    private static String initRandom() {
        return String.valueOf(Randoms.pickNumberInRange(123, 987));
    }

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
    }
}
