package com.example.android_memoryshare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.laiwang.android.MemoryClient;

public class MainActivity extends Activity {
	
	private MemoryClient mMemoryClient;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mMemoryClient = new MemoryClient();
		
		mMemoryClient.startService(this);
		
		this.findViewById(R.id.btnShare).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMemoryClient.readSHM();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	} 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
