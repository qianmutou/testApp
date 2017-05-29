package com.testapp.view;

import com.testapp.R;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpecialViewGroup extends LinearLayout {

	public SpecialViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, isSolo(), getSolutionNum());
	}

	public void init(Context context, AttributeSet attrs, boolean solo,
			int solution_num) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		params.topMargin = 30;
		params.gravity = Gravity.CENTER_HORIZONTAL;
		setLayoutParams(params);
		setOrientation(LinearLayout.VERTICAL);

		for (int i = 0; i < solution_num; i++) {
			LinearLayout ll = (LinearLayout) View.inflate(context,
					R.layout.item_solution, null);
			TextView order_tView = (TextView) ll.findViewById(R.id.order_tv);
			TextView content_tView = (TextView) ll
					.findViewById(R.id.content_tv);
			order_tView.setText("ORDER  " + i);
			content_tView.setText("content  " + i);
			addView(ll);
		}
		if (solo) {
		} else {
			// TODO multi solution
		}

	}

	private int getSolutionNum() {
		return 4;
	}

	private boolean isSolo() {
		return true;
	}
}
