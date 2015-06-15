package com.alibaba.laiwang.android;
import android.os.ParcelFileDescriptor;

interface IMemoryShare {
	ParcelFileDescriptor getShmFD();
}