package com.testapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeAct extends Activity {
	private Button btn_begin;
	private Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.act_welcome);
		btn_begin = (Button) findViewById(R.id.btn_begin);
		
		
		btn_begin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ctx, MainAct.class));
				finish();
			}
		});
		
		
	}
}
