package track4j.core.recognition;

import track4j.sensor.Sensor;

/**
 * Inteface for Recognition and recording.
 *
 *
 */
public interface Recognition {

    /**
     * Attache the {@link Sensor}.
     *
     * @param sensor
     *            the {@link Sensor}
     */
    void attacheSensor(Sensor sensor);

    /**
     * The sensor is started.
     *
     * @return <code>true</code> if the sensor is started.
     */
    boolean isStarted();

    /**
     * Get the gesture lenght.
     *
     * @return the gesture lenght in frame
     */
    int getGestureLenght();

    /**
     * Start the sensor.
     */
    void startSensor();

    /**
     * Stop the sensor.
     */
    void stopSensor();

    /**
     * Start recording.
     */
    void startRecording();

}