#ifndef __MEMORY_BASE_H__
#define __MEMORY_BASE_H__

#ifdef __cplusplus
extern "C" {
#endif

int liblwmm_ashmem_create(const char* name, int size);
int liblwmm_ashmem_mmap(int fd, int size);
int liblwmm_ashmem_msync(void* addr, int len);
int liblwmm_ashmem_close(int fd);
int liblwmm_ashmem_read(int fd, void* vpm, void* buff, int count);

#ifdef __cplusplus
}
#endif

#endif
