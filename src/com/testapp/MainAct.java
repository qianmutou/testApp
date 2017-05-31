package com.testapp;


import com.testapp.engine.TestEngine;
import com.testapp.service.CountTimeService;
import com.testapp.service.CountTimeService.CallBack;
import com.testapp.service.CountTimeService.TimeBinder;
import com.testapp.utils.BaseUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainAct extends Activity implements ServiceConnection {
	private int lastX;
	private int lastY;
	private final int MIN_DISTANCE = 50;
	
	private TextView time_TextView;
	
	private Context ctx;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			//TODO UI
			super.handleMessage(msg);
			int time = (Integer) msg.obj;
			//time_TextView.setText(BaseUtils.formatTime(time));
		};
	};
	
	private TestEngine engine;
	private int order = 1;
	private int totalNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.act_main);
		time_TextView = (TextView) findViewById(R.id.time_textview);
		
		engine = TestEngine.getTestEngine(ctx);
		totalNum = engine.getTotalNum();
		//bindService(new Intent(this, CountTimeService.class), this, BIND_AUTO_CREATE);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = (int) event.getRawX();
			break;

		case MotionEvent.ACTION_UP:
			if (Math.abs(lastX-event.getRawX()) <= MIN_DISTANCE) {
				break;
			}else {
				if (lastX < event.getRawX()) {
					//滑到上一页
					order = order > 0?--order:0;
					Toast.makeText(ctx, "上一页", 0).show();
				}
				if (lastX > event.getRawX()) {
					//滑到下一页
					order = order < totalNum ? ++order : totalNum;
					Toast.makeText(ctx, "下一页", 0).show();
				}
			}
			break;
		}
		
		return true;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		CountTimeService.TimeBinder binder = (TimeBinder) service;
		CountTimeService cTimeService = binder.geTimeService();
		cTimeService.setCallBack(new CallBack() {
			@Override
			public void onDataChange(int time) {
				//send data to service
				Message message = Message.obtain();
				message.obj = time;
				handler.sendMessage(message);
			}
		});
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//unbindService(this);
	}
}
