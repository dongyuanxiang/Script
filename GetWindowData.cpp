#include "GetWindowData.h"
#include "jni.h"
#include <Windows.h>
#include <stdio.h>
#include <iostream>
using namespace std;

JNIEXPORT jint JNICALL Java_GetWindowData_Width
(JNIEnv *env, jobject, jstring WindowClassName, jstring WindowTitle){
	int length = env->GetStringLength(WindowClassName);
	const jchar* jcstr = env->GetStringChars(WindowClassName, 0);
	const char* string1 = (const char*)malloc(length * 2 + 1);
	int size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string1, (length * 2 + 1), NULL, NULL);
	length = env->GetStringLength(WindowTitle);
	jcstr = env->GetStringChars(WindowTitle, 0);
	const char* string2 = (const char*)malloc(length * 2 + 1);
	size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string2, (length * 2 + 1), NULL, NULL);
	HWND hwnd = FindWindow(string1,string2);
	RECT rect;
	GetClientRect(hwnd, &rect);
	jint Width = rect.right - rect.left;
	return Width;
}


JNIEXPORT jint JNICALL Java_GetWindowData_Height
(JNIEnv *env, jobject, jstring WindowClassName, jstring WindowTitle){
	int length = env->GetStringLength(WindowClassName);
	const jchar* jcstr = env->GetStringChars(WindowClassName, 0);
	const char* string1 = (const char*)malloc(length * 2 + 1);
	int size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string1, (length * 2 + 1), NULL, NULL);
	length = env->GetStringLength(WindowTitle);
	jcstr = env->GetStringChars(WindowTitle, 0);
	const char* string2 = (const char*)malloc(length * 2 + 1);
	size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string2, (length * 2 + 1), NULL, NULL);
	HWND hwnd = FindWindow(string1, string2);
	RECT rect;
	GetClientRect(hwnd, &rect);
	jint Height = rect.bottom - rect.top;
	return Height;
}


JNIEXPORT jint JNICALL Java_GetWindowData_LeftUpXCoordinate
(JNIEnv *env, jobject, jstring WindowClassName, jstring WindowTitle){
	int length = env->GetStringLength(WindowClassName);
	const jchar* jcstr = env->GetStringChars(WindowClassName, 0);
	const char* string1 = (const char*)malloc(length * 2 + 1);
	int size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string1, (length * 2 + 1), NULL, NULL);
	length = env->GetStringLength(WindowTitle);
	jcstr = env->GetStringChars(WindowTitle, 0);
	const char* string2 = (const char*)malloc(length * 2 + 1);
	size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string2, (length * 2 + 1), NULL, NULL);
	HWND hwnd = FindWindow(string1, string2);
	LPPOINT point;
	POINT ptemp;
	point = &ptemp;
	ptemp.x = 0;
	ptemp.y = 0;
	ClientToScreen(hwnd, point);
	jint x = point->x;
	return x;
}

JNIEXPORT jint JNICALL Java_GetWindowData_LeftUpYCoordinate
(JNIEnv *env, jobject, jstring WindowClassName, jstring WindowTitle){
	int length = env->GetStringLength(WindowClassName);
	const jchar* jcstr = env->GetStringChars(WindowClassName, 0);
	const char* string1 = (const char*)malloc(length * 2 + 1);
	int size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string1, (length * 2 + 1), NULL, NULL);
	length = env->GetStringLength(WindowTitle);
	jcstr = env->GetStringChars(WindowTitle, 0);
	const char* string2 = (const char*)malloc(length * 2 + 1);
	size = 0;
	size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, (LPSTR)string2, (length * 2 + 1), NULL, NULL);
	HWND hwnd = FindWindow(string1, string2);
	LPPOINT point;
	POINT ptemp;
	point = &ptemp;
	ptemp.x = 0;
	ptemp.y = 0;
	ClientToScreen(hwnd, point);
	jint y = point->y;
	return y;
}