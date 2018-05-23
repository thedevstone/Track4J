/**
 *
 */
package track4j.core.recording;

/**
 * The @link{Recorder} class.
 */
public interface TimerObserver {
    /**
     * Update the timer for gesture recording.
     * 
     * @param val
     *            actual time
     */
    void udpateTimeCount(int val);
}
