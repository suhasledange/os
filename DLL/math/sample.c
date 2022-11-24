#include <stdio.h>
#include "sample.h"
JNIEXPORT int JNICALL Java_sample_add(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1+num2;
}
JNIEXPORT int JNICALL Java_sample_sub(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1-num2;
}
JNIEXPORT int JNICALL Java_sample_mul(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1*num2;
}
JNIEXPORT int JNICALL Java_sample_div(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1/num2;
}
