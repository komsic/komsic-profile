package com.example.dell.komsicprofilepage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Animation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotate =  AnimationUtils.loadAnimation(this, R.anim.rotate);

        ImageView githubLogo = (ImageView) findViewById(R.id.github_logo);
        setOnClick(githubLogo, getString(R.string.github_link));

        ImageView slackLogo = (ImageView) findViewById(R.id.slack_logo);
        setOnClick(slackLogo, getString(R.string.slack_link));

        ImageView twitterLogo = (ImageView) findViewById(R.id.twitter_logo);
        setOnClick(twitterLogo, getString(R.string.twitter_link));

        final ImageView mailLogo = (ImageView) findViewById(R.id.mail_logo);

        RelativeLayout mailRelativeLayout = (RelativeLayout) findViewById(R.id.mail_rl);
        mailRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailLogo.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        String[] adds = {getString(R.string.admin_email)};
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + getString(R.string.email)));
                        intent.putExtra(Intent.EXTRA_CC, adds);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        final ImageView phoneLogo = (ImageView) findViewById(R.id.phone_logo);
        RelativeLayout phoneRelativeLayout = (RelativeLayout) findViewById(R.id.phone_rl);
        phoneRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneLogo.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + getString(R.string.phone_no)));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    private void openWebLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void setOnClick(final View view, final String url) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        openWebLink(url);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
    }

}
