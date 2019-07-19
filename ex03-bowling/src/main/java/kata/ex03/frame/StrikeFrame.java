package kata.ex03.frame;

import kata.ex03.Frame;
import kata.ex03.Roll;
import kata.ex03.roll.StrikeRoll;

import java.util.Optional;
import java.util.stream.Stream;

public class StrikeFrame extends FrameBase {

    // Rollが1つだけのFrameの場合、そのRollは必ずストライク
    private StrikeRoll roll;

    public StrikeFrame(StrikeRoll roll) {
        this.roll = roll;
    }

    @Override
    public int getScore() {
        return roll.getNumOfKnockedOutPins() +
                this.rollStream().limit(2)
                        .mapToInt(roll -> roll.getNumOfKnockedOutPins())
                        .sum();
    }

    @Override
    public Stream<Roll> rollStream() {
        Stream<Roll> thisFrameRollStream = Stream.of(roll);
        Stream<Roll> afterFramesStream =
                Stream.iterate(this.getNextFrame(), Optional::isPresent, frameOptional -> frameOptional.get().getNextFrame())
                        .flatMap(frameOptional -> frameOptional.get().rollStream());
        return Stream.concat(thisFrameRollStream, afterFramesStream);
    }

    @Override
    public String toFormattedString() {
        return roll.getPrintableChars();
    }
}
