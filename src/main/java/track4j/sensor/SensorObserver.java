/**
 *
 */
package track4j.sensor;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * The @link{ObservableRecognizer} class.
 */
public interface SensorObserver {
    /**
     * Notify recognizer when a Joint {@link Vector2D} is available.
     *
     * @param primaryJoint
     *            the primary Joint {@link Vector2D} that generates gestures.
     * @param secondaryJoint
     *            the secondary Joint {@link Vector2D}
     */
    void notifyOnSkeletonChange(Vector2D primaryJoint, Vector2D secondaryJoint);

    /**
     * Notify recognizer when a acceleration {@link Vector3D} is available.
     *
     * @param acceleration
     *            the {@link Vector3D} acceleration
     */
    void notifyOnAccelerometerChange(Vector3D acceleration);
}
