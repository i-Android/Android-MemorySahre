package com.alibaba.laiwang.android;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

public class MemoryService extends Service{
	
	private static final String TAG = "libSHM";
	private MemoryShare mMemoryShare;
	private MemoryImpl mMemoryImpl;
	
	@Override
	public void onCreate() {
		
		Log.e(TAG, "service onCreate");
		
		mMemoryImpl = new MemoryImpl();
		mMemoryShare = new MemoryShare();
		mMemoryShare.init();
		mMemoryShare.open();
		mMemoryShare.mmap(0);
		mMemoryShare.write();
		mMemoryShare.read();
		
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return mMemoryImpl;
	}

	protected void onHandleIntent(Intent intent) {
		Log.e(TAG, "intent");
	}
	
	@Override
	public void onDestroy() {
		mMemoryShare.close();
		super.onDestroy();
	}
	
	class MemoryImpl extends IMemoryShare.Stub {

		@SuppressLint("NewApi")
		@Override
		public ParcelFileDescriptor getShmFD() throws RemoteException {
			try {
				return ParcelFileDescriptor.fromFd(mMemoryShare.mFD);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
