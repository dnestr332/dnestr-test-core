package com.dnestr.web.base;

import com.dnestr.core.assertions.Softly;
import com.dnestr.core.enums.AssertionState;
import com.dnestr.core.enums.ButtonState;
import com.dnestr.core.enums.VisibleState;
import com.dnestr.web.actions.ElementActions;
import com.dnestr.web.assertions.Hardly;
import com.dnestr.web.interfaces.AppPage;
import com.dnestr.web.interfaces.Item;
import com.dnestr.web.interfaces.PageResolver;
import com.microsoft.playwright.Locator;
import lombok.RequiredArgsConstructor;

import java.util.function.BooleanSupplier;

@RequiredArgsConstructor
public abstract class BaseAssertionFlow<P extends Enum<P> & AppPage, T extends BasePage> {

    private final PageResolver<P, T> pageResolver;
    private final ElementActions elementActions;

    protected T resolve(P page) {
        return pageResolver.resolvePage(page);
    }

    private void assertBool(AssertionState state, BooleanSupplier condition, String message) {
        switch (state) {
            case STRICTLY -> Hardly.isTrue(condition.getAsBoolean(), message);
            case SOFTLY   -> Softly.isTrue(condition.getAsBoolean(), message);
        }
    }

    public void verifyVisible(P page, Item item, AssertionState assertion, VisibleState visible) {
        Locator locator = resolve(page).locator(item);

        BooleanSupplier condition = switch (visible) {
            case VISIBLE     -> () -> locator.count() > 0 && locator.first().isVisible();
            case NOT_VISIBLE -> () -> locator.count() == 0 || !locator.first().isVisible();
        };

        String msg = "%s should be %s".formatted(item, visible.name().replace("_", " ").toLowerCase());
        assertBool(assertion, condition, msg);
    }

    public void verifyEnabled(P page, Item item, AssertionState assertion, ButtonState state) {
        Locator locator = resolve(page).locator(item);

        BooleanSupplier condition = switch (state) {
            case ENABLED  -> locator::isEnabled;
            case DISABLED -> locator::isDisabled;
        };

        String msg = "%s should be %s".formatted(item, state);
        assertBool(assertion, condition, msg);
    }

    public void verifyTextEquals(P page, Item item, AssertionState assertion, String expected) {
        Locator locator = resolve(page).locator(item);

        BooleanSupplier condition = () -> elementActions
                .text(locator).replace("’", "'")
                .equals(expected);

        String msg = "%s text should be <%s>".formatted(item, expected);
        assertBool(assertion, condition, msg);
    }

    public void verifyTextContains(P page, Item item, AssertionState assertion, String partial) {
        Locator locator = resolve(page).locator(item);

        BooleanSupplier condition = () -> elementActions
                .text(locator)
                .contains(partial);

        String msg = "%s should contain <%s>".formatted(item, partial);
        assertBool(assertion, condition, msg);
    }

    public void verifyReadOnly(P page, Item item, AssertionState assertion) {
        Locator locator = resolve(page).locator(item);
        BooleanSupplier condition = () -> locator.isDisabled() || !locator.isEditable();

        String msg = "%s should be read-only".formatted(item);
        assertBool(assertion, condition, msg);
    }
}
