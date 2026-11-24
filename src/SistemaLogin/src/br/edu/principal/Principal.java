package br.edu.principal;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		
		String usuarioCorreto = "Carroll";
		String senhaCorreta = "2026";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite seu usuario: ");
		String usuarioDigitado = sc.nextLine();
		
		System.out.println("Digite sua senha:");
		String senhaDigitada = sc.nextLine();
		
		if (usuarioDigitado.equals(usuarioCorreto) && senhaDigitada.equals(senhaCorreta)) {
		    System.out.println("Login realizado com sucesso");
 } else {
		     System.out.println("Usuário ou senha inválidos");   
}	
	}
}