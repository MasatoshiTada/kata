package kata.ex03.frame;

import kata.ex03.Roll;
import kata.ex03.roll.NumberedRoll;
import kata.ex03.roll.StrikeRoll;

import java.util.stream.Stream;

public class FinalFrameWithStrike extends FrameBase {

    private StrikeRoll roll1; // 最終フレームで1投目がストライクなら3投
    private NumberedRoll roll2;
    private NumberedRoll roll3;

    public FinalFrameWithStrike(StrikeRoll roll1, NumberedRoll roll2, NumberedRoll roll3) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.roll3 = roll3;
    }

    @Override
    public int getScore() {
        return this.rollStream()
                .mapToInt(roll -> roll.getNumOfKnockedOutPins())
                .sum()
                + roll2.getNumOfKnockedOutPins()
                + roll3.getNumOfKnockedOutPins();
    }

    @Override
    public Stream<Roll> rollStream() {
        return Stream.of(roll1, roll2, roll3);
    }

    @Override
    public String toFormattedString() {
        return roll1.getPrintableChars()
                + roll2.getPrintableChars()
                + roll3.getPrintableChars();
    }
}
