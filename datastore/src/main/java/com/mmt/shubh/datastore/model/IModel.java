package com.mmt.shubh.datastore.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shubham on 12/23/15.
 */
public interface IModel {
    ContentValues toContentValue();

    void parseCursor(Cursor cursor);

    long getId();

    void setId(long id);
}
