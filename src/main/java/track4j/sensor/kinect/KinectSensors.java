/**
 *
 */
package track4j.sensor.kinect;

/**
 * The @link{KinectSensorType} class.
 */
public enum KinectSensors {
    /**
     * Kinect type of sensor.
     */
    // CHECKSTYLE:OFF
    SKELETON_ONLY(0x20), SKELETON_DEPTH(0x20 | 0x8), ALL_SENSORS(0x01 | 0x02 | 0x08 | 0x20 | 0x100 | 0x1000);

    private final int type;

    /**
     * The @link{KinectSensorType.java} constructor.
     *
     * @param kinectType
     *            the kinect type
     */
    KinectSensors(final int kinectType) {
        this.type = kinectType;
    }

    /**
     * Get the @link{type}.
     *
     * @return the @link{type}
     */
    public int getStartingSensors() {
        return this.type;
    }

}
