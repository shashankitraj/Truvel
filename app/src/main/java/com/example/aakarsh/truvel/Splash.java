package com.example.aakarsh.truvel;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.aakarsh.truvel.R.anim.frombottom;
import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
    Toolbar toolbar;

    ImageView logo;//v,logoTra,logoEl;
    Animation frombottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



//        v=findViewById(R.id.v);
//        logoTra=findViewById(R.id.tra);
//        logoEl=findViewById(R.id.el);
        logo=findViewById(R.id.logo);
//        d=AnimationUtils.loadAnimation(this,R.anim.dd);
//        x=AnimationUtils.loadAnimation(this,R.anim.dd);
//
        frombottom= AnimationUtils.loadAnimation(this,R.anim.dd);
        logo.setAnimation(frombottom);
//        logoTra.setAnimation(d);
//        logoEl.setAnimation(x);
//        try {
//            sleep(400);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        v.setAnimation(frombottom);


        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(Splash.this,MainActivity.class));
                }
            }
        });
        t.start();
    }
}
