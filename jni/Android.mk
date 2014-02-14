
LOCAL_PATH := $(call my-dir)

# library for Android api = 16
include $(CLEAR_VARS)
LOCAL_MODULE := atrack16
LOCAL_CFLAGS += -O2 -Wall -export-dynamic -DBUILD_STANDALONE -DCPU_ARM -DAVSREMOTE -finline-functions -fPIC -D__ARM_EABI__=1 -DOLD_LOGDH -fpermissive
LOCAL_CFLAGS += -DBUILD_JB
LOCAL_C_INCLUDES += $(LOCAL_PATH)/Android/include_jb
LOCAL_SRC_FILES := memory_base.cpp
LOCAL_ARM_MODE := arm
LOCAL_LDLIBS := -llog \
  $(LOCAL_PATH)/Android/lib/jellybean/libcutils.so $(LOCAL_PATH)/Android/lib/jellybean/libutils.so $(LOCAL_PATH)/Android/lib/jellybean/libmedia.so
include $(BUILD_SHARED_LIBRARY)


# common codecs & startup library
include $(CLEAR_VARS)
LOCAL_MODULE := lwhelper
LOCAL_CFLAGS += -O2 -Wall -DBUILD_STANDALONE -DCPU_ARM -DAVSREMOTE -finline-functions -fPIC -D__ARM_EABI__=1 -DOLD_LOGDH -fpermissive
LOCAL_SRC_FILES := Memoryshare.cpp com_alibaba_laiwang_android_MemoryShare.cpp
LOCAL_ARM_MODE := arm
LOCAL_LDLIBS := -llog -ldl 
LOCAL_SHARED_LIBRARIES := atrack16
include $(BUILD_SHARED_LIBRARY)

#CODECS := alac ape flac wav wv mpc
#codec-makefiles =  $(patsubst %,$(LOCAL_PATH)/%/Android.mk,$(CODECS)) 
#include $(call codec-makefiles)

