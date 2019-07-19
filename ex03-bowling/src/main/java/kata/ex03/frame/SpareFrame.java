package kata.ex03.frame;

import kata.ex03.Roll;
import kata.ex03.roll.NumberedRoll;
import kata.ex03.roll.SpareRoll;

import java.util.Optional;
import java.util.stream.Stream;

public class SpareFrame extends FrameBase {

    private NumberedRoll roll1;
    private SpareRoll roll2;

    public SpareFrame(NumberedRoll roll1, SpareRoll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    @Override
    public int getScore() {
        return 10 + this.rollStream().limit(1)
                .mapToInt(roll -> roll.getNumOfKnockedOutPins())
                .sum();
    }

    @Override
    public Stream<Roll> rollStream() {
        Stream<Roll> thisFrameRollStream = Stream.of(roll1, roll2);
        Stream<Roll> afterFramesStream =
                Stream.iterate(this.getNextFrame(), Optional::isPresent, frameOptional -> frameOptional.get().getNextFrame())
                        .flatMap(frameOptional -> frameOptional.get().rollStream());
        return Stream.concat(thisFrameRollStream, afterFramesStream);
    }

    @Override
    public String toFormattedString() {
        return roll1.getPrintableChars() + roll2.getPrintableChars();
    }
}
