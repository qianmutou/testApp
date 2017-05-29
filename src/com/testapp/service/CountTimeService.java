package com.testapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CountTimeService extends Service {
	private CallBack callBack;
	private boolean connecting = false;

	@Override
	public void onCreate() {
		connecting = true;
		super.onCreate();
		new Thread(new Runnable() {
			int count = getTime();
			@Override
			public void run() {
				while (connecting) {
					if (callBack != null) {
						// translate data to activity
						callBack.onDataChange(count);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count--;
				}

				if (count <= 0) {
					stopSelf();
				}
			}
		}).start();

	}

	public int getTime() {
		return 7300;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new TimeBinder();
	}

	public class TimeBinder extends Binder {
		public CountTimeService geTimeService() {
			return CountTimeService.this;
		}
	}

	public interface CallBack {
		void onDataChange(int time);
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		connecting = false;
	}
}
