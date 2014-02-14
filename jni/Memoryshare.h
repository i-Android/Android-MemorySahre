#ifndef __MEMORY_SHARE_H__
#define __MEMORY_SHARE_H__

class MemoryShare {


public:

	int openMemoryShare();
	int mmapMemoryShare(int fd);
	int closeMemoryShare();
	int readMemoryShare(void* buff, int count);
	int writeMemoryShare(void* src, int count);

public:
	int mFD;
	int mOffset;
	void* mPSHM;

};


#endif // __MEMORY_SHARE_H__
