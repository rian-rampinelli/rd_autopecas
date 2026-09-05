import { Link } from "expo-router"
import { KeyboardAvoidingView, Platform, ScrollView, StyleSheet, Text, View } from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context"
import { Button } from "../../components/Button"
import { Input } from "../../components/Input"

export default function Registro() {
    return (
        <SafeAreaProvider>
            <SafeAreaView style={{ flex: 1,  backgroundColor: "#FDFDFD" }}>
                <KeyboardAvoidingView behavior={Platform.select({ ios: "padding", android: "height" })}>
                    <ScrollView>

                        <View style={styles.container}>
                            <View style ={{ alignItems:'center'}}>
                                <Text style={styles.title}>Cadastrar</Text>
                                <Text style={styles.subTitle}>crie sua conta</Text>
                            </View>
                            <View style={styles.form}>
                                <Input  />
                                <Input  />
                                <Input keyboardType="email-address"
                                     />
                                <Input secureTextEntry />
                                <Input secureTextEntry  />
                                <Button label="Entrar">
                                </Button>
                            </View>

                            <Link style={styles.footerLink} href={"/login"}>Voltar para login</Link>
                        </View>
                        
                    </ScrollView>
                </KeyboardAvoidingView>

            </SafeAreaView>
        </SafeAreaProvider>

    )
}

const styles = StyleSheet.create({
    container: {
        padding: 16
    },
    title: {
        fontSize: 32,
        fontWeight: 900,

    },
    subTitle: {
        fontSize: 16,
        fontWeight: 500
    },
    form: {
        marginTop: 24,
        gap: 12
    },
    footerLink: {
        color: "#4F6CF3",
        fontWeight: 700,
        textAlign: "center",
        marginTop: 16
    }
})

