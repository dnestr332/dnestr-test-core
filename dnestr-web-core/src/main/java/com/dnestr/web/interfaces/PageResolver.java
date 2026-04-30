package com.dnestr.web.interfaces;

import com.dnestr.web.base.BasePage;

public interface PageResolver<P extends Enum<P> & AppPage, T extends BasePage> {

    T resolvePage(P page);
}
