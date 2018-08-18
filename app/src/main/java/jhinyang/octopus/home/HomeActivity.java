/**
 *
 *  HomeActivity.java
 *
 *  Created by Ashutosh Sharma on 08/15/18.
 *  Copyright © 2018 Jhinyang Food Ltd.  All rights reserved.
 *
 */

package jhinyang.octopus.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jhinyang.octopus.BaseActivity;
import jhinyang.octopus.R;
import jhinyang.octopus.data.CookbookDTO;
import jhinyang.octopus.network.NetworkInterface;
import jhinyang.octopus.network.NetworkService;
import retrofit2.Retrofit;

public class HomeActivity extends BaseActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(HomeActivity.this);
        intializeSetup();

        Retrofit retrofit = NetworkService.getClient(HomeActivity.this);

        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

        networkInterface.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<CookbookDTO>>() {
                    @Override
                    public void onNext(List<CookbookDTO> cookbookDTOS) {
                        Log.d("responsss", cookbookDTOS.size() + "");
                        populateView(cookbookDTOS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void intializeSetup() {

        // TODO load gif image in background

    }

    private void populateView(List<CookbookDTO> cookbookDTOS) {

    }
}
