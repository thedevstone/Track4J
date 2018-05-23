package test;

import track4j.core.recognition.Recognition;
import track4j.core.recognition.Recognizer;
import track4j.sensor.Joint;
import track4j.sensor.Sensor;
import track4j.sensor.SensorException;
import track4j.sensor.kinect.Kinect;
import track4j.sensor.kinect.KinectSensors;
import track4j.sensor.kinect.KinectVersion;

/**
 * Testing class.
 *
 */
public final class Test {
    /**
     * The @link{Test.java} constructor.
     */
    private Test() {
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
        final Sensor sensor = new Kinect(Joint.LEFT_HAND, KinectSensors.SKELETON_ONLY, KinectVersion.KINECT1);
        final Recognition recognizer = Recognizer.getInstance();
        recognizer.attacheSensor(sensor);
    }

}
