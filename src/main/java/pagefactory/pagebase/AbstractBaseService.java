package main.java.pagefactory.pagebase;

import main.java.pagefactory.page.DefaultPage;

public abstract class AbstractBaseService<T extends DefaultPage> extends AbstractComplexService<T> implements IPageVerifier {

    public String toString() {
        return getPage().getPageName();
    }
}
