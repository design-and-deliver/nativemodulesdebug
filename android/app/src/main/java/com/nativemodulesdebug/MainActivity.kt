package com.nativemodulesdebug

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule

class MainActivity : ReactActivity() {

    override fun getMainComponentName(): String = "nativemodulesdebug"

    override fun createReactActivityDelegate(): ReactActivityDelegate =
        DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")

        // Handle the incoming share intent
        if (intent?.action == Intent.ACTION_SEND) {
            Log.d("MainActivity", "Intent action is SEND")
            if (intent.type == "text/plain") {
                Log.d("MainActivity", "Intent type is text/plain")
                handleSendText(intent) // Handle text being sent
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
        Log.d("MainActivity", "handleSendText called with text: $text")
        text?.let {
            // Send the text to React Native
            sendEventToReactNative(it)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.d("MainActivity", "onNewIntent called")

        // Handle the new intent
        if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            Log.d("MainActivity", "New Intent action is SEND and type is text/plain")
            handleSendText(intent)
        }
    }

    private fun sendEventToReactNative(sharedText: String) {
        val reactContext = reactInstanceManager?.currentReactContext
        reactContext?.let {
            it
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit("ShareText", sharedText)
        }
    }
}
