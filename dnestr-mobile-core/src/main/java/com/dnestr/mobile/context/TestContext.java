package com.dnestr.mobile.context;

import com.dnestr.mobile.enums.MobilePlatform;

public final class TestContext {

    private static MobilePlatform platform;

    private TestContext() {}

    public static MobilePlatform getPlatform() {
        if (platform == null) {
            String raw = System.getProperty("platform");

            if (raw == null) throw new IllegalStateException("Platform is not defined");

            String actual = raw.substring(0, raw.indexOf("_"));
            platform = MobilePlatform.valueOf(actual.toUpperCase());
        }
        return platform;
    }

    public static boolean isCi() {
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }

    public static boolean isAndroid() {
        return getPlatform() == MobilePlatform.ANDROID;
    }

    public static boolean isIos() {
        return getPlatform() == MobilePlatform.IOS;
    }
}
