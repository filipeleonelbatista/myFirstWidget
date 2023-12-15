import { StatusBar } from "expo-status-bar";
import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View, NativeModules, TextInput, Button } from "react-native";

const { RNSharedWidget } = NativeModules;

export default function App() {
  const [message, setMessage] = useState("Olá mundo");

  const onSubmit = () => {
    RNSharedWidget.setData(
      "myData",
      JSON.stringify({
        message: message,
        updatedAt: Date.now(),
      }),
      (_status: number | null) => {
        // log callback in case of success/error
      }
    );
  };

  useEffect(() => {
    RNSharedWidget.setData(
      "myData",
      JSON.stringify({
        message: "Olá mundo",
        updatedAt: Date.now(),
      }),
      (_status: number | null) => {
        // log callback in case of success/error
      }
    );
  }, []);

  return (
    <View style={styles.container}>
      <Text>Digite uma mensagem</Text>
      <TextInput
        placeholder=""
        value={message}
        onChangeText={(txt) => setMessage(txt)}
        style={styles.input}
      />
      <Button title="Salvar" onPress={onSubmit} />
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  input: {
    width: "90%",
    marginTop: 8,
    padding: 10,
    borderRadius: 5,
    fontSize: 20,
    marginBottom: 10,
    fontWeight: "bold",
    borderColor: "grey",
    borderWidth: 1,
  },
});
