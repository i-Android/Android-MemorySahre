package com.example.android_memoryshare;

import android.app.Activity;
import android.os.Bundle;
import android.os.MemoryFile;
import android.util.Log;
import android.view.Menu;

import com.alibaba.laiwang.android.MemoryShare;

public class TestActivity extends Activity {

	
	private MemoryShare mMemoryShare; 
//	MemoryFile
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		mMemoryShare = new MemoryShare();
		mMemoryShare.mFD = getIntent().getIntExtra("fdValue", 0);
		mMemoryShare.init();
		mMemoryShare.open();
		mMemoryShare.mmap(0);
		Log.e("libTest", "onCreate:"+mMemoryShare.mFD);
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
