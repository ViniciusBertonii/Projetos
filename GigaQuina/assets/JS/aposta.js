const numApostados = [];
const finalizarAposta = document.getElementById("finAposta");
const apostarNovamente = document.getElementById("apostarNovamente");
const numSorteados = [];
let apostas = [];



// Verifica se há dados de apostas armazenados no sessionStorage
const apostasArmazenadas = sessionStorage.getItem('apostas');
if (apostasArmazenadas) {
    apostas = JSON.parse(apostasArmazenadas);
}


finalizarAposta.disabled = true;
apostarNovamente.disabled = true;

//armazena nas variaveis o conteudo que recuperou da pagina anterior
function obterNomeCPF() {
    const nome = sessionStorage.getItem('nome');
    const cpf = sessionStorage.getItem('cpf');
    return { nome, cpf };
}

//insere uma aposta com os parametros cod,nome, cpf e numeros em um espaco do array apostas
function registarAposta() {
    const { nome, cpf } = obterNomeCPF();
    const codigoUnico = 1000 + apostas.length;

    const novaAposta = {
        codigoUnico: codigoUnico,
        nome: nome,
        cpf: cpf,
        numeros: numApostados
    }

    apostas.push(novaAposta);


    finalizarAposta.disabled = false;
    novaAposta.disabled = false;

    armazenarApostas();
}
//direciona a próxima tela
function finalizaAposta() {
    
window.location.href = "./sorteio.html";
console.log('Apostas registradas:');
apostas.forEach(aposta => {
    console.log(`Código: ${aposta.codigoUnico}, Nome: ${aposta.nome}, CPF: ${aposta.cpf}, Números: ${aposta.numeros}`);
});
    
}
//bloqueia os escolhidos e chama a funcao exibeApostados ao clicar no numero.
function selecNum(numero) {
    if (numApostados.length < 5) {
        numApostados.push(numero);
        console.log(numApostados);
        exibeApostados();
        bloqEscolhidos(numero);
    }

    if (numApostados.length === 5) {
        finalizarAposta.disabled = false;
        apostarNovamente.disabled = false;
    }
}

function bloqEscolhidos(numero) {
    document.getElementById("num" + numero).disabled = true;
    document.getElementById("num" + numero).style.color = "#ffffff";
    document.getElementById("num" + numero).style.background = "#009e4c";
}

function exibeApostados() {
    let exibeEscolhido1 = document.getElementById("escolhido1")
    let exibeEscolhido2 = document.getElementById("escolhido2")
    let exibeEscolhido3 = document.getElementById("escolhido3")
    let exibeEscolhido4 = document.getElementById("escolhido4")
    let exibeEscolhido5 = document.getElementById("escolhido5")

    exibeEscolhido1.innerHTML = numApostados[0]
    exibeEscolhido2.innerHTML = numApostados[1]
    exibeEscolhido3.innerHTML = numApostados[2]
    exibeEscolhido4.innerHTML = numApostados[3]
    exibeEscolhido5.innerHTML = numApostados[4]
}

document.getElementById("supresinha").addEventListener('click', function() {
    apostaSurpresinha();
    
});

finalizarAposta.addEventListener('click', function() {
    registarAposta();
    finalizaAposta();
});

apostarNovamente.addEventListener('click', function() {
    registarAposta();
    novaAposta();

});




function armazenarApostas() {
    // Armazena as apostas no sessionStorage
    sessionStorage.setItem('apostas', JSON.stringify(apostas));
}

function novaAposta(){
    numApostados.length = 0; 
    finalizarAposta.disabled = true; 
    apostarNovamente.disabled = true; 
    // Reabilita os botões de seleção de números
    for (let i = 1; i <= 10; i++) {
        document.getElementById("num" + i).disabled = false;
        document.getElementById("num" + i).style.color = "";
        document.getElementById("num" + i).style.background = "";
    }
    // Limpa a exibição dos números apostados
    let exibeEscolhido1 = document.getElementById("escolhido1");
    let exibeEscolhido2 = document.getElementById("escolhido2");
    let exibeEscolhido3 = document.getElementById("escolhido3");
    let exibeEscolhido4 = document.getElementById("escolhido4");
    let exibeEscolhido5 = document.getElementById("escolhido5");
    exibeEscolhido1.innerHTML = "";
    exibeEscolhido2.innerHTML = "";
    exibeEscolhido3.innerHTML = "";
    exibeEscolhido4.innerHTML = "";
    exibeEscolhido5.innerHTML = "";

    window.location.href = "../../index.html";
}

function apostaSurpresinha() {
    let contador = 0;
    numApostados.length = 0;

    while (contador < 5) {
        let numeroAleatorio = Math.round(Math.random() * 49 + 1);

        if (!numApostados.includes(numeroAleatorio)) {
            numApostados.push(numeroAleatorio);
            contador++;
        }
    }

    exibeApostados();

    apostarNovamente.disabled = false;
    finalizarAposta.disabled = false;
}












