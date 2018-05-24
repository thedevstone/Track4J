package track4j.core.view;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface View {

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
    void notifyOnFrameChange(int frame, Vector2D derivative, Vector2D path);

    /**
     * Update view on feature vector event.
     */
    void notifyOnFeatureVectorEvent();

    /**
     * Set the frame Length.
     *
     * @param length
     *            the length
     */
    void setFrameLength(int length);

}