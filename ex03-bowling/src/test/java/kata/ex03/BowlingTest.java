package kata.ex03;

import kata.ex03.frame.FinalFrameWithSpare;
import kata.ex03.frame.SpareFrame;
import kata.ex03.roll.NumberedRoll;
import kata.ex03.roll.SpareRoll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingTest {

    private void assertLine(Line line, int score, String scoresString) {
        assertAll(
                () -> assertEquals(score, line.getScore()),
                () -> {
                    StringWriter writer = new StringWriter();
                    line.print(writer);
                    assertEquals(scoresString, writer.toString());
                }
        );
    }

    @Test
    @DisplayName("全部ストライクで300点")
    void when_all_strike_score_is_300() {
        Line line = null; // TODO Lineを全ストライクで初期化
        assertLine(line, 300, "X X X X X X X X X XXX ");
    }

    @Test
    @DisplayName("全部スペアで150点")
    void when_all_spare_score_is_150() {
        List<Frame> frameList = List.of(
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new SpareFrame(new NumberedRoll(5), new SpareRoll(5)),
                new FinalFrameWithSpare(new NumberedRoll(5), new SpareRoll(5), new NumberedRoll(5))
        );
        for (int i = 0; i < frameList.size() - 1; i++) {
            Frame frame = frameList.get(i);
            frame.setNextFrame(frameList.get(i + 1));
        }
        Line line = new Line(frameList);
        assertLine(line, 150, "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");
    }

    @Test
    @DisplayName("全部9本で90点")
    void when_all_9_score_is_90() {
        Line line = null; // TODO Lineを全部9本で初期化
        assertLine(line, 90, "9- 9- 9- 9- 9- 9- 9- 9- 9- 9- ");
    }

}
