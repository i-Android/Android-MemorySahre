package com.alibaba.laiwang.android;

import android.os.Build;
import android.os.Build.VERSION_CODES;



public class MemoryShare {

	static {
		if (VERSION_CODES.JELLY_BEAN == Integer.parseInt(Build.VERSION.SDK)) {
			System.loadLibrary("atrack16");
		}
		
		System.loadLibrary("lwhelper");
	}
	
	public int init() {
		
		this.mHandler = native_init();
		return 0;
	}
	
	public int open() {
		native_open(this.mHandler);
		return 0;
	}
	
	public int mmap(int fd) {
		this.mFD = native_mmap(this.mHandler, fd);
		return 0;
	}
	
	public int close() {
		native_close(this.mHandler);
		return 0;
	}
	
	
	public int write() {
		native_write(this.mHandler);
		return 0;
	}
	
	public int read() {
		native_read(this.mHandler);
		return 0;
	}
	
	private native int native_init();
	private native int native_open(int handler);
	private native int native_mmap(int handler, int fd);
	private native int native_close(int handler);
	private native int native_read(int handler);
	private native int native_write(int handler);
	private native int native_unmmap();
	
	
	private int mHandler;
	
	public int mFD;
	
}
