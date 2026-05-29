package com.dnestr.web.resolvers;

import com.dnestr.web.pages.PageElement;

public interface BaseContextResolver {

    String resolveInput(PageElement element, String value);
    String resolveExpectedValue(Object key, String value);
    int resolveRowIndex(String index);
}
