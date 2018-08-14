package jhinyang.octopus.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import jhinyang.octopus.BaseActivity;
import jhinyang.octopus.R;
import jhinyang.octopus.utils.GifImageView;

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
