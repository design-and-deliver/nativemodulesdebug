import React, { useEffect } from 'react';
import { NativeEventEmitter, NativeModules } from 'react-native';

const { IntentModule } = NativeModules;

const logWithDateTime = (message, data) => {
  const now = new Date();
  const dateTimeString = `${now.toLocaleDateString()} ${now.toLocaleTimeString()}`;
  console.log(`[${dateTimeString}] ${message}`, data);
};

const IntentEventListener = ({ onSharedTextReceived }) => {
  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(IntentModule);
    const eventListener = eventEmitter.addListener('ShareText', (sharedText) => {
      logWithDateTime('Received shared text via event:', sharedText);
      if (typeof onSharedTextReceived === 'function') {
        onSharedTextReceived(sharedText);
      } else {
        console.error('onSharedTextReceived is not a function');
      }
    });

    // Clean up the event listener on component unmount
    return () => {
      eventListener.remove();
    };
  }, [onSharedTextReceived]);

  return null; // This component does not render anything
};

export default IntentEventListener;
