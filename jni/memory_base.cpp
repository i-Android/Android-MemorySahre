#include <jni.h>
#include <utils/String8.h>
#include <android/log.h>
#include <sys/system_properties.h>
#include <cutils/ashmem.h>
#include <unistd.h>
#include <sys/mman.h>

#include "memory_base.h"
#include <binder/MemoryHeapBase.h>

extern "C" {

int liblwmm_ashmem_create(const char* name, int size) {

	int nFD = ashmem_create_region(name, size);
	ashmem_set_prot_region(nFD, PROT_READ|PROT_WRITE);
	size_t length = 100;
	uint32_t flag = 0;

//	android::MemoryHeapBase* pMemoryHeapBase = new android::MemoryHeapBase(length, flag, "dsds");

//	int nd = pMemoryHeapBase->getHeapID();
	if(nFD < 0)
		return -1;
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "create ashmem");
	return nFD;
}

int liblwmm_ashmem_mmap(int fd, int size) {

	uint8_t * pSHM = mmap(0, size,  PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if (pSHM == MAP_FAILED) {
		return -1;
	}
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "mmap ashmem");
	return pSHM;
}

int liblwmm_ashmem_close(int fd) {

	close(fd);
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "close ashmem");
	return 0;
}

int liblwmm_ashmem_msync(void* addr, int len) {

	int ret = msync(addr, len, MS_SYNC);
	__android_log_print(ANDROID_LOG_INFO,"libatrack", "msync ashmem:%d", ret);
	return 0;
}

int liblwmm_ashmem_read(int fd, void* vpm, void* buff, int count) {

	if (ashmem_pin_region(fd, 0, 0) == ASHMEM_WAS_PURGED) {
		ashmem_unpin_region(fd, 0, 0);
		return -1;
	}

	memcpy(buff, vpm, count);
	ashmem_unpin_region(fd, 0, 0);

	return 0;
}

} // extern "C"




