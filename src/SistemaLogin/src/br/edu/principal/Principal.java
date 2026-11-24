package br.edu.principal;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    
    static ArrayList<String> usuarios = new ArrayList<>();
    static ArrayList<String> senhas = new ArrayList<>();

    public static void main(String[] args) {

        String usuarioCorreto = "Carroll";
        String senhaCorreta = "2026";
        int tentativas = 3;

        Scanner sc = new Scanner(System.in);
        
        //Aqui inicio o "while" pra definir a quantidade de tentativas antes de bloquear.

        while (tentativas > 0) {
            System.out.println("Digite seu usuario:");
            String usuarioDigitado = lerTexto(sc);

            System.out.println("Digite sua senha:");
            String senhaDigitada = lerTexto(sc);
            
            //Verificação de campos vazios
            
            if (campoVazio(usuarioDigitado, senhaDigitada)) {
                
                System.out.println("Preencha todos os campos!");
                } else if (usuarioDigitado.equals(usuarioCorreto) && senhaDigitada.equals(senhaCorreta)) {
                System.out.println("Login realizado com sucesso");
                break;
            } else {
                tentativas--;
                System.out.println("Usuário ou senha inválidos");
                if (tentativas == 0) {
                    System.out.println("Tentativas esgotadas de login.");
                    }
            }
        }
    }
    
    //Verificação de campos vazios

    public static boolean campoVazio(String usuarioDigitado, String senhaDigitada) {
        return usuarioDigitado.isEmpty() ||
               senhaDigitada.isEmpty();
    }
    
    public static String lerTexto(Scanner sc) {
    return sc.nextLine().trim();
    }
}
