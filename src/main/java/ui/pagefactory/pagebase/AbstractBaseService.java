package main.java.ui.pagefactory.pagebase;

import main.java.ui.pagefactory.page.classic.DefaultPage;

public abstract class AbstractBaseService<T extends DefaultPage> extends PageProvider<T> implements IPageVerifier {

    public String toString() {
        return getPage().getPageName();
    }
}