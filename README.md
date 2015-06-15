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
2.mmap 
3.read or write share memory
4.close
