package com.outsourcing.llxpb.ui.customView.CustomDialog;
import android.view.View;
import com.youth.banner.transformer.ABaseTransformer;
import com.nineoldandroids.view.ViewHelper;
public class FadeSlideZoomInTransformer extends ABaseTransformer {
    @Override
    protected void onTransform(View page, float position) {
//        ViewHelper.setTranslationX(page, 0);
        if (position <= -1.0F || position >= 1.0F) {
            ViewHelper.setAlpha(page, 0.0F);
        } else if (position == 0.0F) {
            ViewHelper.setAlpha(page, 1.0F);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            ViewHelper.setAlpha(page, 1.0F - Math.abs(position));
        }

        float scale = position < 0 ? position + 1f : Math.abs(1f - position);
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setPivotX(page.getWidth() * 0.5f);
        page.setPivotY(page.getHeight() * 0.5f);
        page.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
    }
}
