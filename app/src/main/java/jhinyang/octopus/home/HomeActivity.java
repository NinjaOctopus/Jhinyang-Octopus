/**
 *
 *  HomeActivity.java
 *
 *  Created by Ashutosh Sharma on 08/15/18.
 *  Copyright © 2018 Jhinyang Food Ltd.  All rights reserved.
 *
 */

package jhinyang.octopus.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jhinyang.octopus.BaseActivity;
import jhinyang.octopus.R;
import jhinyang.octopus.data.CookbookDTO;
import jhinyang.octopus.network.NetworkInterface;
import jhinyang.octopus.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends BaseActivity implements Callback<List<CookbookDTO>> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(HomeActivity.this);
        intializeSetup();

        NetworkService networkClass = new NetworkService();
        Retrofit retrofit = networkClass.start();

        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

        Call<List<CookbookDTO>> resultDTOCall = networkInterface.getRecipes();
        resultDTOCall.enqueue(this);
    }

    private void intializeSetup() {

        // TODO load gif image in background

    }

    @Override
    public void onResponse(Call<List<CookbookDTO>> call, Response<List<CookbookDTO>> response) {
        Log.d("responsss", response.body().toString());
    }

    @Override
    public void onFailure(Call<List<CookbookDTO>> call, Throwable t) {
        Log.d("responsss", t.getMessage());
    }
}
