package com.alibaba.laiwang.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MemoryService extends Service{


	private MemoryShare mMemoryShare;
	private int mFD;
	
	@Override
	public void onCreate() {
		
		Log.e("libsrv", "onCreate");
		mMemoryShare = new MemoryShare();
		mMemoryShare.init();
		mMemoryShare.mmap(mFD);
		mMemoryShare.read();
		
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	protected void onHandleIntent(Intent intent) {
//		Log.e("libsrv", "intent");
//		mFD = intent.getIntExtra("fdValue", 0);
//	}
	

}
