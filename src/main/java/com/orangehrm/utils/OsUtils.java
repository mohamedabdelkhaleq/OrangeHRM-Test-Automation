package com.orangehrm.utils;

import com.orangehrm.utils.dataReader.PropertyReader;

public class OsUtils {
    public enum OS {
        WINDOWS,
        LINUX,
        OTHER;
    }

    public static OS getOS() {
        String osName = PropertyReader.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            return OS.WINDOWS;
        }
        if (osName.contains("linux")) {
            return OS.LINUX;
        }
        return OS.OTHER;
    }
}
