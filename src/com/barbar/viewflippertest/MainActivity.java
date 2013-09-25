package com.barbar.viewflippertest;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ViewAnimator;

public class MainActivity extends Activity {
    public volatile MainActivity pMainActivity = null;
    public volatile ObjectAnimator ballRoller = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pMainActivity = this;
        setContentView(R.layout.activity_main);
        final ViewAnimator pages = (ViewAnimator) findViewById(R.id.pages);
        Button prev = (Button) findViewById(R.id.prev);
        Button next = (Button) findViewById(R.id.next);
        // AnimationSet slideAndScale = new AnimationSet(true);
        // TranslateAnimation slide = new
        // TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1f,
        // Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1f,
        // Animation.RELATIVE_TO_PARENT, 0);
        // ScaleAnimation scale = new ScaleAnimation(5, 1, 5, 1);
        // slideAndScale.addAnimation(slide);
        // slideAndScale.addAnimation(scale);
        // slideAndScale.setDuration(3000);
        // pages.setInAnimation(slideAndScale);

        View rollingBall = findViewById(R.id.rollingball);
        ballRoller = ObjectAnimator.ofFloat(rollingBall, "TranslationX", 0, 400);
        ballRoller.setDuration(3000);
        ballRoller.setRepeatMode(ObjectAnimator.REVERSE);
        ballRoller.setRepeatCount(ObjectAnimator.INFINITE);
        ballRoller.start(); // always running when view showing

        final View bouncingBallView = findViewById(R.id.bouncingball);
        ValueAnimator ballBouncer = ValueAnimator.ofInt(0, 40);

        ballBouncer.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator ballBouncer) {
                final int animatedValue = (Integer) ballBouncer.getAnimatedValue();
                bouncingBallView.post(new Runnable() {
                    public void run() {
                        bouncingBallView.setPadding(bouncingBallView.getPaddingLeft(), 40 - animatedValue, bouncingBallView.getPaddingRight(), animatedValue);
                        bouncingBallView.invalidate();
                    }
                });

            }
        });

        ballBouncer.setDuration(1000);
        ballBouncer.setRepeatMode(ValueAnimator.REVERSE);
        ballBouncer.setRepeatCount(ValueAnimator.INFINITE);
        ballBouncer.setInterpolator(new DecelerateInterpolator());

        // ValueAnimator.setFrameDelay(50);
        ballBouncer.start();

        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // we have overrode this by AnimationSet slideAndScale
                // pages.setInAnimation(pMainActivity, R.anim.slideout_prv);
                // pages.setOutAnimation(pMainActivity, R.anim.slidein_prv);
                pages.showPrevious();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // pages.setInAnimation(pMainActivity, R.anim.slidein_nxt);
                // pages.setOutAnimation(pMainActivity, R.anim.slideout_nxt);
                pages.showNext();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
