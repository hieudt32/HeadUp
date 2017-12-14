package app.mirror.com.myapplication;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private AnimatorSet mSetRightOut;
  private AnimatorSet mSetLeftIn;

  MirrorRelativeLayout mFlipLayoutFront;
  private boolean mIsBackVisible = false;

  Button flip;
  TextView mSpeed;
  ImageView mNavigation;
  int speed = 0;
  final String KM = "Km/h";
  Handler mHandler = new Handler();
  Runnable mThread;
  //  private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_SETTINGS};
//  private static final int CODE = 1001;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mFlipLayoutFront = findViewById(R.id.flip_front);
    mSpeed = findViewById(R.id.speed);
    mNavigation = findViewById(R.id.navigation);
    flip = findViewById(R.id.flip);
    changeCameraDistance();
    loadAnimations();
    mThread = new Runnable() {
      @Override
      public void run() {
        mSpeed.setText(String.valueOf(speed + KM));
        speed++;
        if (speed > 100) speed = 0;
        mHandler.postDelayed(this, 1000);
      }
    };
    flip.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (!mIsBackVisible) {
          mHandler.post(mThread);
          mSetRightOut.setTarget(mFlipLayoutFront);
          mSetLeftIn.setTarget(mFlipLayoutFront);
          mFlipLayoutFront.setFlip(true);
          mSetRightOut.start();
          mSetLeftIn.start();
          mIsBackVisible = true;
          mFlipLayoutFront.invalidate();
        } else {
          mSetLeftIn.setTarget(mFlipLayoutFront);
          mFlipLayoutFront.setFlip(false);
          mSetLeftIn.start();
          mIsBackVisible = false;
          mFlipLayoutFront.invalidate();
        }
      }
    });

  }

  @Override
  protected void onPause() {
    super.onPause();
    mHandler.removeCallbacks(mThread);
  }

  private void changeCameraDistance() {
    int distance = 8000;
    float scale = getResources().getDisplayMetrics().density * distance;
    mFlipLayoutFront.setCameraDistance(scale);
  }

  private void loadAnimations() {
    mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
    mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
  }
}
