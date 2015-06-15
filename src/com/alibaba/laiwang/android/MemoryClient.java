package com.alibaba.laiwang.android;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

@SuppressLint("NewApi")
public class MemoryClient {

	private static final String TAG = "MemoryClient";

	private MemoryConnection mMemoryConnection = null;
	private IMemoryShare mIMemoryShareProxy = null;
	private MemoryShare mMemoryShare; 

	public void readSHM() {
		if (null==mIMemoryShareProxy) {
			Log.i(TAG, "mIMemoryShareProxy  is null.");
			return ;
		}

		mMemoryShare = new MemoryShare();
		mMemoryShare.init();
		ParcelFileDescriptor pfd;
		try {
			pfd = mIMemoryShareProxy.getShmFD();
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return ;
		}
		mMemoryShare.mmap(pfd.getFd());
		mMemoryShare.read();
	}

	public void startService(Context context) {
		Intent intentService = new Intent();
		intentService.setClassName("com.example.android_memoryshare", "com.alibaba.laiwang.android.MemoryService");
		Log.i(TAG, "Laiwang service is binding.");

		mMemoryConnection = new MemoryConnection();
		
		boolean exitService = false;
		try {
			context.startService(intentService);
			exitService = context.bindService(intentService, mMemoryConnection, Context.BIND_AUTO_CREATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class MemoryConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIMemoryShareProxy = IMemoryShare.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	}
}
