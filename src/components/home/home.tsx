import React, { useState } from 'react';
import { View, Text } from 'react-native';
import IntentEventListener from '../intent-bridge/intent-event-listener'; // Adjust the import path as needed

const Home = () => {
  const [sharedText, setSharedText] = useState('');

  const handleSharedTextReceived = (text) => {
    console.log('Setting shared text:', text); // Debug log
    setSharedText(text);
  };

  return (
    <View>
      <Text>Welcome to the Home Component!</Text>
      <Text>Shared Text: {sharedText}</Text>
      <IntentEventListener onSharedTextReceived={handleSharedTextReceived} />
    </View>
  );
};

export default Home;
