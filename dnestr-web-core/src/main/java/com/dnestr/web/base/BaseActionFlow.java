package com.dnestr.web.base;

import com.dnestr.web.interfaces.AppPage;
import com.dnestr.web.interfaces.Item;
import com.dnestr.web.interfaces.PageResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseActionFlow<P extends Enum<P> & AppPage> {

    private final PageResolver<P> pageResolver;

    protected BasePage resolve(P page) {
        return pageResolver.resolvePage(page);
    }

    public void click(Item item, P page) {
        resolve(page).click(item);
    }

    public void type(Item item, P page, String value) {
        resolve(page).type(item, value);
    }
}
