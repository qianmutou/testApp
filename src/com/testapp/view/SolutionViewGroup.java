package com.testapp.view;

import com.testapp.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SolutionViewGroup extends LinearLayout {

	public SolutionViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.item_solution, null);
		;
		setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		setLayoutParams(params);

		TextView order_tView;
		TextView content_tView;
		order_tView = new TextView(context);
		order_tView.setGravity(Gravity.CENTER);
		order_tView.setTextSize(25);
		content_tView = new TextView(context);
		content_tView.setGravity(Gravity.CENTER);
		content_tView.setTextSize(10);
		order_tView.setText("A");
		content_tView.setText("1+1=2");
		addView(order_tView);
		addView(content_tView);

	}

}
