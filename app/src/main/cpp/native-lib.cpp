#include <jni.h>
#include <string>

extern "C"
jint
Java_com_example_dou_myndktest_MainActivity_addInt(JNIEnv *env, jobject instance, jint a, jint b) {

    return a + b;

}

extern "C"
jdouble
Java_com_example_dou_myndktest_MainActivity_mulDouble(JNIEnv *env, jobject instance, jdouble a,
                                                      jdouble b) {

   return a * b;

}

extern "C"
jboolean
Java_com_example_dou_myndktest_MainActivity_bigger(JNIEnv *env, jobject instance, jfloat a,
                                                   jfloat b) {

    return a > b;

}


extern "C"
jstring
Java_com_example_dou_myndktest_MainActivity_addString(JNIEnv *env, jobject instance, jstring a_,
                                                      jstring b_) {
    //字符串和数组要转换
    const char *a = env->GetStringUTFChars(a_, 0);
    const char *b = env->GetStringUTFChars(b_, 0);
    int len_a = strlen(a);
    int len_b = strlen(b);
    char *c = (char *)malloc(len_a + len_b);
    strcpy(c, a);
    strcat(c, b);
    //是否字符串
    env->ReleaseStringUTFChars(a_, a);
    env->ReleaseStringUTFChars(b_, b);

    return env->NewStringUTF(c);
}


//实现必须按照示例来

extern "C"
//返回类型
jstring
Java_com_example_dou_myndktest_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
