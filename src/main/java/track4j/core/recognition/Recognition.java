/*******************************************************************************
 * Copyright (c) 2018 Giulianini Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

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