package kata.ex03.frame;

import kata.ex03.Roll;
import kata.ex03.roll.NumberedRoll;
import kata.ex03.roll.SpareRoll;
import kata.ex03.roll.StrikeRoll;

import java.util.stream.Stream;

public class FinalFrameWithSpare extends FrameBase {

    private NumberedRoll roll1;
    private SpareRoll roll2; // 最終フレームで2投目がスペアなら3投
    private NumberedRoll roll3;

    public FinalFrameWithSpare(NumberedRoll roll1, SpareRoll roll2, NumberedRoll roll3) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.roll3 = roll3;
    }

    @Override
    public int getScore() {
        return this.rollStream()
                .mapToInt(roll -> roll.getNumOfKnockedOutPins())
                .sum()
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
