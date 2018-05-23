package track4j.core.codification;

/**
 * Frequency in extraction.
 *
 *
 */
public enum FeatureExtractionFrequency {
    /**
     * Gestures duration in frame. 30 FPS base
     */
    TEN_SECOND(10), TWENTY_SECONDS(20), THIRTY_SECONDS(30);

    private int frequencyNumber;

    /**
     * The @link{JestureFrameLenght.java} constructor.
     *
     * @param frequency
     */
    FeatureExtractionFrequency(final int frequency) {
        this.frequencyNumber = frequency;
    }

    /**
     * Get the @link{frameNumber}.
     *
     * @return the @link{frameNumber}
     */
    public int getExtractionFrequency() {
        return this.frequencyNumber;
    }
}
