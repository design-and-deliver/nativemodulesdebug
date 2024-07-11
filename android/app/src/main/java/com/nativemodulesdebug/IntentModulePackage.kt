package com.nativemodulesdebug

import android.util.Log
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.NativeModule
import com.facebook.react.uimanager.ViewManager

class IntentModulePackage : ReactPackage {

    init {
        Log.d("IntentModulePackage", "IntentModulePackage initialized")
    }

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        Log.d("IntentModulePackage", "enter createNativeModules")
        val modules = listOf(IntentModule(reactContext))
        Log.d("IntentModulePackage", "createNativeModules: $modules")
        return modules
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
