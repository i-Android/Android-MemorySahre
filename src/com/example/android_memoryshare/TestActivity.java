package com.example.android_memoryshare;

import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Menu;
import com.alibaba.laiwang.android.MemoryShare;

public class TestActivity extends Activity {

	
	private MemoryShare mMemoryShare; 
//	MemoryFile
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		mMemoryShare = new MemoryShare();
		mMemoryShare.mFD = getIntent().getIntExtra("fdValue", 0);
		ParcelFileDescriptor fdc = getIntent().getParcelableExtra("fdName");
		mMemoryShare.mFD = fdc.getFd();
		
//		try {
//			mMemoryShare.mFD = ParcelFileDescriptor.fromFd(mMemoryShare.mFD).getFd();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Log.e("libTest", "onCreate:"+mMemoryShare.mFD);
		
		mMemoryShare.init();
//		mMemoryShare.open();
		mMemoryShare.mmap(mMemoryShare.mFD);
		Log.e("libTest", "onCreate2:"+mMemoryShare.mFD);
//		mMemoryShare.write();
		mMemoryShare.read();
		
//		Intent intent = new Intent();
//		intent.putExtra("fdValue", mMemoryShare.mFD);
//		intent.setClassName("com.example.android_memoryshare", "com.alibaba.laiwang.android.MemoryService");
//		this.startService(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	protected void onDestroy() {
		
//		mMemoryShare.close();
		super.onDestroy();
	}

}
