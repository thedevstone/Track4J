/**
 *
 */
package track4j.core.codification;

import java.util.Queue;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.google.common.collect.EvictingQueue;

import track4j.core.recognition.RecognizerObserver;

/**
 * The @link{DerivativeCodifier} class.
 */
public class DerivativeCodifier implements Codifier {
    private final Queue<Vector2D> featureVector;
    private Vector2D oldVector;
    private Vector2D derivative; // NOPMD
    private Vector2D startingVector;
    private int frame;
    private final GestureFrameLenght gestureLenght;
    private RecognizerObserver recognizer;

    /**
     * The @link{DerivativeCodifier.java} constructor.
     *
     * @param frames
     *            the gesture's duration in frame
     */
    public DerivativeCodifier(final GestureFrameLenght frames) {
        this.featureVector = EvictingQueue.create(frames.getFrameNumber());
        this.oldVector = new Vector2D(0, 0);
        this.frame = 0;
        this.gestureLenght = frames;
    }

    @Override
    public void codifyOnSkeletonChange(final Vector2D newVector) {
        // CALCOLO DERIVATA
        this.derivative = newVector.subtract(this.oldVector);
        this.featureVector.offer(this.derivative);
        this.oldVector = newVector;
        // SE SONO ALLA FINE RESETTO
        if (this.frame == 0) {
            this.startingVector = newVector;
        }
        if (this.frame == this.gestureLenght.getFrameNumber() - 1) {
            this.resetFrame();
            this.recognizer.notifyOnFeatureVectorEvent(this.featureVector);
        } else { // NELLA CODA C'E' UN VETTORE IN PIU'
            this.incrementFrame();
            this.recognizer.notifyOnFrameChange(this.frame, this.derivative, this.startingVector.subtract(newVector));
        }
    }

    @Override
    public Queue<Vector2D> extractFeatureVector() {
        return this.featureVector;
    }

    @Override
    public void attacheCoreRecognizer(final RecognizerObserver recognizer) {
        this.recognizer = recognizer;
    }

    @Override
    public synchronized void resetFrame() {
        this.frame = 0;
    }

    private synchronized void incrementFrame() {
        this.frame++;
    }

    /**
     * Get the gesture lenght.
     *
     * @return the {@link GestureFrameLenght}
     */
    public GestureFrameLenght getGestureLenght() {
        return this.gestureLenght;
    }

}
