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

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import jhinyang.octopus.BaseActivity;
import jhinyang.octopus.R;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.imgGifBackground)
    SimpleDraweeView imgGifBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(HomeActivity.this);
        intializeSetup();
    }

    private void intializeSetup() {

        // TODO load gif image in background
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.raw.giphy).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageRequest.getSourceUri())
                .setAutoPlayAnimations(true)
                .build();
        imgGifBackground.setController(controller);

    }
}
