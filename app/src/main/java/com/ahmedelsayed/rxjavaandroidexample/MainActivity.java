package com.ahmedelsayed.rxjavaandroidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> listObservable =
                Observable.just("Maroon", "Red", "Orange", "Yellow", "Green", "White", "Black", "Blue", "Navy");


        listObservable.subscribeOn(Schedulers.newThread())
                //Observable runs on new background thread.
                .observeOn(AndroidSchedulers.mainThread())
                //Observer will run on main UI thread.
                .subscribe(new DisposableObserver<String>()
                        //Subscribe the observer
                {
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: New data received:  " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error received: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All data emitted.");
                    }
                });
    }
}
