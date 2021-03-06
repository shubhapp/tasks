package com.mmt.shubh.owsmtasks.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mmt.shubh.datastore.database.DatabaseOpenHelper;
import com.mmt.shubh.datastore.database.TaskContract;
import com.mmt.shubh.datastore.database.adapter.TaskBoardDataAdapter;
import com.mmt.shubh.datastore.database.adapter.TaskDataAdapter;
import com.mmt.shubh.owsmtasks.utility.Constants;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    protected BriteDatabase mBriteDatabase;

    public ApplicationModule(Application application) {
        mApplication = application;
        mBriteDatabase = SqlBrite.create().wrapDatabaseHelper(new DatabaseOpenHelper(mApplication), Schedulers.io());
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    BriteDatabase provideDatabase() {
        return mBriteDatabase;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return mApplication.getSharedPreferences(Constants.PREFS_NAME, 0);
    }

    @Provides
    @Singleton
    TaskDataAdapter provideTaskDataAdapter() {
        return new TaskDataAdapter(mBriteDatabase);
    }

    @Provides
    @Singleton
    TaskBoardDataAdapter provideTaskBoardDataAdapter() {
        return new TaskBoardDataAdapter(mBriteDatabase, TaskContract.TASK_BOARD_TABLE_NAME);
    }

}