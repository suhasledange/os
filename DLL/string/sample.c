#include <stdio.h>
#include <string.h>
#include "sample.h"
JNIEXPORT int JNICALL Java_sample_oper(JNIEnv *env, jobject javaobj, jint s1, jint s2) 
{
	if (strcmp(s1, s2) ==0)
       return "string 1 and string 2 are equal";
    else 
       return "string 1 and 2 are different";
      
}