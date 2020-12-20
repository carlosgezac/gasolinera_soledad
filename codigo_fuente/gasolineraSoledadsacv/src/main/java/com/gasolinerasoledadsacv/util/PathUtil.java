package com.gasolinerasoledadsacv.util;

import java.io.File;
import java.io.IOException;

public final class PathUtil {

    public static final String getMainPath() throws IOException {
        return new File(".").getCanonicalPath();
    }

    public static final String getImagesPath() throws IOException {
        return getMainPath().concat("\\images\\");
    }

    public static final String getConfigFilesPath() throws IOException {
        return getMainPath().concat("\\configFiles\\");
    }
}
