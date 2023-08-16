package org.github;

import hageldave.ezfftw.dp.FFT;
import org.apache.commons.math3.complex.Complex;

public class HilbertTransformer {

    /***
     * Applies Hilbert transformation using FFT, based on SciPy's Marple implementation
     * @param waveform Signal in time domain
     * @return Analytical signal, real values are original signal and imaginary values are hilbert transformed signal
     */
    public static Complex[] transform(double[] waveform) {
        double[] real = new double[waveform.length];
        double[] imaginary = new double[waveform.length];

        FFT.fft(waveform, real, imaginary, waveform.length);
        double[] conv = new double[waveform.length];

        if (waveform.length % 2 == 0) {
            conv[0] = 1;
            for (int i = 1; i < waveform.length / 2; i++) {
                conv[i] = 2;
            }
            conv[waveform.length / 2] = 1;
        } else {
            conv[0] = 1;
            for (int i = 1; i < (waveform.length + 1) / 2; i++) {
                conv[i] = 2;
            }
        }

        double[] hilbertReal = new double[waveform.length];
        double[] hilbertImaginary = new double[waveform.length];

        for (int i = 0; i < waveform.length; i++) {
            hilbertReal[i] = real[i] * conv[i];
            hilbertImaginary[i] = imaginary[i] * conv[i];
        }

        double[] outputReal = new double[waveform.length];
        double[] outputImaginary = new double[waveform.length];

        FFT.ifft(hilbertReal, hilbertImaginary, outputReal, outputImaginary, waveform.length);

        Complex[] result = new Complex[waveform.length];
        for (int i = 0; i < waveform.length; i++) {
            result[i] = new Complex(outputReal[i]/ waveform.length, outputImaginary[i]/ waveform.length) ;
        }

        return result;
    }

}
