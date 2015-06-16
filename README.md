Android-MemorySahre
===================

use ashmem ipc in native.


process A:

1.ashmeme_create_region

2.mmap

3.read or write share memory.

4.close.



process B:

1.transacte fd between two process by binder's ParcelFileDescriptor.
because struct file is global for linux kernal. so binder driver can get file by fget, and map file to anthoer process with 
new fd. API target_fd_install. 

2.mmap 

3.read or write share memory

4.close

