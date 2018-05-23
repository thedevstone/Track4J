package track4j.core.view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

/**
 * Factory for recording view.
 *
 */
final class RecordingFactory {
    private RecordingFactory() {
    }

    public static LineChart<Number, Number> createDerivativeLineChart() {
        final NumberAxis x2Axis = new NumberAxis("Space", -100, 100, 1);
        final NumberAxis x1Axis = new NumberAxis("Time", 0, 60, 1);
        final LineChart<Number, Number> lineChart = new LineChart<>(x1Axis, x2Axis);
        lineChart.getYAxis().setAutoRanging(false);
        lineChart.getYAxis().setAutoRanging(false);
        lineChart.setAnimated(false);
        return lineChart;
    }
}