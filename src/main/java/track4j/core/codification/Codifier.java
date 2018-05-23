/**
 *
 */
package track4j.core.codification;

import java.util.Queue;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import track4j.core.recognition.Recognizer;
import track4j.core.recognition.RecognizerObserver;

/**
 * The @link{Codifier} class.
 */
public interface Codifier {
    /**
     * Codify the body joint according to class specification. This performs discretization on skeleton stream.
     * <p>
     * Codification can be derivative or on vectorial space.
     *
     * @param newVector
     *            the primary {@link Vector2D} joint according to sensor settings
     *
     */
    void codifyOnSkeletonChange(Vector2D newVector);

    /**
     * Get the feature vector.
     *
     * @return the {@link Queue} feature vector
     */
    Queue<Vector2D> extractFeatureVector();

    /**
     * Attache the {@link Recognizer} for feeback notification.
     *
     * @param recognizer
     *            the {@link Recognizer}
     */
    void attacheCoreRecognizer(RecognizerObserver recognizer);

    /**
     * Reset the frame for starting a new gesture.
     */
    void resetFrame();
}
