package com.mmt.shubh.owsmtasks.ui.presenter;

import android.content.SharedPreferences;

import com.mmt.shubh.datastore.model.Task;
import com.mmt.shubh.owsmtasks.Constants;
import com.mmt.shubh.owsmtasks.JsonParser;
import com.mmt.shubh.owsmtasks.ui.mvpviews.SplashView;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SplashPresenter extends BasePresenter<Task, SplashView> {

    final SharedPreferences mSharedPreferences;


    final JsonParser mJsonParser;

    @Inject
    public SplashPresenter(SharedPreferences sharedPreferences, JsonParser jsonParser) {
        mSharedPreferences = sharedPreferences;
        mJsonParser = jsonParser;
        Timber.tag(this.getClass().getName());
    }


    public void addSeedData() {
        boolean seedDataStatus = mSharedPreferences.getBoolean(Constants.KEY_SEED_DATA_ADDED, false);
       /* if (!seedDataStatus){
            Timber.i("Seed Data Seed Data already added");
            getMvpView().startHomeActivity();
            return;
        }*/
        mJsonParser.seedData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        mSharedPreferences.edit().putBoolean(Constants.KEY_SEED_DATA_ADDED, true).apply();
                        getMvpView().startHomeActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                        getMvpView().showError("");
                    }

                    @Override
                    public void onNext(Boolean task) {
                        Timber.d("Task added to database with id = %s", task.toString());
                    }
                });

    }

    @Override
    protected void handleError(Throwable throwable) {

    }

    @Override
    protected void handleData(Task task) {

    }
}
