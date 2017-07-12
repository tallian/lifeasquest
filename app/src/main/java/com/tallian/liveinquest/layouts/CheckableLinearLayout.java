package com.tallian.liveinquest.layouts;

import android.*;
import android.content.*;
import android.util.*;
import android.widget.*;

public class CheckableLinearLayout extends LinearLayout implements Checkable
 {
	boolean mChecked = false;

	private static final int[] CHECKED_STATE_SET = {
        R.attr.state_checked
	};

	public CheckableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean isChecked() {
		return mChecked;
	}

	@Override
	public void setChecked(boolean checked) {
		if (mChecked != checked) {
			mChecked = checked;
			refreshDrawableState();
		}
	}

	@Override
	public void toggle() {
		mChecked = !mChecked;
		refreshDrawableState();
	}

	@Override
	protected int[] onCreateDrawableState(int extraSpace) {
		final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
		if (isChecked()) {
			mergeDrawableStates(drawableState, CHECKED_STATE_SET);
		}
		return drawableState;
	}

	@Override
	public boolean performClick() {
		toggle();
		return super.performClick();
	}
}
