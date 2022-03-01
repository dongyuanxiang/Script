#include "jni.h"
#include "jni_md.h"
#include "GetMousePositionWindowData.h"
#include <Windows.h>

JNIEXPORT jint JNICALL Java_GetMousePositionWindowData_Width
(JNIEnv*, jobject) {
	POINT point;
	HWND hwnd;
	GetCursorPos(&point);
	hwnd = WindowFromPoint(point);
	RECT rect;
	GetClientRect(hwnd, &rect);
	jint Width = rect.right - rect.left;
	return Width;
}

JNIEXPORT jint JNICALL Java_GetMousePositionWindowData_Height
(JNIEnv*, jobject) {
	POINT point;
	HWND hwnd;
	GetCursorPos(&point);
	hwnd = WindowFromPoint(point);
	RECT rect;
	GetClientRect(hwnd, &rect);
	jint Height = rect.bottom - rect.top;
	return Height;
}

JNIEXPORT jint JNICALL Java_GetMousePositionWindowData_LeftUpXCoordinate
(JNIEnv*, jobject) {
	POINT point;
	HWND hwnd;
	GetCursorPos(&point);
	hwnd = WindowFromPoint(point);
	LPPOINT lppoint;
	POINT ptemp;
	lppoint = &ptemp;
	ptemp.x = 0;
	ptemp.y = 0;
	ClientToScreen(hwnd, lppoint);
	jint x = lppoint->x;
	return x;
}

JNIEXPORT jint JNICALL Java_GetMousePositionWindowData_LeftUpYCoordinate
(JNIEnv*, jobject) {
	POINT point;
	HWND hwnd;
	GetCursorPos(&point);
	hwnd = WindowFromPoint(point);
	LPPOINT lppoint;
	POINT ptemp;
	lppoint = &ptemp;
	ptemp.x = 0;
	ptemp.y = 0;
	ClientToScreen(hwnd, lppoint);
	jint y = lppoint->y;
	return y;
}