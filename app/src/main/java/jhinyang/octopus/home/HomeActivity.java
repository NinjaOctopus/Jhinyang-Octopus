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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jhinyang.octopus.BaseActivity;
import jhinyang.octopus.R;
import jhinyang.octopus.adapter.HomeAdapter;
import jhinyang.octopus.data.CookbookDTO;
import jhinyang.octopus.network.NetworkInterface;
import jhinyang.octopus.network.NetworkService;
import retrofit2.Retrofit;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rv_recipe) RecyclerView recyclerRecipe;

    private List<CookbookDTO> tmpCookbook;

    private HomeAdapter adapter;

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
                .subscribe(new Observer<List<CookbookDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CookbookDTO> cookbookDTOS) {
                       for(int i = 2; i < cookbookDTOS.size(); i++) {
                           if(cookbookDTOS.get(i).getName() != null) {
                               tmpCookbook.add(cookbookDTOS.get(i));
                           }
                       }

                       populateView(tmpCookbook);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("responsss", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void intializeSetup() {
        tmpCookbook = new ArrayList<>();
        recyclerRecipe.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        // TODO load gif image in background

    }

    private void populateView(List<CookbookDTO> cookbookDTOS) {
        adapter = new HomeAdapter(cookbookDTOS);
        recyclerRecipe.setAdapter(adapter);
    }
}
