import { NativeModules } from 'react-native';
const { IntentModule } = NativeModules;

const logWithDateTime = (message, data) => {
  const now = new Date();
  const dateTimeString = `${now.toLocaleDateString()} ${now.toLocaleTimeString()}`;
  console.log(`[${dateTimeString}] ${message}`, data);
};

logWithDateTime("All Native Modules:", NativeModules);
logWithDateTime("IntentModule - direct access:", NativeModules.IntentModule);

const getSharedText = async () => {
  try {
    const result = await NativeModules.IntentModule.getSharedText();
    logWithDateTime("ADC - IntentModule - direct access - shared text:", result);
  } catch (error) {
    logWithDateTime("Error in getSharedText:", error);
  }
};

getSharedText();

export default IntentModule;
