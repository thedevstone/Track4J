/**
 *
 */
package track4j.sensor.kinect;

/**
 * Interface for other kind of kinect SKD.
 */
interface KinectInterfaceAdapter {
    /**
     * Attache kinect instance for notification.
     *
     * @param kinect
     *            the {@link KinectObserver}
     */
    void attacheKinect(KinectObserver kinect);

    /**
     * Start the {@link KinectObserver}.
     *
     * @param kinectStartingSensors
     *            the {@link KinectSensors}
     * @param kinectVersion
     *            the {@link KinectVersion}
     */
    void start();

    /**
     * Print Sensor info.
     *
     * @param the
     *            {@link String} info
     */
    String printKinectInfo();

}