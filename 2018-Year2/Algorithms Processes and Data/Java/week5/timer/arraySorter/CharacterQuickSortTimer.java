package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CharacterArrayGenerator;
import timer.Timer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.HashMap;

public class CharacterQuickSortTimer extends QuickSortTimer<Character> {
    @Override
    public Timer getTimer(int size) {
        ArrayGenerator<Character> generator = new CharacterArrayGenerator();
        setArray(generator.getArray(size));
        return this;
    }

    public static void main(String[] args) {
        QuickSortTimer timer = new CharacterQuickSortTimer();
        timer.timingSequence();
    }
}
