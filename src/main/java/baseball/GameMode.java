package baseball;

public enum GameMode {
    ONGOING(0), RENEW(1), STOP(2);
    final int value;

    GameMode(int enumType) {
        this.value = enumType;
    }
}
