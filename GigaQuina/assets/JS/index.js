// Exemplo de uso:
const cpfInput = document.getElementById("CPF");
const nomeInput = document.getElementById("Nome");
const concCadastro = document.getElementById("Concluir");
concCadastro.disabled = true;

// function para armazenar os dados e direcionar a escolha de numeros
function cadastroConcluido(){
    coletarNomeCPF();
    window.location.href = "./assets/HTML/aposta.html";
    
}

function verificarNome(nome) {
    return nome.trim().length >= 3; // verifica qtd de caracteres ignorando espaços, o que nao permitiria que o usuario colocasse 3x "espaço" e conseguisse prosseguir
}

// formata cpf
function formatarCPF(cpf) {
    cpf = cpf.replace(/\D/g, ''); // 
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); 
    return cpf;
}

function verificarCPFPreenchido(cpf) {
    cpf = cpf.replace(/\D/g, ''); // remove caracteres nao numericos

    // verifica qtd de numeros inseridos desconsiderando as pontuacoes
    if (cpf.length !== 11) {
        return false;
    }

    // verifica combinações invalidas de cpf antes de prosseguir para verificacao matematica com intuito de otimizar o processamento
    const cpfArray = cpf.split('');
    const isAllDigitsEqual = cpfArray.every(digit => digit === cpfArray[0]);
    if (isAllDigitsEqual) {
        return false;
    }

    // calcula a formula matematica do cpf para verificar a validade
    let sum = 0;
    for (let i = 0; i < 9; i++) {
        sum += parseInt(cpfArray[i]) * (10 - i);
    }
    let aux = sum % 11;
    let digit1 = (aux < 2) ? 0 : 11 - aux;

    sum = 0;
    for (let i = 0; i < 10; i++) {
        sum += parseInt(cpfArray[i]) * (11 - i);
    }
    aux = sum % 11;
    let digit2 = (aux < 2) ? 0 : 11 - aux;

    return parseInt(cpfArray[9]) === digit1 && parseInt(cpfArray[10]) === digit2;
}


cpfInput.addEventListener('input', () => {
    let cpf = cpfInput.value;
    cpf = formatarCPF(cpf); // chama o metodo de formatacao
    cpfInput.value = cpf;

    if (verificarCPFPreenchido(cpf) && verificarNome(nomeInput.value)) {
        concCadastro.disabled = false;
    } else {
        concCadastro.disabled = true;
    }
});

nomeInput.addEventListener('input', () => {
    let nome = nomeInput.value;
    
    if (verificarNome(nome) && verificarCPFPreenchido(cpfInput.value)) {
        concCadastro.disabled = false;
    } else {
        concCadastro.disabled = true;
    }
});



//armazena o nome e cpf do usuario para serem usados no vinculo dos numeros apostados em outro arquivo js
function coletarNomeCPF() {
    const nome = nomeInput.value;
    const cpf = cpfInput.value;
    sessionStorage.setItem('nome', nome);
    sessionStorage.setItem('cpf', cpf);
}







