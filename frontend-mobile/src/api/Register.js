export default function register(dadosCadastro) {
    fetch('http://localhost:8080/auth/register', {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            dadosCadastro
        }),
    });
}