import { Link } from "expo-router"
import { Image, KeyboardAvoidingView, Platform, ScrollView, StyleSheet, Text, TouchableOpacity, View } from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context"
import { Button } from "../../components/Button"
import { Input } from "../../components/Input"

export default function Login() {
    return (
        <SafeAreaProvider>
            <SafeAreaView style={styles.container}>

                <KeyboardAvoidingView behavior={Platform.select({ ios: "padding", android: "height" })}>
                    <ScrollView>

                        <View >
                            <Image
                                source={require("../../assets/img3.png")}
                                style={styles.illustration}
                            />

                            <View style={styles.form}>
                                <Input keyboardType="email-address" placeholder="Insire seu Email" />
                                <Input secureTextEntry placeholder="Insire seu Email" />
                                <Button label="Entrar" >
                                </Button>
                            </View>


                            <Link style={styles.buttonLink} href={"/registro"}>Não tem conta? Cadastra-se aqui</Link>


                        </View>

                    </ScrollView>
                </KeyboardAvoidingView>

            </SafeAreaView>
        </SafeAreaProvider>

    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#FDFDFD",
        padding: 18
    },
    illustration: {
        width: "100%",
        height: 190,
        resizeMode: "contain",
        marginTop: 45,
        marginBottom: 12

    },
    form: {
        marginTop: 24,
        gap: 28
    },
    buttonLink: {
        marginTop: 20,
        width: "99%",
        height: 48,
        borderRadius: 24,
        fontSize: 16,
        justifyContent: "center",
        alignItems: "center",
        borderWidth: 1,
        textAlign: "center",
        textAlignVertical: "center",
        borderColor: "grey"

    }

})

