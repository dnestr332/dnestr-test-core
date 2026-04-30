package com.dnestr.web.interfaces;

import com.microsoft.playwright.options.AriaRole;

public interface Item {

    String getLabel();
    AriaRole getRole();
}
