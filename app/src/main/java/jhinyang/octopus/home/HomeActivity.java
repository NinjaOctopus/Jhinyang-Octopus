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
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
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
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rv_recipe) RecyclerView recyclerRecipe;
    @BindView(R.id.fb_add_recipe) FloatingActionButton fbAddRecipe;

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

    @OnClick(R.id.fb_add_recipe)
    public void onClickAddRecipe() {
        showBottomSheetAddRecipe();
    }

    private void populateView(List<CookbookDTO> cookbookDTOS) {
        adapter = new HomeAdapter(cookbookDTOS);
        recyclerRecipe.setAdapter(adapter);
    }

    private void showBottomSheetAddRecipe() {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(HomeActivity.this, R.style
                .BottomSheetDialog);
        View sheetView = getLayoutInflater().inflate(R.layout.bottomsheet_add_recipe, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();

        final EditText editRecipeImage = sheetView.findViewById(R.id.et_image);
        final EditText editRecipeName = sheetView.findViewById(R.id.et_recipe_name);
        final EditText editRecipeTarget = sheetView.findViewById(R.id.et_target);
        final EditText editRecipeAptFor = sheetView.findViewById(R.id.et_apt_for);
        final EditText editRecipeContent = sheetView.findViewById(R.id.et_contents);
        final EditText editRecipeCategory = sheetView.findViewById(R.id.et_category);
        final EditText editRecipeCost = sheetView.findViewById(R.id.et_price);

        Button btnAddRecipe = sheetView.findViewById(R.id.btn_add_recipe);

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Send post request here
                Retrofit retrofit = NetworkService.getClient(HomeActivity.this);

                NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

                CookbookDTO cookbookDTO = new CookbookDTO();
                cookbookDTO.setImage(editRecipeImage.getText().toString());
                cookbookDTO.setName(editRecipeName.getText().toString());
                cookbookDTO.setTarget(editRecipeTarget.getText().toString());
                cookbookDTO.setAptFor(editRecipeAptFor.getText().toString());
                cookbookDTO.setCategory(editRecipeCategory.getText().toString());
                cookbookDTO.setFoodContents(editRecipeContent.getText().toString());
                cookbookDTO.setPrice(Double.parseDouble(editRecipeCost.getText().toString()));

                networkInterface.submitRecipe(cookbookDTO)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<CookbookDTO>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<CookbookDTO> cookbookDTO) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                mBottomSheetDialog.dismiss();
            }
        });
    }
}
