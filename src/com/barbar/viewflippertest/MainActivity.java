package com.barbar.viewflippertest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ViewAnimator;

public class MainActivity extends Activity {
    public volatile MainActivity pMainActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pMainActivity = this;
        setContentView(R.layout.activity_main);
        final ViewAnimator pages = (ViewAnimator) findViewById(R.id.pages);
        Button prev = (Button) findViewById(R.id.prev);
        Button next = (Button) findViewById(R.id.next);
        AnimationSet slideAndScale = new AnimationSet(true);
        TranslateAnimation slide = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 1f,
                        Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0
                        );

        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pages.setInAnimation(pMainActivity, R.anim.slideout_prv);
                pages.setOutAnimation(pMainActivity, R.anim.slidein_prv);
                pages.showPrevious();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pages.setInAnimation(pMainActivity, R.anim.slidein_nxt);
                pages.setOutAnimation(pMainActivity, R.anim.slideout_nxt);
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
