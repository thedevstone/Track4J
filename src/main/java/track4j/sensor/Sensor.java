/**
 *
 */
package track4j.sensor;

/**
 * The @link{Sensor} class. It rapresents a general sensor that can be attached to the framework.
 * <p>
 * Sensors may be Kinect, LipMotion ecc. If your sensor is not present please contact me.
 */
public interface Sensor {
    /**
     * Start the sensor with the default configuration setted in the constructor specific sensor.
     *
     * @throws SensorException
     *             if the sensor encurres in problems during starting phase.
     *
     * @throws IllegalSensorStateException
     *             if sensor is started outside recognition
     */
    void startSensor() throws SensorException, IllegalSensorStateException;

    /**
     * Stop the sensor with the default configuration setted in the constructor specific sensor.
     *
     * @throws SensorException
     *             if the sensor encurres in problems during stopping phase.
     *
     * @throws IllegalSensorStateException
     *             if sensor is started outside recognition
     */
    void stopSensor() throws SensorException, IllegalSensorStateException;

    /**
     * Attache the recognizer.
     *
     * @param recognizer
     *            the {@link SensorObserver}
     */
    void attacheRecognizer(SensorObserver recognizer);
}
