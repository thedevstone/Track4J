/**
 *
 */
package demo;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import track4j.core.tracking.JointListener;
import track4j.core.tracking.Tracker;
import track4j.core.tracking.Tracking;
import track4j.core.view.TrackerView;
import track4j.core.view.View;
import track4j.sensor.Joint;
import track4j.sensor.Sensor;
import track4j.sensor.SensorException;
import track4j.sensor.kinect.Kinect;
import track4j.sensor.kinect.KinectSensors;
import track4j.sensor.kinect.KinectVersion;

/**
 * The @link{Demo} class.
 */
public class Demo {

    /**
     * The @link{Demo.java} constructor.
     */
    public Demo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Main.
     *
     * @param args
     *            args
     * @throws SensorException
     *             the sensor exception
     */
    public static void main(final String[] args) throws SensorException {
        final Sensor sensor = new Kinect(Joint.RIGHT_HAND, KinectSensors.SKELETON_ONLY, KinectVersion.KINECT1);
        final Tracking recognizer = Tracker.getInstance();
        recognizer.attacheSensor(sensor);
        final View view = new TrackerView(recognizer);
        recognizer.attacheUI(view);
        recognizer.setOnJointTracked(new JointListener() {

            @Override
            public void onJointTracked(final Vector2D primaryJoint, final Vector2D secondaryJoint) {
                System.out.println(primaryJoint);

            }

            @Override
            public void onDistanceFromStartingJoint(final Vector2D distance) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDerivativeJointTracked(final Vector2D derivativeJoint) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAccelerometerTracked(final Vector3D accelerometer) {
                // TODO Auto-generated method stub

            }
        });
    }

}
