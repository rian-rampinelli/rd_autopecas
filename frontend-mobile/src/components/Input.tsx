import { TextInput, StyleSheet, TextInputProps, View,Text } from "react-native";

type InputArea = TextInputProps

export function Input({ ...rest }: InputArea) {
    return (
        <View>
            <TextInput style={styles.input} {...rest}>

            </TextInput>
        </View>


    )
}

const styles = StyleSheet.create({
    input: {
        width: "99%",
        height: 48,
        borderWidth: 1,
        borderRadius: 4,
        fontSize: 14,
        paddingLeft: 12,
        borderColor: "black"
    },
    label:{
        fontWeight:"700",
        textTransform:"capitalize"
    }
})