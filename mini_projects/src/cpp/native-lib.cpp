#include <jni.h>
#include <string>
#include <map>

// source: https://stackoverflow.com/questions/47224571/how-to-create-hashmapstring-string-through-jni-then-parse-to-java
extern "C" jobject Java_com_tanakayu_mini_1projects_di_NdkModule_adrNativeValues(JNIEnv *env,jobject /* this */){
    jclass mapClass = env->FindClass("java/util/HashMap");
    if(mapClass == NULL)
    {
        return NULL;
    }
    jmethodID init = env->GetMethodID(mapClass, "<init>", "(I)V");
    jmethodID put = env->GetMethodID(mapClass, "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");

    jsize map_len = 4; // WARNING, MAKE SURE YOU CHANGE THIS WHEN YOU ADD/DELETE NEW ENTRIES
    jobject hashMap = env->NewObject(mapClass, init, map_len);
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("KEY_A"), env->NewStringUTF("Some Value"));
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("KEY_B"), env->NewStringUTF("https://your.company.dev-url.com"));
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("KEY_C"), env->NewStringUTF("https://your.company.staging-url.com"));
    env->CallObjectMethod(hashMap, put, env->NewStringUTF("KEY_D"), env->NewStringUTF("https://your.company.production-urln.com/"));
    // WARNING, MAKE SURE TO INCREMENT map_len VARIABLE AFTER YOU ADD/DELETE NEW ENTRIES

    return hashMap;
}