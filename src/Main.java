
public class Main {
	
	public static void main(String[] args) {
		System.out.println("oi");
		int[] x = {5, 4, 3, 2, 1, 0};
		
		ordenarBubbleSort(x);
		
		imprimirVetor(x);
		
	}
	
	public static void ordenarBubbleSort(int[] vetor) {
		int tamanhoVetor = obterTamanhoVetor(vetor);
		
		// -1 no loop externo serve porque foi percebido 
		// que tudo já fica ordenado em uma iteração a menos.
		//
		// -1 no loop interno existe pra não dar array out of
		// bounds, porque quando chegar no último b+1 faria
		// eu comparar com um índice após o último.
		// 
		// c serve pra não comparar com os maiores números que foram
		// "levados pela bolha" até o final na última iteração.
		// Pois eles já chegaram na posição certa.	
		for (int a = 0, c = 0; a < tamanhoVetor - 1; a++, c++) {
			for (int b = 0; b < tamanhoVetor - 1 - c; b++) {
				if (vetor[b] > vetor[b+1]) {
					int temp = vetor[b+1];
					vetor[b+1] = vetor[b];
					vetor[b] = temp;
				}
			}
		}
		
	}
	
	public static void imprimirVetor(int[] vetor) {
		int tamanho = obterTamanhoVetor(vetor);
		
		for (int i = 0; i < tamanho; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println();
	}
	
	private static int obterTamanhoVetor(int[] vetor) {	
		for (int i = 0, aux; ; i++) {
			try {
				aux = vetor[i];
			} catch (ArrayIndexOutOfBoundsException e) {
				return i;
			}
		}
	}

}
