package track4j.core.view;

import javax.swing.text.html.CSS;

import javafx.fxml.FXML;

/**
 * Enumerator of the .fxml resources for the view.
 *
 */
public enum FXMLScreens {

    /**
     * Menu {@link FXMLScreens} and {@link CSS}.
     */
    HOME("/screens/ScreenRecorder.fxml", "/sheets/ScreenRecorder.css");

    private final String resourcePath;
    private final String cssPath;

    FXMLScreens(final String path, final String styleSheetPath) {
        this.resourcePath = path;
        this.cssPath = styleSheetPath;
    }

    /**
     * Get the path of the {@link FXML}.
     *
     * @return full qualified path of the {@link FXML}
     */
    public String getPath() {
        return this.resourcePath;
    }

    /**
     * Get the path of the {@link CSS}.
     *
     * @return full qualified path of the {@link CSS}
     */
    public String getCssPath() {
        return this.cssPath;
    }
}
