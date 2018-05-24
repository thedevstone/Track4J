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

package track4j.core.view;

import java.io.IOException;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import track4j.core.recognition.Recognition;
import track4j.core.recognition.Recognizer;
import track4j.core.recording.RecordTimer;
import track4j.core.recording.TimerObserver;

/**
 *
 *
 */
@SuppressWarnings("restriction")
public class RecognizerView implements TimerObserver {
    private final Recognition recognizer;
    private boolean recording;

    private Thread record; // NOPMD

    // VIEW
    private Stage stage; // NOPMD
    private Scene scene; // NOPMD
    // CHART
    private LineChart<Number, Number> lineChartX; // NOPMD
    private LineChart<Number, Number> lineChartY; // NOPMD
    private XYChart.Series<Number, Number> xSeries;
    private XYChart.Series<Number, Number> ySeries;
    // CANVAS
    private Canvas canvas;
    private GraphicsContext context;

    @FXML
    private BorderPane recorderPane; // NOPMD
    @FXML
    private JFXButton startButton;
    @FXML
    private JFXButton stopButton;
    @FXML
    private VBox vbox;
    @FXML
    private StackPane canvasStackPane;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton startingButton;

    private static final int COUNTDOWN = 5;

    /**
     * @param recognizer
     *            the {@link Recognizer}
     */
    public RecognizerView(final Recognition recognizer) {
        this.recognizer = recognizer;

        Platform.runLater(() -> {
            final FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            loader.setLocation(this.getClass().getResource(FXMLScreens.HOME.getPath()));
            try {
                this.recorderPane = (BorderPane) loader.load();
            } catch (final IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    private void initialize() { // NOPMD

        // BUTTONS
        this.startingButton.setOnAction(e -> {
            if (!this.recording) {
                this.record = new RecordTimer(RecognizerView.COUNTDOWN, this);
                this.record.start();
                this.xSeries.getData().remove(0, this.xSeries.getData().size() - 1);
                this.ySeries.getData().remove(0, this.ySeries.getData().size() - 1);

            }
        });
        this.startButton.setOnAction(e -> this.recognizer.startSensor());
        this.stopButton.setOnAction(e -> this.recognizer.stopSensor());

        // CANVAS
        this.canvas = new Canvas(this.recorderPane.getMinWidth(), this.recorderPane.getMinHeight());
        this.context = this.canvas.getGraphicsContext2D();
        this.canvasStackPane.getChildren().setAll(this.canvas);

        // CHART
        this.xSeries = new XYChart.Series<>();
        this.ySeries = new XYChart.Series<>();
        this.lineChartX = RecordingFactory.createDerivativeLineChart();
        this.lineChartY = RecordingFactory.createDerivativeLineChart();
        this.lineChartX.getData().add(this.xSeries);
        this.lineChartY.getData().add(this.ySeries);
        this.lineChartX.setTitle("Derivative: X");
        this.lineChartY.setTitle("Derivative: Y");
        HBox.setHgrow(this.lineChartX, Priority.ALWAYS);
        HBox.setHgrow(this.lineChartY, Priority.ALWAYS);
        this.vbox.getChildren().addAll(this.lineChartX, this.lineChartY);

        // CREATING VIEW
        this.stage = new Stage();
        this.scene = new Scene(this.recorderPane);
        this.stage.setScene(this.scene);

        // CLOSING REQUEST
        this.stage.setOnCloseRequest(e -> this.recognizer.stopSensor());

        // SHOWING
        this.stage.show();

    }

    @Override
    public void udpateTimeCount(final int val) {
        Platform.runLater(() -> {
            if (val == 0) {
                this.startingButton.setText("GO");
                this.recognizer.startRecording();
                this.recording = true;
            } else {
                this.startingButton.setText(" " + val);
            }
        });
    }

    /**
     * Update view on frame event.
     *
     * @param frame
     *            the frame
     * @param derivative
     *            the {@link Vector2D} derivative
     * @param path
     *            the {@link Vector2D} gesture path
     */
    public void notifyOnFrameChange(final int frame, final Vector2D derivative, final Vector2D path) {
        Platform.runLater(() -> {
            if (this.recording) {
                this.xSeries.getData().add(new XYChart.Data<Number, Number>(frame, (int) derivative.getX()));
                this.ySeries.getData().add(new XYChart.Data<Number, Number>(frame, (int) derivative.getY()));
                this.progressBar.setProgress((double) frame / this.recognizer.getGestureLenght());
            }
            this.context.fillOval(path.getX() + this.canvas.getWidth() / 2, path.getY() + this.canvas.getHeight() / 2,
                    4, 4);
        });

    }

    /**
     * Update view on feature vector event.
     */
    public void notifyOnFeatureVectorEvent() {
        if (this.recording) {
            this.recording = false;
        }
        Platform.runLater(() -> this.context.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight()));
    }

    /**
    *
    */
    public static void startFxThread() {
        PlatformImpl.startup(() -> {
        });
    }

}
