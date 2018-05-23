/**
 *
 */
package track4j.sensor;

/**
 * The @link{SensorException} class.
 */
public class SensorException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * The @link{SensorException.java} constructor.
     */
    public SensorException() {
        super("A sensor has encountered an error. Please check the configuration or the sensor");
    }

    /**
     * The @link{SensorException.java} constructor.
     *
     * @param message
     *            the exception message.
     */
    public SensorException(final String message) {
        super(message);

    }

}
