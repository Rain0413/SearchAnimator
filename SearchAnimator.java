package com.dailylifeapp.app.and.dailylife.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Descriptions :动画帮助类
 * api21以上自定义动画，api21以下为渐变透明
 * GitHub : https://github.com/Rain0413
 * Blog   : http://blog.csdn.net/sinat_33680954
 * Created by Rain on 16-12-29.
 */
public class SearchAnimator {

    public static final int ANIMATION_DURATION = 500;

    @TargetApi(21)
    public static void revealShowView(View view, int duration) {
        int cx = view.getWidth();
        int cy = view.getHeight() / 2;

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        if (duration > 0) anim.setDuration(duration);
        else {
            anim.setDuration(ANIMATION_DURATION);
        }

        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(21)
    public static void revealShowView(View view) {
        revealShowView(view, ANIMATION_DURATION);
    }

    @TargetApi(21)
    public static void revealHideView(final View view, int duration, AnimatorListenerAdapter listenerAdapter) {
        int cx = view.getWidth();
        int cy = view.getHeight() / 2;

        float initialRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

        anim.addListener(listenerAdapter);

        if (duration > 0) anim.setDuration(duration);

        else anim.setDuration(ANIMATION_DURATION);

        anim.start();
    }

    @TargetApi(21)
    public static void revealHideView(final View view, AnimatorListenerAdapter listenerAdapter) {
        revealHideView(view, ANIMATION_DURATION, listenerAdapter);
    }

    public static void fadeInView(View view) {
        fadeInView(view, ANIMATION_DURATION);
    }

    public static void fadeInView(View view, int duration) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        ViewCompat.animate(view).alpha(1f).setDuration(duration).setListener(null);
    }


    public static void fadeOutView(View view) {
        fadeOutView(view, ANIMATION_DURATION);
    }

    public static void fadeOutView(final View view, int duration) {
        ViewCompat.animate(view).alpha(0f).setDuration(duration).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                view.setDrawingCacheEnabled(true);
            }

            @Override
            public void onAnimationEnd(View view) {
                view.setVisibility(View.GONE);
                view.setAlpha(1f);
                view.setDrawingCacheEnabled(false);
            }

            @Override
            public void onAnimationCancel(View view) {
            }
        });
    }
}