
/**
 * Representa um estudante
 * 
 * @author Marcos Bertoni 
 * @version 2.0
 */
public class Aluno
{
    private int idade;
    private String nome;
    private String cpf;
    private String telefone;
    private int modCurso;
    private String nomeCurso;
    private int qtdDisciplinas;
    private String [] discMatriculadas;
    
    //Classe Construtor
    public Aluno(String nome, int idade, String cpf, String telefone, int modCurso, String nomeCurso, int qtdDisciplinas, String [] discMatriculadas){
        this.idade=idade;
        this.nome=nome;
        this.cpf=cpf;
        this.telefone=telefone;
        this.modCurso=modCurso;
        this.nomeCurso=nomeCurso;
        this.qtdDisciplinas=qtdDisciplinas;
        this.discMatriculadas=discMatriculadas;
        
        
    }
    
    //Getters

    public String getCpf() {
        return (cpf);
    }
    public String[] getDiscMatriculadas() {
        return (discMatriculadas);
    }
    public int getIdade() {
        return (idade);
    }
    public String getModCurso() {
        switch (modCurso) {
        case 1:
            return "Extensão";
        case 2:
            return "Graduação Tecnológica";
        case 3:
            return "Especialização";
        default:
            return "Tipo de curso desconhecido";
        }
    }
    public String getNome() {
        return (nome);
    }
    public String getNomeCurso() {
        return (nomeCurso);
    }
    public int getQtdDisciplinas() {
        return (qtdDisciplinas);
    }
    public String getTelefone() {
        return (telefone);
    }

    // Setters
    public void setCpf() {
        String cpf;
    }public void setDiscMatriculadas() {
        String[] discMatriculadas;
    }public void setIdade() {
        int idade;
    }public void setModCurso() {
        int modCurso;
    }public void setNome() {
        String nome;
    }public void setNomeCurso() {
        String nomeCurso;
    }public void setQtdDisciplinas() {
        int qtdDisciplinas;
    }public void setTelefone() {
        String telefone;
    }
    
    private String formataCPF(){
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9); 
        }
    
    
    private String formataTel(){
        return "(" + telefone.substring(0,2) + ")" + telefone.substring(2,7) + "-" + telefone.substring(7);
    }
    
    

    
    
    
    public String toString() {
    String cpfFormatado = formataCPF();
    String telFormatado = formataTel();

    String disciplinasMatriculadas = "";
    String[] disciplinas = getDiscMatriculadas(); 

    
    for (int i = 0; i < disciplinas.length; i++) {
        disciplinasMatriculadas += "\n - " + disciplinas[i];
    }

    return "Nome: " + getNome() + "\nIdade: " + getIdade() + "\nCPF: " + cpfFormatado + "\nTelefone: " +
            telFormatado + "\nNome do Curso: " + getNomeCurso() + "\nModalidade do Curso: " + getModCurso() +
            "\nQuantidade de Disciplinas: " + getQtdDisciplinas() + "\nDisciplinas Matriculadas:" +
            disciplinasMatriculadas;
}



}

