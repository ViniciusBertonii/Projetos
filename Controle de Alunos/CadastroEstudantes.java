import java.util.Random;


public class CadastroEstudantes{
    private Aluno [] vetorEstudantes;
    private int index; 
    
    
    
    public CadastroEstudantes(){
        this.vetorEstudantes = new Aluno[10];
        this.index = 0;
    }
    
    public void adicionaEstudante(Aluno aluno){
        if (index<vetorEstudantes.length){
            vetorEstudantes[index] = aluno;
            index++;
            System.out.println("Novo estudante adicionado");
            
        }
        else{
            System.out.println(" <[ERRO]> O numero de vetorEstudantes chegou ao limite!!");
        }
    }
    
    public void removeEstudante(String nome){
        boolean achou = false;
        for(int i= 0; i< index; i++){
            if(vetorEstudantes[i].getNome().equalsIgnoreCase(nome)){
                for(int j = i; j<index -1; j++){
                    vetorEstudantes[j] = vetorEstudantes [j+1];
                }
                index--;
                achou = true;
                System.out.println("Estudante Removido do Sistema");
                break;
            }
        }
            if(!achou){
                System.out.println("[ERRO] Estudante não encontrado");
            }
    }
    
    public void escreveAlunosVetor() {
    for (int i = 0; i < index; i++) {
        if (vetorEstudantes[i] != null) {
            System.out.println("Aluno " + (i + 1) + ":");
            
            System.out.println(vetorEstudantes[i].toString());
        }
    }
}

    
    public void escreveNomeCursoAlfab(){
        for(int i = 0; i<index -1; i++){
            for (int j=0; j<index - i-1; j++){
                if(vetorEstudantes[j].getNome().compareToIgnoreCase(vetorEstudantes[j+1].getNome())>0){
                    Aluno aux = vetorEstudantes[j];
                    vetorEstudantes[j] = vetorEstudantes[j+1];
                    vetorEstudantes[j+1] = aux;
                }
            }
        }
        for(int i=0;i<index;i++){
            System.out.println(vetorEstudantes[i].getNome() + " - Curso: " + vetorEstudantes[i].getNomeCurso());
        }
    }
    public double medIdadeAlunos(){
        int somaIdades = 0;
        for (int i=0; i<index; i++){
            somaIdades += vetorEstudantes[i].getIdade();
        }
        if(index>0){
            return (double) somaIdades / index;
        }
        else{
            return 0.0;
        }
    }
    public void nomeMaiorVogais(){
        int maxVogais = 0;
        String alunos = "";
        
        for (int i=0;i<index;i++){
            int vogais =  indexarVogais(vetorEstudantes[i].getNome());
            if (vogais>maxVogais){
                maxVogais = vogais;
                alunos = vetorEstudantes[i].getNome() + ", "; 
            }else if(vogais == maxVogais){
                alunos += vetorEstudantes[i].getNome() + ", "; 
            }
        }
        if(!alunos.isEmpty()){ //metodo isEmpty (fonte chatGPT) verifica se a string alunos está vazia, se não estiver, ele entra no bloco condicional para imprimir os nomes dos alunos que tem o maior numero de vogais em seus nomes
                alunos = alunos.substring(0, alunos.length() - 2);
                System.out.println("Aluno com maior numero de vogais no nome: " + alunos);
        }
        }
    public void proporcaoDeCursos(){
            int extensao = 0;
            int gradTecnologica = 0;
            int especializacao = 0;
            
            for (int i = 0; i<index;i++){
                switch(vetorEstudantes[i].getModCurso()){
                    case "Extensão": 
                        extensao++;
                        break;
                    case "Graduação Tecnológica":
                        gradTecnologica++;
                        break;
                    case "Especialização": especializacao++;
                    break;
                }
            }
            System.out.println("A proporcao de vetorEstudantes por tipo de curso é: ");
            System.out.println("Extensão: " + ((double) extensao/index) * 100 + "%");
            System.out.println("Graduacao Tecnologica: " + ((double) gradTecnologica/index) * 100 + "%");
            System.out.println("Especializacao: " + ((double) especializacao/index) * 100 + "%");
        }  
        
        private void alocarAlunosAleatoriamente() {
        Random random = new Random();

        for (int i = 0; i < index; i++) {
            int posicaoAleatoria;
            do {
                posicaoAleatoria = random.nextInt(vetorEstudantes.length);
            } while (vetorEstudantes[posicaoAleatoria] != null);

            vetorEstudantes[posicaoAleatoria] = vetorEstudantes[i];
            vetorEstudantes[i] = null;
        }
    }
    private int indexarVogais(String nome) {
    int vogais = 0;
    
    for (int i = 0; i < nome.length(); i++) {
        char ch = Character.toLowerCase(nome.charAt(i));
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
            || ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú'
            || ch == 'à' || ch == 'è' || ch == 'ì' || ch == 'ò' || ch == 'ù'
            || ch == 'ã' || ch == 'ẽ' || ch == 'ĩ' || ch == 'õ' || ch == 'ũ'
            || ch == 'â' || ch == 'ê' || ch == 'î' || ch == 'ô' || ch == 'û'
            || ch == 'ä' || ch == 'ë' || ch == 'ï' || ch == 'ö' || ch == 'ü') {
            vogais++;
        }
    }
    return vogais;
}
}
