package com.nativemodulesdebug

import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

class IntentModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var receivedIntent: Intent? = null

    init {
        Log.d("IntentModule", "IntentModule initialized")
    }

    override fun getName(): String {
        return "IntentModule"
    }

    @ReactMethod
    fun getSharedText(promise: Promise) {
        Log.d("IntentModule", "ADC - getSharedText called - kt")
        Log.d("IntentModule", "ADC - receivedIntent - $receivedIntent")
        receivedIntent?.let { intent ->
            Log.d("ADC - IntentModule", "Received intent action: ${intent.action}, type: ${intent.type}")
            val map = Arguments.createMap()

            intent.extras?.let { extras ->
                for (key in extras.keySet()) {
                    val value = extras.get(key)
                    map.putString(key, value?.toString())
                    Log.d("IntentModule", "Intent extra - key: $key, value: $value")
                }
            }

            promise.resolve(map)
        } ?: run {
            promise.resolve(null)
            Log.d("IntentModule", "Received intent is null")
        }
    }

    fun setReceivedIntent(intent: Intent) {
        receivedIntent = intent
        Log.d("IntentModule", "Intent received and set - kt")
    }

    // Required for NativeEventEmitter to work correctly
    @ReactMethod
    fun addListener(eventName: String) {
        // Keep: Required for RN built-in Event Emitter Calls.
    }

    @ReactMethod
    fun removeListeners(count: Int) {
        // Keep: Required for RN built-in Event Emitter Calls.
    }
}
