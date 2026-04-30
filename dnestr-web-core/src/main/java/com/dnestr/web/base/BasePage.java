package com.dnestr.web.base;

import com.dnestr.web.actions.ElementActions;
import com.dnestr.web.interfaces.Item;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.AriaRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BasePage {

    protected final Page page;
    protected final ElementActions elementActions;

    protected Locator byRole(AriaRole role, String name) {
        return page.getByRole(role, new Page.GetByRoleOptions().setName(name));
    }

    protected Locator byRole(AriaRole role, String name, boolean exact) {
        return page.getByRole(role, new Page.GetByRoleOptions()
                .setName(name)
                .setExact(exact));
    }

    protected Locator byText(String visibleText) {
        return page.getByText(visibleText);
    }

    public Locator locator(Item item) {
        return byRole(item.getRole(), item.getLabel());
    }

    public void click(Item button) {
        elementActions.click(locator(button));
    }

    public void type(Item field, String text) {
        elementActions.type(locator(field), text);
    }
}
