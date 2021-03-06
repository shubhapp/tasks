package com.mmt.shubh.owsmtasks;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.mmt.shubh.owsmtasks.dagger.ApplicationComponent;
import com.mmt.shubh.owsmtasks.dagger.ApplicationModule;
import com.mmt.shubh.owsmtasks.dagger.DaggerApplicationComponent;
import com.mmt.shubh.owsmtasks.utility.Constants;

import timber.log.Timber;

public class TaskApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static TaskApplication get(Context context) {
        return (TaskApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        getComponent();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag(Constants.TAG);

        }
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}