#include <jni.h>
#include <windows.h>
#include "com_thebest12lines_worldmanager_util_SystemSettings.h"

extern "C" {

    JNIEXPORT jstring JNICALL Java_com_thebest12lines_worldmanager_util_SystemSettings_getSystemTheme(JNIEnv *env, jclass cls) {
        HKEY hKey;
        const char* subKey = "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize";
        const char* valueName = "AppsUseLightTheme";
        DWORD data;
        DWORD dataSize = sizeof(data);

        if (RegOpenKeyExA(HKEY_CURRENT_USER, subKey, 0, KEY_READ, &hKey) == ERROR_SUCCESS) {
            if (RegQueryValueExA(hKey, valueName, NULL, NULL, (LPBYTE)&data, &dataSize) == ERROR_SUCCESS) {
                RegCloseKey(hKey);
                return env->NewStringUTF(data == 0 ? "Dark" : "Light");
            }
            RegCloseKey(hKey);
        }
        return env->NewStringUTF("Unknown");
    }
}
