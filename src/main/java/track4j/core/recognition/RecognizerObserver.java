/**
 *
 */
package track4j.core.recognition;

import java.util.Queue;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * The @link{ObservableCoreRecognizer} class.
 */
public interface RecognizerObserver {
    /**
     * Notify the {@link Recognizer} when a feature vector {@link Queue} is avaiable.
     *
     * @param featureVector
     *            the {@link Queue} feature vector.
     */
    void notifyOnFeatureVectorEvent(Queue<Vector2D> featureVector);

    /**
     * Notify the {@link Recognizer} when a frame changes.
     *
     * @param frame
     *            the frame
     * @param vector
     *            the derivative vector
     * @param startingVector
     *            the starting vector
     */
    void notifyOnFrameChange(int frame, Vector2D vector, Vector2D startingVector);

}
