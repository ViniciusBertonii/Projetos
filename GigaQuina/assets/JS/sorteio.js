let ganhadores = [];
let resultado = [];

const apostasArmazenadas = sessionStorage.getItem('apostas');
if (apostasArmazenadas) {
    apostas = JSON.parse(apostasArmazenadas);

}


console.log('Apostas registradas:');
apostas.forEach(aposta => {
    console.log(`Código: ${aposta.codigoUnico}, Nome: ${aposta.nome}, CPF: ${aposta.cpf}, Números: ${aposta.numeros}`);
});



function apurarResultado(ganhadores) {
    // Armazena os ganhadores no sessionStorage para exibição na página de apuração
    sessionStorage.setItem('ganhadores', JSON.stringify(ganhadores));
    
    // Redireciona para a página de apuração
    window.location.href = "./apuracao.html";
}



// funçao para realizar o sorteio com a possibilidade de repetir até 25x
function sorteioComRepeticao() {
    let contador = 0;
    let totalTentativas = 0;
    const maximoTentativas = 25;

    
    

    const interval = setInterval(() => {
        if (contador < 5) {
            let sorteado = Math.round(Math.random() * 49 + 1); //vai ate 49 + 1 para nao sortear o zero 

            while (resultado.includes(sorteado)) {// caso o numero sorteado já exista no array resultado, um novo numero é sorteado
                sorteado = Math.round(Math.random() * 49 + 1);
            }

            resultado.push(sorteado);

            const elementoSorteado = document.getElementById("Sorteado" + (contador + 1));
            elementoSorteado.innerHTML = sorteado;
            
            contador++;
        } else {
            const ganhadores = verificarGanhador(resultado);
                //define o criterio para continuar sorteando
            if (ganhadores.length === 0 && totalTentativas < maximoTentativas) {
                // caso não houver ganhadores e o número total de tentativas for menor que o máximo permitido, reinicia o sorteio.
                contador = 0;
                resultado = [];
                totalTentativas++;
            } else {
                
                clearInterval(interval);
                apurarResultado(ganhadores);
            }
        }
    }, 100); //
}

//verifica ganhadores

function verificarGanhador(resultadoSorteio) {
    const ganhadores = [];
    
    apostas.forEach(aposta => {
        // verifica se os números apostados pelo jogador correspondem ao resultado do sorteio
        const numerosApostados = aposta.numeros;
        const numerosAcertados = numerosApostados.filter(numero => resultadoSorteio.includes(numero));
        
        // se o jogador acertou todos os números, adiciona-o à lista de ganhadores
        if (numerosAcertados.length === 5 && numerosApostados.every(numero => resultadoSorteio.includes(numero))) {
            ganhadores.push(aposta);
        }
    });
    
    return ganhadores;
}


// função para apurar o resultado do sorteio e passar para proxima tela
function apurarResultado(ganhadores) {
    // armazena os ganhadores no sessionStorage para exibição na página de apuração
    sessionStorage.setItem('ganhadores', JSON.stringify(ganhadores));
    
    // redireciona para a página de apuração
    window.location.href = "./apuracao.html";
}


sorteioComRepeticao();



