package main.java.ui.pagefactory.pagebase;

import main.java.ui.pagefactory.page.DefaultPage;

public abstract class AbstractBaseService<T extends DefaultPage> extends AbstractComplexService<T> implements IPageVerifier {

    public String toString() {
        return getPage().getPageName();
    }
}
