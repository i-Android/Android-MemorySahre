package com.example.android_memoryshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.alibaba.laiwang.android.MemoryShare;

public class MainActivity extends Activity {

	
	private MemoryShare mMemoryShare; 
	private MemoryShare mMemoryShare2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMemoryShare = new MemoryShare();
		mMemoryShare.init();
		mMemoryShare.open();
		mMemoryShare.mmap(0);
		mMemoryShare.write();
		mMemoryShare.read();
		
//		mMemoryShare2 = new MemoryShare();
//		mMemoryShare2.init();
////		mMemoryShare2.open();
//		mMemoryShare2.mmap(mMemoryShare.mFD);
//		mMemoryShare2.read();
		
		Intent intent = new Intent();
		intent.putExtra("fdValue", mMemoryShare.mFD);
		intent.setClass(this, TestActivity.class);
//		intent.setClassName("com.example.android_memoryshare", "com.alibaba.laiwang.android.MemoryService");
		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
//		mMemoryShare.read();
		super.onResume();
	} 
	
	@Override
	protected void onDestroy() {
		
//		mMemoryShare.close();
		super.onDestroy();
	}

}
