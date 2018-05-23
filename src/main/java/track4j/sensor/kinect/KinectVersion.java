/**
 *
 */
package track4j.sensor.kinect;

/**
 * The @link{KinectVersion} class.
 */
public enum KinectVersion {

    /**
     * Kinect first version.
     */
    KINECT1((byte) 0x1),
    /**
     * Kinect second version.
     */
    KINECT2((byte) 0x2);

    private byte version;

    /**
     * The @link{KinectVersion.java} constructor.
     *
     * @param kinectVersion
     *            the kinect version
     */
    KinectVersion(final byte kinectVersion) {
        this.version = kinectVersion;
    }

    /**
     * Get the @link{version}.
     *
     * @return the @link{version}
     */
    public byte getVersion() {
        return this.version;
    }

}
