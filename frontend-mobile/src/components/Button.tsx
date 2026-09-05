import { Text, StyleSheet, PressableProps, Pressable } from "react-native";


type ButtonProps = PressableProps & {
    label: string
}
export function Button({ label, ...rest }: ButtonProps) {
    return (
        <Pressable style={styles.container} {...rest}>
            <Text style={styles.label}>
                {label}
            </Text>
        </Pressable>
    )

}

const styles = StyleSheet.create({
    container: {
        width: "100%",
        height: 48,
        borderRadius: 24,
        fontSize: 16,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#fd6c2b"
    },
    label: {
        color:"white",
        fontWeight:"bold"

    }
})