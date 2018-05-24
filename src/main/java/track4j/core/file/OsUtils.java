package track4j.core.file;

import java.util.Locale;

/**
 *
 *
 */
final class OsUtils {

    private OsUtils() {

    }

    /**
     * types of Operating Systems.
     */
    public enum OSType {
        /**
         * Systems.
         */
        Windows, MacOS, Linux, Other
    };

    // cached result of OS detection
    private static OSType detectedOS; // NOPMD
    private static String separator; // NOPMD

    /**
     * detect the operating system from the os.name System property and cache the result.
     *
     * @return the operating system detected
     */
    public static OSType getOperatingSystemType() {
        if (OsUtils.detectedOS == null) {
            final String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if (os.indexOf("mac") >= 0 || os.indexOf("darwin") >= 0) {
                OsUtils.detectedOS = OSType.MacOS;
            } else if (os.indexOf("win") >= 0) {
                OsUtils.detectedOS = OSType.Windows;
            } else if (os.indexOf("nux") >= 0) {
                OsUtils.detectedOS = OSType.Linux;
            } else {
                OsUtils.detectedOS = OSType.Other;
            }
        }
        return OsUtils.detectedOS;
    }

    /**
     * Get the system separator.
     *
     * @return the separator
     */
    public static String getSeparator() {
        if (OsUtils.separator == null) {
            OsUtils.separator = System.getProperty("file.separator");
        }
        return OsUtils.separator;
    }
}
