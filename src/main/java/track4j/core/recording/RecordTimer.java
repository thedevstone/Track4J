/**
 *
 */
package track4j.core.recording;

/**
 * The @link{RecordTimer} class.
 */
public class RecordTimer extends Thread {
    private final TimerObserver recorder;
    private static final int SECOND_WAIT = 1000;
    private int count;

    /**
     * The @link{RecordTimer.java} constructor.
     *
     * @param count
     *            the number of seconds to wait
     * @param recorder
     *            the recorder
     */
    public RecordTimer(final int count, final TimerObserver recorder) {
        this.count = count;
        this.recorder = recorder;
    }

    private synchronized boolean decreaseCounter() {
        if (this.count == 0) {
            return false;
        } else {
            this.count--;
            this.recorder.udpateTimeCount(this.count);
            return true;
        }
    }

    @Override
    public void run() {
        while (this.decreaseCounter()) {
            try {
                Thread.sleep(RecordTimer.SECOND_WAIT);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
