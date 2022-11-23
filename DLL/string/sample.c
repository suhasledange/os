#include <stdio.h>
#include <string.h>
#include "sample.h"
JNIEXPORT jstring JNICALL Java_sample_oper(JNIEnv *env, jobject javaobj, jstring s1, jstring s2) 
{
	 const char *ss1 = (*env)->GetStringUTFChars(env,s1, NULL);
	  const char *ss2 = (*env)->GetStringUTFChars(env,s2, NULL);
    	  jstring result;
	  
	if (strcmp(ss1, ss2) ==0)
	{	char msg[60] = "string are equal";
		result = (*env)->NewStringUTF(env,msg); 
       	return result;
	}
    else {
    		
    		char msg[60] = "string are not equal";
		result = (*env)->NewStringUTF(env,msg); 
       	return result;
       
      }
}
