const apostasArmazenadas = sessionStorage.getItem('apostas');
let apostas = [];

if (apostasArmazenadas) {
    apostas = JSON.parse(apostasArmazenadas);
}

console.log('Apostas registradas:');
apostas.forEach(aposta => {
    console.log(`Código: ${aposta.codigoUnico}, Nome: ${aposta.nome}, CPF: ${aposta.cpf}, Números: ${aposta.numeros}`);
});

const ganhadoresArmazenados = sessionStorage.getItem('ganhadores');
if (ganhadoresArmazenados) {
    ganhadores = JSON.parse(ganhadoresArmazenados);
}

console.log(`ganhadores`);
ganhadores.forEach(ganhadores => {
  console.log(`Código: ${ganhadores.codigoUnico}, Nome: ${ganhadores.nome}, CPF: ${ganhadores.cpf}, Números: ${ganhadores.numeros}`);
});




function atualizarApostadores() {
    const listaApostadores = document.getElementById('apostadoresOrdem');
    listaApostadores.innerHTML = '';

    // ordena em ordem alfabetica os dados dos apostadores
    const apostadoresOrdenados = apostas.map(aposta => aposta.nome).sort();

    const listaGanhadores = [];
    
    apostadoresOrdenados.forEach(nome => {
        const lista = document.createElement('li');
        const apostador = apostas.find(aposta => aposta.nome === nome);
        lista.textContent = `${apostador.nome} - CPF: ${apostador.cpf} - Números: ${apostador.numeros.join(', ')}`;
        listaApostadores.appendChild(lista);
        
        // verificar se o apostador está entre os ganhadores
        if (ganhadores.some(ganhador => ganhador.nome === nome)) {
            listaGanhadores.push(apostador);
        }
    });

    // verifique se há ganhadores
    if (listaGanhadores.length > 0) {
        // se houver ganhadores, exibe os ganhadores no footer
        const listaGanhadoresElement = document.getElementById('ganhadores');
        listaGanhadoresElement.innerHTML = '';
        listaGanhadores.forEach(ganhador => {
            const listItem = document.createElement('li');
            listItem.textContent = `Nome: ${ganhador.nome}, CPF: ${ganhador.cpf}, Números: ${ganhador.numeros.join(', ')}`;
            listaGanhadoresElement.appendChild(listItem);
        });
    } else {
        // se não houver ganhadores, a mensagem no footer é mantida
        const mensagemVencedores = document.getElementById('mensagemVencedores');
        mensagemVencedores.textContent = "Não Houveram Vencedores";
    }
}


// conta a frequência de todos os números apostados
function contarNumerosSorteados() {
    const numerosFrequencia = {};
    apostas.forEach(aposta => {
        aposta.numeros.forEach(numero => {
            if (numerosFrequencia[numero]) {
                numerosFrequencia[numero]++;
            } else {
                numerosFrequencia[numero] = 1;
            }
        });
    });
    return Object.entries(numerosFrequencia).sort((a, b) => b[1] - a[1]);
}

// atualiza a lista de números sorteados
function atualizarNumerosSorteados() {
    const listaNumerosSorteados = document.getElementById('numerosMaisSorteados');
    listaNumerosSorteados.innerHTML = '';
    const numerosMaisSorteados = contarNumerosSorteados();
    numerosMaisSorteados.forEach(parNumeroFrequencia => {
        const lista = document.createElement('li');
        lista.textContent = `${parNumeroFrequencia[0]} - Apareceu ${parNumeroFrequencia[1]} vezes`;
        listaNumerosSorteados.appendChild(lista);
    });
}

// chama as funcoes para mostrar os dados apurados.
atualizarNumerosSorteados();
atualizarApostadores();

