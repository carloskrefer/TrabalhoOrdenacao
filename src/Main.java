
public class Main {
	
	public static void main(String[] args) {
		System.out.println("oi");
		int[] x = {5, 4, 6, 66, 0};
		
		// remover função LENGTH
		ordenarMergeSort(x, 0, x.length - 1);
		
		imprimirVetor(x);
		
		
		
	}
	
	public static void ordenarMergeSort(int[] vetor, int indiceInicio, int indiceFim) {	
		int tamanhoVetor = indiceFim - indiceInicio + 1;
		
		if (tamanhoVetor > 2) {		
			int tamanhoVetorMetadeEsquerda = tamanhoVetor / 2;
			int indiceInicioMetadeEsquerda = indiceInicio;
			int indiceFimMetadeEsquerda = indiceInicio + tamanhoVetorMetadeEsquerda - 1;
			
			int indiceInicioMetadeDireita = indiceFimMetadeEsquerda + 1;
			int indiceFimMetadeDireita = indiceFim;
			
			ordenarMergeSort(vetor, indiceInicioMetadeEsquerda, indiceFimMetadeEsquerda);
			ordenarMergeSort(vetor, indiceInicioMetadeDireita,  indiceFimMetadeDireita);
			
			ordenarPartesOrdenadasDumVetorNoMesmoVetor(vetor, indiceInicioMetadeEsquerda, indiceFimMetadeEsquerda,
					indiceInicioMetadeDireita, indiceFimMetadeDireita);	
			
		}  else if (tamanhoVetor == 2) {
			if (vetor[indiceInicio] > vetor[indiceFim]) {
				int temp 			= vetor[indiceInicio];
				vetor[indiceInicio] = vetor[indiceFim];
				vetor[indiceFim] 	= temp;
			}
		}
	}
	
	// Já foram ordenados pedacinhos esquerdo e direito do vetor. Aqui vamos unir eles de maneira ordenada no mesmo vetor.
	public static void ordenarPartesOrdenadasDumVetorNoMesmoVetor(int[] vetor, int indiceInicioMetadeEsquerdaOrdenada,
			int indiceFimMetadeEsquerdaOrdenada, int indiceInicioMetadeDireitaOrdenada, int indiceFimMetadeDireitaOrdenada) {

		int tamanhoVetorDestinoQueSeraOrdenado = indiceFimMetadeDireitaOrdenada - indiceInicioMetadeEsquerdaOrdenada + 1;
		
		// Este 'vetorAuxOrdenado' existe porque:
		// Se 'vetor' = ... 4, 5, 6, 1, 2, 3, ...
		// Sendo a parte esquerda 4, 5, 6 e a parte direita 1, 2, 3, então neste exemplo na ordenação
		// a parte direita seria salva por cima da parte esquerda, perdendo os dados da parte esquerda. Por isso preciso do
		// 'vetorAuxOrdenado' pra guardar o resultado ordenado para no final eu copiar os dados dele no 'vetor'.
		int[] vetorAuxOrdenado = new int[tamanhoVetorDestinoQueSeraOrdenado];
		
		for (int i = 0, e = indiceInicioMetadeEsquerdaOrdenada, 
				d = indiceInicioMetadeDireitaOrdenada; i < tamanhoVetorDestinoQueSeraOrdenado; i++) {
			
			boolean isParteEsquerdaEsgotada = (e > indiceFimMetadeEsquerdaOrdenada);
			boolean isParteDireitaEsgotada =  (d > indiceFimMetadeDireitaOrdenada);
			
			if (isParteEsquerdaEsgotada) {
				vetorAuxOrdenado[i] = vetor[d];
				d++;
				continue;
			}
			if (isParteDireitaEsgotada) {
				vetorAuxOrdenado[i] = vetor[e];
				e++;
				continue;
			}
			
			if (vetor[e] < vetor[d]) {
				vetorAuxOrdenado[i] = vetor[e];
				e++;
			} else {
				vetorAuxOrdenado[i] = vetor[d];
				d++;
			}
		}
		
		copiarTodosDadosVetorOrigemNumTrechoVetorDestino(vetorAuxOrdenado, vetor, 
				indiceInicioMetadeEsquerdaOrdenada, indiceFimMetadeDireitaOrdenada);
		
	}
	
	public static void copiarTodosDadosVetorOrigemNumTrechoVetorDestino(int[] vetorOrigem, int[] vetorDestino, 
			int indiceInicioTrechoVetorDestino, int indiceFimTrechoVetorDestino) {
		for(int id = indiceInicioTrechoVetorDestino, io = 0; id <= indiceFimTrechoVetorDestino; id++, io++) {
			vetorDestino[id] = vetorOrigem[io];
		}
	}
	
	public static void ordenarBubbleSort(int[] vetor, int tamanhoVetor) {
		
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
