package com.xiaochendev.lib;

import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.TextField;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.agp.utils.TextTool;
import ohos.app.Context;

/**
 * Created by tanfujun on 8/18/16.
 *
 * <p>Be careful not to use the original hint of EditText when using this control. Use the in-control method instead.
 */
public class HintAnimEditText extends TextField implements Component.DrawTask {

    CharSequence mAnimHintString = "";
    Paint mHintPaint;
    float mDefaultAlpha;
    float mDefaultTextSize;
    float mTextLength;

    public HintAnimEditText(Context context) {
        super(context);
        init();
    }

    public HintAnimEditText(Context context, AttrSet attrs) {
        super(context, attrs);
        init();
    }

    public HintAnimEditText(Context context, AttrSet attrs, int defStyleAttr) {
        super(context, attrs, "defStyleAttr");
        init();
    }

    private void init() {
        mHintPaint = new Paint();
        mHintPaint.setAntiAlias(true);
        mHintPaint.setColor(getHintColor());
        mHintPaint.setAlpha((int) getAlpha());
        mHintPaint.setColor(Color.GRAY);
        mHintPaint.setTextSize(getTextSize());
        mHintPaint.setTextAlign(TextAlignment.CENTER);
        mDefaultTextSize = getTextSize();
        mDefaultAlpha = mHintPaint.getAlpha();
        addDrawTask(this);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        if (TextTool.isNullOrEmpty(getText())) {
            if (Float.floatToRawIntBits(mTextLength) == 0) {
                mTextLength = mHintPaint.measureText(mAnimHintString.toString());
            }
            canvas.drawText(mHintPaint, mAnimHintString.toString(), getEstimatedWidth() / 2f,
                    (float) (getEstimatedHeight() * 0.75));
        }
    }

    /**
     * Modify hint text.
     *
     * @param hint hint
     */
    public void setHintString(final CharSequence hint) {
        mAnimHintString = hint;
        if (mAnimHintString != null) {
            mTextLength = mHintPaint.measureText(mAnimHintString.toString());
        } else {
            mTextLength = 0;
        }
        invalidate();
    }

    /**
     *  Use animation to switch hints.
     *
     * @param hint hint
     */
    public void changeHintWithAnim(final CharSequence hint) {
        AnimatorValue rollPlayAnim = new AnimatorValue();
        rollPlayAnim.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            float prevValue = 0;
            boolean checkInput = false;
            float alteredAnimatedValue = 0;
            public void onUpdate(AnimatorValue animatorValue, float animatedValue) {
                checkInput = prevValue > animatedValue ? !checkInput : checkInput;
                alteredAnimatedValue = animatedValue;
                if (!checkInput) {
                    alteredAnimatedValue = 1 - animatedValue;
                }
                int textsize = (int) (mDefaultTextSize * (alteredAnimatedValue));
                mHintPaint.setTextSize(textsize);
                mHintPaint.setAlpha(alteredAnimatedValue);
                invalidate();
                prevValue = animatedValue;
            }
        });
        rollPlayAnim.setCurveType(Animator.CurveType.LINEAR);
        rollPlayAnim.setDuration(250);
        rollPlayAnim.setLoopedCount(1);
        rollPlayAnim.setDelay(30);

        rollPlayAnim.setStateChangedListener(new Animator.StateChangedListener() {
            @Override
            public void onStart(Animator animator) {
                mTextLength = mHintPaint.measureText(mAnimHintString.toString());
            }

            @Override
            public void onStop(Animator animator) {
                //Need not implement
            }

            @Override
            public void onCancel(Animator animator) {
                //Need not implement
            }

            @Override
            public void onEnd(Animator animator) {
                mAnimHintString = hint;
            }

            @Override
            public void onPause(Animator animator) {
                //Need not implement
            }

            @Override
            public void onResume(Animator animator) {
                //Need not implement
            }
        });
        rollPlayAnim.setLoopedListener(new Animator.LoopedListener() {
            @Override
            public void onRepeat(Animator animator) {
                mAnimHintString = hint;
                mTextLength = mHintPaint.measureText(mAnimHintString.toString());
            }
        });
        rollPlayAnim.start();
    }

    public CharSequence getAnimHintString() {
        return mAnimHintString;
    }
}
