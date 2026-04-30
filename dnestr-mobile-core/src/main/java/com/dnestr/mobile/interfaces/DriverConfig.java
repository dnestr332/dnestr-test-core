package com.dnestr.mobile.interfaces;

import com.dnestr.mobile.enums.MobilePlatform;

import java.net.URL;

public interface DriverConfig {

    MobilePlatform platform();
    URL url();
}
