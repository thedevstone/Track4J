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
package track4j.core.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

/**
 * Class dedicated to File managing.
 */
final class FileManager {

    private static String libDir;

    /**
     * Constructor.
     */
    private FileManager() {
        // TODO Auto-generated constructor stub
    }

    private static void addDir(final String s) throws IOException {
        try {
            // This enables the java.library.path to be modified at runtime
            // From a Sun engineer at http://forums.sun.com/thread.jspa?threadID=707176
            //
            final Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);
            final String[] paths = (String[]) field.get(null);
            for (final String path : paths) {
                if (s.equals(path)) {
                    return;
                }
            }
            final String[] tmp = new String[paths.length + 1];
            System.arraycopy(paths, 0, tmp, 0, paths.length);
            tmp[paths.length] = s;
            field.set(null, tmp);
            System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + s);
        } catch (final IllegalAccessException e) {
            throw new IOException("Failed to get permissions to set library path");
        } catch (final NoSuchFieldException e) {
            throw new IOException("Failed to get field handle to set library path");
        }
    }

    // LIST FILE IN NATIVE
    private static List<String> getResourceFiles(final String path) throws IOException {
        final List<String> filenames = new ArrayList<>();
        try (InputStream in = FileManager.class.getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) { // NOPMD
                filenames.add(resource);
            }
        }
        return filenames;
    }

    /**
     * Put the natives in filesystem.
     */
    public static void loadNativeLibraries() {
        FileManager.createLibSubFolder(LibPaths.NATIVE_DIR);

        List<String> files = null;
        try {
            files = FileManager.getResourceFiles("/native");
        } catch (final IOException e1) {
            e1.printStackTrace();
        }

        for (final String file : files) {
            final String libToLoad = FileManager.crateOrGetLibDir() + OsUtils.getSeparator()
                    + LibPaths.NATIVE_DIR.getDirName() + OsUtils.getSeparator() + file;
            try {
                Files.copy(new File(FileManager.class.getResource("/native/" + file).getFile()), new File(libToLoad));

            } catch (final IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static void createLibSubFolder(final LibPaths path) {
        final String tempPath = FileManager.crateOrGetLibDir() + OsUtils.getSeparator() + path.getDirName();
        if (!java.nio.file.Files.exists(java.nio.file.Paths.get(tempPath))) {
            try {
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get(tempPath));
            } catch (final IOException e) {
                System.out.println("Cannot create lib directory");
            }
        }
        if (path.equals(LibPaths.NATIVE_DIR)) {
            try {
                FileManager.addDir(tempPath);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the library directory.
     *
     * @return the directory
     */
    public static String crateOrGetLibDir() {
        if (FileManager.libDir == null) {
            FileManager.libDir = System.getProperty("user.home") + OsUtils.getSeparator()
                    + LibPaths.LIB_NAME.getDirName();
            if (!java.nio.file.Files.exists(java.nio.file.Paths.get(FileManager.libDir))) {
                try {
                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get(FileManager.libDir));
                } catch (final IOException e) {
                    System.out.println("Cannot create lib directory");
                }
            }
        }
        return FileManager.libDir;
    }

}
