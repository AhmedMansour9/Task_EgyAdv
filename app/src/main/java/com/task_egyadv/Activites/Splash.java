package com.task_egyadv.Activites;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.task_egyadv.Activites.Login;
import com.task_egyadv.R;
import com.task_egyadv.SharedPrefManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);
        final ImageView logo =  findViewById(R.id.logo);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animationsplash);
        logo.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                String Userid= SharedPrefManager.getInstance(Splash.this).getUserToken();
                if(Userid!=null){
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(getBaseContext(), Login.class);
                    startActivity(i);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

    }
}
