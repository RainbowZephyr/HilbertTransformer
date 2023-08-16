import org.apache.commons.math3.complex.Complex;
import org.github.HilbertTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HilbertTransformerTest {

    @Test
    void transformerPower2Test() {
        double[] waveform = {1.0, 2.0, 3.0, 4.0};
        Complex[] actual = HilbertTransformer.transform(waveform);

        // Validated by SciPy
        double[] real = {1.0, 2.0, 3.0, 4.0};
        double[] imaginary = {1.0, -1.0, -1.0, 1.0};

        Complex[] expected = new Complex[real.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Complex(real[i], imaginary[i]);
        }

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void transformerOddTest() {
        double[] waveform = {1.0, 2.0, 3.0, 4.0, 5.0};
        Complex[] actual = HilbertTransformer.transform(waveform);

        // Validated by SciPy
        double[] real = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] imaginary = {1.7013016167040795, -1.3763819204711736, -0.6498393924658126, -1.3763819204711731, 1.7013016167040795};

        Complex[] expected = new Complex[real.length];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Complex(real[i], imaginary[i]);
        }

        Assertions.assertArrayEquals(expected, actual);
    }
}
