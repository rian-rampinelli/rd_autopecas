import { Link } from "expo-router"
import { KeyboardAvoidingView, Platform, ScrollView, StyleSheet, Text, View } from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context"
import { Button } from "../../components/Button"
import { Input } from "../../components/Input"
import { useState } from "react"
import register from "../../api/Register"

export default function Registro() {

    const [nome, setNome] = useState("")
    const [cpf, setCpf] = useState("")
    const [email, setEmail] = useState("")
    const [senha, setSenha] = useState("")
    const [confirmarSenha, setConfirmarSenha] = useState("")
    const [cep, setCep] = useState("")

    function registrar(){
        const dados ={
            nome,
            cpf,
            email,
            senha,
            confirmarSenha,
            cep
        }

        const response = register(dados);
        console.log(response)

    }

    return (
        <SafeAreaProvider>
            <SafeAreaView style={{ flex: 1, backgroundColor: "black" }}>
                <KeyboardAvoidingView behavior={Platform.select({ ios: "padding", android: "height" })}>
                    <ScrollView>

                        <View style={styles.container}>

                            <View style={{ alignItems: 'center' }}>
                                <Text style={styles.title}>Cadastrar-se</Text>
                            </View>
                            <View>
                                <Text style={styles.subTitle}>Dados Pessoais</Text>
                            </View>

                            <View style={styles.form}>
                                <View>
                                    <Text style={styles.label}>Nome Completo</Text>
                                    <Input placeholder="digite seu nome" value={nome} onChangeText={setNome} />
                                </View>
                                <View>
                                    <Text style={styles.label}>CPF</Text>
                                    <Input placeholder="000.000.000.00" value={cpf} onChangeText={setCpf} />

                                </View>

                                <View>
                                    <Text style={styles.label}>Email</Text>
                                    <Input placeholder="seu@email.com" keyboardType="email-address" value={email} onChangeText={setEmail} />
                                </View>

                                <View>
                                    <Text style={styles.label}>Senha</Text>
                                    <Input placeholder="********" secureTextEntry value={senha} onChangeText={setSenha} />
                                </View>

                                <View>
                                    <Text style={styles.label}>Confirmar Senha</Text>
                                    <Input placeholder="********" secureTextEntry value={confirmarSenha} onChangeText={setConfirmarSenha} />
                                </View>

                            </View>

                            <View>
                                <Text style={styles.subTitle}>Endereco</Text>
                            </View>
                            <View style={styles.form}>
                                <View>
                                    <Text style={styles.label}>CEP</Text>
                                    <Input placeholder="36140000" value={cep} onChangeText={setCep} />
                                </View>
                            </View>

                            <View style={{ marginTop: 20, marginBottom: 45 }}>
                                <Button label="Entrar" onPress={registrar}>
                                </Button>
                                <Link style={styles.buttonLink} href={"/login"}>Voltar para login</Link>
                            </View>

                        </View>

                    </ScrollView>
                </KeyboardAvoidingView>

            </SafeAreaView>
        </SafeAreaProvider>

    )
}

const styles = StyleSheet.create({
    container: {
        padding: 16,
        backgroundColor: "#FAFAFA"

    },
    title: {
        fontSize: 30,
        fontWeight: 700,
        marginTop: 45

    },
    subTitle: {
        fontSize: 18,
        marginTop: 16,
        fontWeight: 500
    },
    form: {
        marginTop: 16,
        gap: 12
    },
    footerLink: {
        color: "#4F6CF3",
        fontWeight: 700,
        textAlign: "center",
        marginTop: 16
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
    },
    label: {
        marginBottom: 8
    }
})

