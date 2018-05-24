package track4j.core.file;

/**
 * Native file paths.
 *
 */
enum LibPaths {
    /**
     * Kinect1_64.
     */
    LIB_NAME(".Track4J"),
    /**
     * Native.
     */
    NATIVE_DIR("native");

    private String pathNative;

    LibPaths(final String nativeLib) {
        this.pathNative = nativeLib;
    }

    /**
     * Get the path for native.
     *
     * @return the String path
     */
    public String getDirName() {
        return this.pathNative;
    }

}
