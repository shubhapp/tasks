package com.mmt.shubh.datastore.database.adapter;

import java.util.List;

import rx.Observable;

/**
 * Created by shubham on 12/23/15.
 */
public interface IDataAdapter<M> {

    Observable<List<M>> getAllObserver();

    public List<M> getAll();

    M getById(long id);

    long create(M m);

    long create(List<M> mList);

    long delete(M m);

    long delete(long id);

}
