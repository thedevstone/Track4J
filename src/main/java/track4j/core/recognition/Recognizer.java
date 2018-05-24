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

import java.util.Queue;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import track4j.core.codification.Codification;
import track4j.core.codification.Codifier;
import track4j.core.codification.DerivativeCodifier;
import track4j.core.codification.GestureFrameLenght;
import track4j.core.view.RecognizerView;
import track4j.sensor.IllegalSensorStateException;
import track4j.sensor.Sensor;
import track4j.sensor.SensorException;
import track4j.sensor.SensorObserver;

/**
 * The @link{Recorder} class.
 */

public final class Recognizer implements RecognizerObserver, SensorObserver, Recognition {
    private static Recognition instance;
    private Codifier codifier;
    private Sensor sensor;
    private final RecognizerView gui;
    private final GestureFrameLenght gestureLenght;
    private boolean started;

    /**
     * The @link{Recognizer.java} constructor.
     */
    private Recognizer() {
        this(Codification.DERIVATIVE, GestureFrameLenght.TWO_SECONDS);
    }

    private Recognizer(final Codification codificationType, final GestureFrameLenght gestureLenght) {

        if (codificationType.equals(Codification.DERIVATIVE)) {
            this.codifier = new DerivativeCodifier(gestureLenght);
        }

        this.gestureLenght = gestureLenght;
        this.started = false;
        this.codifier.attacheCoreRecognizer(this);

        RecognizerView.startFxThread();
        this.gui = new RecognizerView(this);

    }

    /**
     * Get the instance.
     *
     * @return the {@link Recognition} instance.
     */
    public static Recognition getInstance() {
        synchronized (Recognition.class) {
            if (Recognizer.instance == null) {
                Recognizer.instance = new Recognizer();
            }
        }
        return Recognizer.instance;
    }

    @Override
    public void attacheSensor(final Sensor sensor) {
        this.sensor = sensor;
        this.sensor.attacheRecognizer(this);
    }

    @Override
    public void notifyOnSkeletonChange(final Vector2D primaryJoint, final Vector2D secondaryJoint) {
        this.codifier.codifyOnSkeletonChange(primaryJoint);
    }

    @Override
    public void notifyOnAccelerometerChange(final Vector3D acceleration) {
        // TODO Auto-generated method stub
    }

    @Override
    public void notifyOnFrameChange(final int frame, final Vector2D vector, final Vector2D startingVector) {
        this.gui.notifyOnFrameChange(frame, vector, startingVector);

    }

    @Override
    public void notifyOnFeatureVectorEvent(final Queue<Vector2D> featureVector) {
        this.gui.notifyOnFeatureVectorEvent();
    }

    /**
     * Start record a new gesture.
     */
    @Override
    public void startRecording() {
        this.codifier.resetFrame();
    }

    @Override
    public void startSensor() {
        try {
            this.started = true;
            this.sensor.startSensor();
        } catch (final SensorException e) {
            e.printStackTrace();
        } catch (final IllegalSensorStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopSensor() {
        try {
            this.started = false;
            this.sensor.stopSensor();
        } catch (final SensorException e) {
            e.printStackTrace();
        } catch (final IllegalSensorStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGestureLenght() {
        return this.gestureLenght.getFrameNumber();
    }

    @Override
    public boolean isStarted() {
        return this.started;
    }

}
