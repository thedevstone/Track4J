/**
 *
 */
package track4j.core.codification;

/**
 * The @link{JestureFrameLenght} class.
 */
public enum GestureFrameLenght {
    /**
     * Gestures duration in frame. 30 FPS base
     */
    ONE_SECOND(30), TWO_SECONDS(60), THREE_SECONDS(90);

    private int frameNumber;

    /**
     * The @link{JestureFrameLenght.java} constructor.
     */
    GestureFrameLenght(final int frames) {
        this.frameNumber = frames;
    }

    /**
     * Get the @link{frameNumber}.
     * 
     * @return the @link{frameNumber}
     */
    public int getFrameNumber() {
        return this.frameNumber;
    }

}
