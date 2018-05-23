/**
 *
 */
package track4j.sensor;

/**
 * The @link{IllegalSensorStateException} class.
 */
public class IllegalSensorStateException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The @link{IllegalSensorStateException.java} constructor.
     */
    public IllegalSensorStateException() {
        super("Cannot start the sensor outside recognizer. Please attache it to recognizer and start the recognition");
    }

}
