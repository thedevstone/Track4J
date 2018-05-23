/**
 *
 */
package track4j.sensor.kinect;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import track4j.core.recognition.Recognizer;
import track4j.sensor.IllegalSensorStateException;
import track4j.sensor.Joint;
import track4j.sensor.Sensor;
import track4j.sensor.SensorObserver;

/**
 * The @link{Kinect} class.
 */
public class Kinect implements KinectObserver, Sensor {
    private final KinectAdapter kinectAdapter;
    private SensorObserver recognizer;

    /**
     * The @link{Kinect.java} constructor.
     *
     * @param kinectVersion
     *            the {@link Kinect} version
     */
    public Kinect(final KinectVersion kinectVersion) {
        this.kinectAdapter = new KinectAdapter(Joint.RIGHT_HAND, KinectSensors.SKELETON_ONLY, kinectVersion);
        this.kinectAdapter.attacheKinect(this);
    }

    /**
     * The @link{Kinect.java} constructor.
     *
     * @param primaryJoint
     *            the primary {@link Joint}
     * @param kinectStartingSensors
     *            {@link KinectSensors} the {@link KinectSensors} starting sensors
     * @param kinectVersion
     *            the {@link KinectVersion}
     *
     */
    public Kinect(final Joint primaryJoint, final KinectSensors kinectStartingSensors,
            final KinectVersion kinectVersion) {
        this.kinectAdapter = new KinectAdapter(primaryJoint, kinectStartingSensors, kinectVersion);
        this.kinectAdapter.attacheKinect(this);
    }

    @Override
    public void notifyOnSkeletonChange(final Vector2D primaryJoint, final Vector2D secondaryJoint) {
        this.recognizer.notifyOnSkeletonChange(primaryJoint, secondaryJoint);
    }

    @Override
    public void notifyOnAccelerometerChange(final Vector3D acceleration) {
        this.recognizer.notifyOnAccelerometerChange(acceleration);
    }

    @Override
    public void startSensor() throws IllegalSensorStateException {
        if (Recognizer.getInstance().isStarted()) {
            this.kinectAdapter.start();
        } else {
            throw new IllegalSensorStateException();
        }
    }

    @Override
    public void stopSensor() throws IllegalSensorStateException {
        if (!Recognizer.getInstance().isStarted()) {
            this.kinectAdapter.stop();
        } else {
            throw new IllegalSensorStateException();
        }
    }

    @Override
    public void attacheRecognizer(final SensorObserver recognizer) {
        this.recognizer = recognizer;
    }

}
