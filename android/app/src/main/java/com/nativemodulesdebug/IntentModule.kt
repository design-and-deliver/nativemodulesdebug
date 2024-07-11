package com.nativemodulesdebug

import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.Arguments

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
        Log.d("IntentModule", "getSharedText called")
        receivedIntent?.let {
            if (Intent.ACTION_SEND == it.action && it.type == "text/plain") {
                val sharedText = it.getStringExtra(Intent.EXTRA_TEXT)
                val map = Arguments.createMap()
                map.putString("sharedText", sharedText)
                promise.resolve(map)
                Log.d("IntentModule", "Shared text resolved: $sharedText")
            } else {
                promise.resolve(null)
                Log.d("IntentModule", "Intent action not ACTION_SEND or type not text/plain")
            }
        } ?: run {
            promise.resolve(null)
            Log.d("IntentModule", "Received intent is null")
        }
    }

    fun setReceivedIntent(intent: Intent) {
        receivedIntent = intent
        Log.d("IntentModule", "Intent received and set")
    }
}
