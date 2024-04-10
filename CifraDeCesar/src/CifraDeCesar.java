import java.util.Scanner;
import java.util.Random;

public class CifraDeCesar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a mensagem a ser criptografada: ");
        String mensagem = scanner.nextLine();

        String mensagemCriptografada = criptografarCesarAleatoria(mensagem);
        System.out.println("Mensagem criptografada: " + mensagemCriptografada);

        String mensagemDescriptografada = descriptografarCesarAleatoria(mensagemCriptografada);
        System.out.println("Mensagem descriptografada: " + mensagemDescriptografada);

        scanner.close();
    }

    public static String criptografarCesarAleatoria(String mensagem) {
        StringBuilder resultado = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < mensagem.length(); i++) {
            char caractere = mensagem.charAt(i);
            if (Character.isLetter(caractere)) {
                char base = (caractere >= 'a' && caractere <= 'z') ? 'a' : 'A';
                int chave = random.nextInt(26) + 1;
                char caractereCriptografado = (char) (base + (caractere - base + chave) % 26);
                resultado.append(caractereCriptografado);
                resultado.append((char) ('A' + chave - 1)); // Adiciona a chave à mensagem criptografada
            } else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }

    public static String descriptografarCesarAleatoria(String mensagemCriptografada) {
        StringBuilder resultado = new StringBuilder();
        int i = 0;

        while (i < mensagemCriptografada.length()) {
            char caractere = mensagemCriptografada.charAt(i);
            if (Character.isLetter(caractere)) {
                char base = (caractere >= 'a' && caractere <= 'z') ? 'a' : 'A';
                char chaveCaractere = mensagemCriptografada.charAt(i + 1);
                int chave = chaveCaractere - 'A' + 1; // Recupera a chave da mensagem criptografada
                char caractereDescriptografado = (char) (base + (caractere - base - chave + 26) % 26);
                resultado.append(caractereDescriptografado);
                i += 2; // Pule o próximo caractere, que é a chave
            } else {
                resultado.append(caractere);
                i++;
            }
        }
        return resultado.toString();
    }
}
