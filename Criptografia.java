import javax.swing.JOptionPane;

public class Cripto2 {

	public static void main(String[] args) {
		String[] alfabeto = gerar_alfabeto();
		String[] alfabeto_espelhado = gerar_alfabeto_espelhado();
		String opcao = "a";
		while(!opcao.equals("0")) {
			opcao =  JOptionPane.showInputDialog("Bem vindo ao sistema de criptografia\n"
					+ "\nPor favor, escolha uma opcao"
					+ "\n1 - Criptografar uma mensagem"
					+ "\n2 - Descriptografar uma mensagem"
					+ "\n0 - sair"
					+ "\n\n");
			if(opcao.equals("1")) {
				String frase = JOptionPane.showInputDialog("Informe a mensagem que deseja criptografar\n");
				frase = frase.toUpperCase();
				char[] frase_quebrada = gerar_vetor_letras_frase(frase);
				String frase_criptografada = criptografar(frase_quebrada);
				JOptionPane.showMessageDialog(null,"A frase digitada se tornou o seguinte codigo ao ser criptografada:\n"+frase_criptografada);

			}
			else if(opcao.equals("2")) {
				String frase = JOptionPane.showInputDialog("Informe a mensagem que deseja criptografar\n");
				frase = frase.toUpperCase();
				char[] frase_quebrada = gerar_vetor_letras_frase(frase);
				String frase_descriptografada = descriptografar(frase_quebrada);
				JOptionPane.showMessageDialog(null,"O codigo digitado se tornou a seguinte mensagem ao ser descriptografado:\n"+ frase_descriptografada);
			}
		}


	}
public static String[] gerar_alfabeto() {
		String alfabeto_completo = "A;B;C;D;E;F;G;H;I;J;K;L;M;N;O;P;Q;R;S;T;U;V;W;X;Y;Z;0;1;2;3;4;5;6;7;8;9";
		String[] alfabeto = new String[26];
		alfabeto = alfabeto_completo.split(";");
		return alfabeto;
	}
	public static String[] gerar_alfabeto_espelhado() {
		String alfabeto_espelhado = "9;8;7;6;5;4;3;2;1;0;Z;Y;X;W;V;U;T;S;R;Q;P;O;N;M;L;K;J;I;H;G;F;E;D;C;B;A";
		String[] alfabeto = new String[26];
		alfabeto = alfabeto_espelhado.split(";");
		return alfabeto;
	}
	
	public static String criptografar(char[] frase_quebrada) {
		String[] alfabeto = gerar_alfabeto();
		String[] alfabeto_espelhado = gerar_alfabeto_espelhado();
		String frase="";
		for(int i=0; i<frase_quebrada.length;i++) {
			int j = achar_pos_alfabeto(frase_quebrada[i], gerar_alfabeto()) + i;
			
			if (frase_quebrada[i] == ' ') {
				frase += " ";
			}
			else if(j%2==0) {
				while(j>=37)
					j-=36;
				if((j-1) < 0 )
					j = 36;
				frase += alfabeto_espelhado[j-1];
				//26
				//26 - 1 = 25 (z) (a)
			}
			else {
				while(j>=35)
					j -= 36;
				//-1
				//0 (a) (z)					
				frase += alfabeto_espelhado[j+1];
			}
		}
		return frase;
	}
	public static String descriptografar(char[] frase_quebrada) {
		String[] alfabeto = gerar_alfabeto();
		String[] alfabeto_espelhado = gerar_alfabeto_espelhado();
		String frase="";
		for(int i=0; i<frase_quebrada.length;i++) {
			int j = achar_pos_alfabeto(frase_quebrada[i], gerar_alfabeto_espelhado());
			if (frase_quebrada[i] == ' ') {
				frase += " ";
			}
			else{
				if(j%2==0) {
					if((j-1) < 0)
						j = 35;
					else
						j--;
				}
					//frase += alfabeto_espelhado[achar_pos_alfabeto(frase_quebrada[i], gerar_alfabeto())-1];
				else {
					if((j+1)>35)
						j=0;
					else
						j++;
					//frase += alfabeto_espelhado[achar_pos_alfabeto(frase_quebrada[i], gerar_alfabeto())+1];
				}
				j-=i;
				while(j<0)
					j+=36;
				frase += alfabeto[j];
			}
		}
		return frase;
	}
	
	
	public static char[] gerar_vetor_letras_frase(String frase) {
		char[] vetor_letras_frase = new char[frase.length()];
		for(int i= 0; i<frase.length(); i++) {
			vetor_letras_frase[i] = frase.charAt(i); 
		}
		return vetor_letras_frase;
	}
	
	public static int achar_pos_alfabeto(char letra, String [] alfabeto) {
		int i;
		for(i=0; i<alfabeto.length;i++ ) {
			if(letra == alfabeto[i].charAt(0))
				return i;
		}

		return 0;
	}
}