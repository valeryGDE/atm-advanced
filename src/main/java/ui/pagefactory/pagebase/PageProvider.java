package main.java.ui.pagefactory.pagebase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import main.java.ui.pagefactory.page.classic.AbstractPage;

abstract class PageProvider<T extends AbstractPage> {

    @Inject
    private Provider<T> basePageProvider;

    public T getPage() {
        return basePageProvider.get();
    }
}
