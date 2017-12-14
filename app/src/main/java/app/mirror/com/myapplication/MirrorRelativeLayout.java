package app.mirror.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * MirrorRelativeLayout
 * Created by hieudt on 12/14/2017.
 */

public class MirrorRelativeLayout extends RelativeLayout {

  public boolean isFlip = false;

  public void setFlip(boolean flip) {
    isFlip = flip;
  }

  public boolean isFlip() {
    return isFlip;
  }

  public MirrorRelativeLayout(Context context) {
    super(context);
  }

  public MirrorRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MirrorRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    canvas.save();
    if (isFlip) {
      canvas.scale(-1f, 1f, getWidth() / 2f, getHeight() / 2f);
    } else {
      canvas.scale(1f, 1f, getWidth() / 2f, getHeight() / 2f);
    }
    super.dispatchDraw(canvas);
    canvas.restore();
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.save();
    if (isFlip) {
      canvas.scale(-1f, 1f, getWidth() / 2f, getHeight() / 2f);
    } else {
      canvas.scale(1f, 1f, getWidth() / 2f, getHeight() / 2f);
    }
    super.dispatchDraw(canvas);
    canvas.restore();
    super.draw(canvas);
  }
}
