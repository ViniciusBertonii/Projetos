
import java.util.Scanner;


public class Aplicacao{
    
    public static void main(String[] args) {
        CadastroEstudantes cadastro = new CadastroEstudantes();
        
        Scanner in = new Scanner(System.in);
        int opcao;
        
        String[] disciplinasAluno1 = {"Matemática", "Física", "Química"};
        Aluno aluno1 = new Aluno("Joao", 20, "12345678901", "51999999999", 1, "Ciências", 3, disciplinasAluno1);

        String[] disciplinasAluno2 = {"História", "Geografia", "Português"};
        Aluno aluno2 = new Aluno("Maria", 22, "98765432109", "51888888888", 2, "História", 3, disciplinasAluno2);

        String[] disciplinasAluno3 = {"Programação", "Banco de Dados", "Redes"};
        Aluno aluno3 = new Aluno("Pedro", 25, "13579246801", "51777777777", 3, "Informática", 3, disciplinasAluno3);
        
        cadastro.adicionaEstudante(aluno1);
        cadastro.adicionaEstudante(aluno2);
        cadastro.adicionaEstudante(aluno3);
       
        

        
        
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = in.nextInt();
            in.nextLine(); 

            switch (opcao) {
                case 1:
                    
                    adicionarNovoEstudante(in, cadastro);
                    break;
                case 2:
                    
                    System.out.print("Digite o nome do estudante a ser removido: ");
                    String nomeRemover = in.nextLine();
                    cadastro.removeEstudante(nomeRemover);
                    break;
                case 3:
                    cadastro.escreveAlunosVetor();
                    break;
                    
                    
                case 4:
                    cadastro.escreveNomeCursoAlfab();
                    break;
                    
                    
                case 5:
                    double mediaIdade = cadastro.medIdadeAlunos();
                    System.out.println("A média de idade dos alunos é: " + mediaIdade);
                    break;
                    
                    
                case 6:
                    cadastro.nomeMaiorVogais();
                    break;
                   
                    
                case 7:
                    cadastro.proporcaoDeCursos();
                    break;
                    
                    
                case 8:
                    System.out.println("Saindo do programa...");
                    break;
    
                    
                default:
                    System.out.println("Opção inválida. Escolha uma opção de 1 a 7.");
                    break;
            }
        } while (opcao != 8);

        in.close();
        }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Adicionar novo estudante");
        System.out.println("2. Remover estudante");
        System.out.println("3. Listar alunos cadastrados");
        System.out.println("4. Listar alunos por ordem alfabética");
        System.out.println("5. Calcular média de idade dos alunos");
        System.out.println("6. Encontrar aluno com maior número de vogais no nome");
        System.out.println("7. Calcular proporção de alunos por tipo de curso");
        System.out.println("8. Sair do programa");
        
    }
    

        private static void adicionarNovoEstudante(Scanner in, CadastroEstudantes cadastro) {
        System.out.print("Digite o nome do novo estudante: ");
        String nome = in.nextLine();

        int idade = 0;
        boolean idadeValida = false;
        do {
            System.out.print("Digite a idade: ");
            String verificaIdade = in.nextLine();

            if (verificaIdade.matches("\\d+") && Integer.parseInt(verificaIdade) >= 0) {
                idade = Integer.parseInt(verificaIdade);
                idadeValida = true;
            } else {
                System.out.println("[Erro] Digite um número inteiro positivo.");
            }
        } while (!idadeValida);
        

        String cpf;
        boolean cpfValido = false;
        do {
            System.out.print("Digite o CPF (11 dígitos numéricos): ");
            cpf = in.nextLine();

            if (cpf.length() != 11 || !cpf.matches("[0-9]+")) {
                System.out.println("CPF inválido! Deve ter exatamente 11 dígitos numéricos.");
            } else {
                cpfValido = true;
            }
        } while (!cpfValido);
        
        String telefone;
        boolean telefoneValido = false;
        
        do {
            System.out.print("Digite o telefone do aluno (DDD sem o zero + numero com o nove na frente!): ");
            telefone = in.nextLine();

            if (telefone.length() != 11 || !telefone.matches("[0-9]+")) {
                System.out.println("Telefone inválido! Deve ter exatamente 11 dígitos numéricos.");
            } else {
                telefoneValido = true;
            }
        } while (!telefoneValido);
        
        
        
        System.out.print("Digite o modo do curso (1 - Extensão, 2 - Graduação Tecnológica, 3 - Especialização): ");
        int modCurso = in.nextInt();
        in.nextLine(); 
        
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = in.nextLine(); 
        
        int qtdDisciplinas = 0;
        boolean qtdDisciplinasValida = false;
        do {
            System.out.print("Digite a quantidade de disciplinas (entre 2 e 6): ");
            String verificaQtdDisciplinas = in.nextLine();

            if (verificaQtdDisciplinas.matches("[2-6]")) {
                qtdDisciplinas = Integer.parseInt(verificaQtdDisciplinas);
                qtdDisciplinasValida = true;
            } else {
                System.out.println("Erro: Digite um número entre 2 e 6 para a quantidade de disciplinas.");
            }
        } while (!qtdDisciplinasValida);

        

        String[] discMatriculadas = new String[qtdDisciplinas];
        for (int i = 0; i < qtdDisciplinas; i++) {
            System.out.print("Digite a disciplina " + (i + 1) + ": ");
            discMatriculadas[i] = in.nextLine();
        }

        
        Aluno novoAluno = new Aluno (nome, idade, cpf, telefone, modCurso, nomeCurso, qtdDisciplinas, discMatriculadas);
        cadastro.adicionaEstudante(novoAluno);
    }
}
