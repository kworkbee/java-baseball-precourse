package baseball;

import java.util.HashSet;
import java.util.Set;
import nextstep.utils.Console;

public class Player {

    private String guess;

    public Player() {}

    public void getUserInput() {
        System.out.print("숫자를 입력해주세요 : ");
        this.guess = Console.readLine();
    }

    public String getUserGuess() {
        return this.guess;
    }

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
