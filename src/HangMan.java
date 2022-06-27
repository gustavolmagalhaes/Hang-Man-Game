import java.util.*;
import java.util.function.BiConsumer;

public class HangMan {
    
    /*
          _______
     |/      |
     |      (_)
     |      \|/
     |       |
     |      / \
     |
    _|___
     */

    private boolean hasRightLeg = false;
    private boolean hasLeftLeg = false;
    private boolean hasRightArm = false;
    private boolean hasLeftArm = false;
    private boolean hasBody = false;
    private boolean hasHead = false;

    private String word;

    public State gameState = State.NEUTRAL;

    enum State {
        WON,
        NEUTRAL,
        LOST
    }
    enum BodyParts {
        HEAD, BODY, LEFT_ARM,
        RIGHT_ARM, LEFT_LEG, RIGHT_LEG
    }

    ArrayList<Character> letters = new ArrayList<>();
    ArrayList<Boolean> shown = new ArrayList<>();
    ArrayList<Character> wrong = new ArrayList<>();

    public HangMan(String word) {
        this.word = word;
        for (char letter : word.toCharArray()) {
            letters.add(letter);
            shown.add(letter == ' ');
        }
    }

    private void breakHang() {
        if (!hasHead) {
            hasHead = true;
            return;
        }
        if (!hasBody) {
            hasBody = true;
            return;
        }
        if (!hasRightArm) {
            hasRightArm = true;
            return;
        }
        if (!hasLeftArm) {
            hasLeftArm = true;
            return;
        }
        if (!hasLeftLeg) {
            hasLeftLeg = true;
            return;
        }
        if (!hasRightLeg) {
            hasRightLeg = true;
            gameState = State.LOST;
        }
    }

    public void fullGuess(String guess) {
        if (!guess.equalsIgnoreCase(word)) { gameState = State.LOST; return; }
        gameState = State.WON;
    }

    public void checkLetter(char letter) {

        if (wrong.contains(letter)) {
            return;
        }

        boolean worked = false;

        for (int i = 0; i < letters.size(); i++) {
            if (letters.get(i) == letter) {
                shown.set(i, true);
                worked = true;
            }
        }

        if (!worked) {
            breakHang();
            return;
        }

        if (shown.contains(false)) {
            return;
        }

        gameState = State.WON;

    }

    public boolean hasMember(BodyParts bp) {
        return switch (bp) {
            case HEAD -> hasHead;
            case BODY -> hasBody;
            case LEFT_ARM -> hasLeftArm;
            case RIGHT_ARM -> hasRightArm;
            case LEFT_LEG -> hasLeftLeg;
            case RIGHT_LEG -> hasRightLeg;
            default -> false;
        };
    }

    public String getHang() {
        return  "          _______\n" +
                "     |/      |\n" +
                "     |      "+getMember(BodyParts.HEAD)+"\n" +
                "     |      "+getMember(BodyParts.LEFT_ARM)+getMember(BodyParts.BODY)+getMember(BodyParts.RIGHT_ARM)+"\n" +
                "     |       "+getMember(BodyParts.BODY)+"\n" +
                "     |      "+getMember(BodyParts.LEFT_LEG)+" "+getMember(BodyParts.RIGHT_LEG)+"\n" +
                "     |\n" +
                "    _|___";
    }

    public String getLetters() {
        char[] result = new char[letters.size()];

        for (int i = 0; i < letters.size(); i++) {
            if (shown.get(i)) {
                result[i] = letters.get(i);
            } else {
                result[i] = '*';
            }
        }

        return new String(result);
    }

    public String getMember(BodyParts member) {
        return switch (member) {
            case HEAD -> (hasHead) ? "(_)" : "   ";
            case BODY -> (hasBody) ? "|" : " ";
            case LEFT_ARM -> (hasLeftArm) ? "\\" : " ";
            case RIGHT_ARM -> (hasRightArm) ? "/" : " ";
            case LEFT_LEG -> (hasLeftLeg) ? "/" : " ";
            case RIGHT_LEG -> (hasRightLeg) ? "\\" : " ";
            default -> "wtf";
        };
    }
}
