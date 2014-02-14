#include "com_alibaba_laiwang_android_MemoryShare.h"
#include "Memoryshare.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <inttypes.h>
#include <stdbool.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <jni.h>
#include <pthread.h>
#include <dlfcn.h>
#include <android/log.h>





#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,"liblwhelper",__VA_ARGS__)


JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1init
  (JNIEnv *env, jobject obj) {

	MemoryShare* pMemoryShare = new MemoryShare();

	return (jint)pMemoryShare;
}

JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1open
  (JNIEnv *env, jobject obj, jint iBinder) {

	MemoryShare* pMemoryShare = (MemoryShare*)iBinder;

	pMemoryShare->openMemoryShare();
	return (jint)pMemoryShare->mFD;
}


JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1mmap
  (JNIEnv *env, jobject obj, jint iBinder, jint fd) {
	MemoryShare* pMemoryShare = (MemoryShare*)iBinder;
	pMemoryShare->mmapMemoryShare(fd);
	return pMemoryShare->mFD;
}


JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1close
  (JNIEnv *env, jobject obj, jint iBinder) {

	MemoryShare* pMemoryShare = (MemoryShare*)iBinder;
	pMemoryShare->closeMemoryShare();

	delete pMemoryShare;
	return 0;
}

JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1write
  (JNIEnv *env, jobject obj, jint iHandler) {
	__android_log_print(ANDROID_LOG_INFO,"liblwhelper", "write ashmem");
	MemoryShare* pMemoryShare = (MemoryShare*)iHandler;
	char buff[30];
	memset(buff, 0, 30);
	strcpy(buff, "Hello World");
	pMemoryShare->writeMemoryShare(buff, 30);
	return 0;
}

JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1read
  (JNIEnv *env, jobject obj, jint iBinder) {
	MemoryShare* pMemoryShare = (MemoryShare*)iBinder;
	char buff[30];
	memset(buff, 0, 30);
	pMemoryShare->readMemoryShare(buff, 30);
	__android_log_print(ANDROID_LOG_INFO,"liblwhelper", "read ashmem:%s", buff);
	return 0;
}

JNIEXPORT jint JNICALL Java_com_alibaba_laiwang_android_MemoryShare_native_1unmmap
  (JNIEnv *env, jobject obj, jint iBinder) {


	return 0;
}
