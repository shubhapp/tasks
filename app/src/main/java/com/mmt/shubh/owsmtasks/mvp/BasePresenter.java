package com.mmt.shubh.owsmtasks.mvp;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMVPView().
 */
public abstract class BasePresenter<V extends MVPView> implements Presenter<V> {

    private WeakReference<V> mMvpView;

    public BasePresenter() {
        Timber.tag(this.getClass().getName());
    }

    @Override
    public void attachView(V mvpView) {
        mMvpView = new WeakReference<V>(mvpView);
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        checkViewAttached();
        return mMvpView.get();
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MVPView) before" +
                    " requesting data to the Presenter");
        }
    }
}
