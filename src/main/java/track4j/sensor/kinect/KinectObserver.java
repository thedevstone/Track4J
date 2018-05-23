/**
 *
 */
package track4j.sensor.kinect;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * The @link{ObservableKinect} class. Kinect Adapter notify Kinect throw this interface.
 */
public interface KinectObserver {
    /**
     * Notify Kinect on skeleton change.
     *
     * @param primaryJoint
     *            the {@link Vector2D} primary joint used in gesture recognition
     * @param secondaryJoint
     *            the {@link Vector2D} secondary joint used in gesture recognition
     */
    void notifyOnSkeletonChange(Vector2D primaryJoint, Vector2D secondaryJoint);

    /**
     * Notify Kinect on accelerometer change.
     *
     * @param acceleration
     *            the {@link Vector3D} acceleration
     */
    void notifyOnAccelerometerChange(Vector3D acceleration);
}
