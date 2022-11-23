#include <stdio.h>
#include "sample.h"
JNIEXPORT int JNICALL Java_sample_add(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1+num2;
}