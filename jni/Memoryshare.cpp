#include "Memoryshare.h"
#include "memory_base.h"
#include <sys/mman.h>
#include <unistd.h>
#include <android/log.h>

#define MEME_SIZE 100

int MemoryShare::openMemoryShare() {

	mFD = liblwmm_ashmem_create("my_laiwang_ashmem", MEME_SIZE);
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "ashmem create:%d", mFD);
	if(mFD < 0)
		return -1;

	return 0;
}

int MemoryShare::mmapMemoryShare(int fd) {

	if (fd!=0) {
		mFD = fd;
	}
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "ashmem fd:%d", mFD);

	this->mPSHM = liblwmm_ashmem_mmap(mFD, MEME_SIZE);
	if (mPSHM == -1) {
		__android_log_print(ANDROID_LOG_INFO,"libatrack", "ashmem map faile");
		return -1;
	}

	__android_log_print(ANDROID_LOG_INFO,"libatrack", "ashmem addr:%x", mPSHM);

	return 0;
}

int MemoryShare::closeMemoryShare() {

	liblwmm_ashmem_close(mFD);
	return 0;
}

int MemoryShare::writeMemoryShare(void* src, int count) {

	// todo: memory copy
	memcpy(this->mPSHM, src, count);
	liblwmm_ashmem_msync(this->mPSHM, MEME_SIZE);
	return count;
}

int MemoryShare::readMemoryShare(void* buff, int count) {

	// todo: memory copy
//	memcpy(buff, this->mPSHM, count);
	liblwmm_ashmem_read(mFD, this->mPSHM, buff, count);

	return 0;
}



