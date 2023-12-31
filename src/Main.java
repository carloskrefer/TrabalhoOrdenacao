/*
 * Aluno: Carlos Eduardo Krefer
 * TDE 03 RA 04 - Estrutura de Dados
 * 07/11/2023
 */

import java.util.Random;

public class Main {
	public static int numeroTrocas = 0;
	public static int numeroIteracoes = 0;
	
	public static double somaTempoSegundos = 0;
	
	public static final int[] VETOR_QTDS_DADOS_TESTES = {50, 500, 1000, 5000, 10000};
	
	public static void main(String[] args) {
		long inicioCronometro;
		long fimCronometro;
		int[] vetorGeradoAleatoriamente;

		System.out.println("Todos resultados foram obetidos pela média de 5 execuções.\n");
		
		// ----------------------------------------------------------------------------------------------
		
		System.out.println("BubbleSort");
		for (int i = 0; i < obterTamanhoVetor(VETOR_QTDS_DADOS_TESTES); i++) {
			System.out.println("\tTamanho: " + 
					VETOR_QTDS_DADOS_TESTES[i]);
							
			for (int j = 0; j < 5; j++) {
				vetorGeradoAleatoriamente = gerarVetorAleatorioMesmaSemente(VETOR_QTDS_DADOS_TESTES[i]);
				
				inicioCronometro = System.nanoTime();
				ordenarBubbleSort(vetorGeradoAleatoriamente, VETOR_QTDS_DADOS_TESTES[i]);
				fimCronometro    = System.nanoTime();
				somaTempoSegundos += obterDiferencaTempoSegundos(inicioCronometro, fimCronometro);
				
				if (!conferirSeEstaOrdenado(vetorGeradoAleatoriamente, VETOR_QTDS_DADOS_TESTES[i])) {
					throw new RuntimeException("Erro! Ordenação não ocorreu!");
				}
			}
			
			System.out.printf("\t\tTempo execução: %.9f s\n", (somaTempoSegundos / 5.0));
			System.out.println("\t\tNúmero de trocas: " + (numeroTrocas / 5));
			System.out.println("\t\tNúmero de iterações: " + (numeroIteracoes / 5));	
			zerarVariaveisTeste();
		}
		
		// ----------------------------------------------------------------------------------------------
		
		System.out.println("MergeSort");
		for (int i = 0; i < obterTamanhoVetor(VETOR_QTDS_DADOS_TESTES); i++) {
			System.out.println("\tTamanho: " + 
					VETOR_QTDS_DADOS_TESTES[i]);
							
			for (int j = 0; j < 5; j++) {
				vetorGeradoAleatoriamente = gerarVetorAleatorioMesmaSemente(VETOR_QTDS_DADOS_TESTES[i]);
				
				inicioCronometro = System.nanoTime();
				ordenarMergeSort(vetorGeradoAleatoriamente, 0, VETOR_QTDS_DADOS_TESTES[i] - 1);
				fimCronometro    = System.nanoTime();
				somaTempoSegundos += obterDiferencaTempoSegundos(inicioCronometro, fimCronometro);
				
				if (!conferirSeEstaOrdenado(vetorGeradoAleatoriamente, VETOR_QTDS_DADOS_TESTES[i])) {
					throw new RuntimeException("Erro! Ordenação não ocorreu!");
				}
			}
			
			System.out.printf("\t\tTempo execução: %.9f s\n", (somaTempoSegundos / 5.0));
			System.out.println("\t\tNúmero de trocas: " + (numeroTrocas / 5));
			System.out.println("\t\tNúmero de iterações: " + (numeroIteracoes / 5));	
			zerarVariaveisTeste();
		}
			
		// ----------------------------------------------------------------------------------------------
		
		System.out.println("QuickSort");
		for (int i = 0; i < obterTamanhoVetor(VETOR_QTDS_DADOS_TESTES); i++) {
			System.out.println("\tTamanho: " + 
					VETOR_QTDS_DADOS_TESTES[i]);
							
			for (int j = 0; j < 5; j++) {
				vetorGeradoAleatoriamente = gerarVetorAleatorioMesmaSemente(VETOR_QTDS_DADOS_TESTES[i]);
				
				inicioCronometro = System.nanoTime();
				ordenarQuickSort(vetorGeradoAleatoriamente, 0, 1, VETOR_QTDS_DADOS_TESTES[i] - 1, 0, VETOR_QTDS_DADOS_TESTES[i] - 1);
				fimCronometro    = System.nanoTime();
				somaTempoSegundos += obterDiferencaTempoSegundos(inicioCronometro, fimCronometro);
				
				if (!conferirSeEstaOrdenado(vetorGeradoAleatoriamente, VETOR_QTDS_DADOS_TESTES[i])) {
					throw new RuntimeException("Erro! Ordenação não ocorreu!");
				}
			}
			
			System.out.printf("\t\tTempo execução: %.9f s\n", (somaTempoSegundos / 5.0));
			System.out.println("\t\tNúmero de trocas: " + (numeroTrocas / 5));
			System.out.println("\t\tNúmero de iterações: " + (numeroIteracoes / 5));	
			zerarVariaveisTeste();
		}

		// ----------------------------------------------------------------------------------------------
			
	}
	
	private static void zerarVariaveisTeste() {
		numeroTrocas = 0;
		numeroIteracoes = 0;
		somaTempoSegundos = 0;
	}
	
	private static double obterDiferencaTempoSegundos(long inicioNanossegundos, long fimNanossegundos) {
		return (fimNanossegundos - inicioNanossegundos) / 1_000_000_000.00d;
	}
	
	public static int[] gerarVetorAleatorioMesmaSemente(int tamanho) {
		long semente = 1L;
		int valorMaximo = 1000;
		Random random = new Random(semente);
		int[] vetor = new int[tamanho];
		
		for (int i = 0; i < tamanho; i++) {
			vetor[i] = random.nextInt(valorMaximo + 1);
		}
		
		return vetor;
	}
	
	// Apenas para conferir se a ordenação funcionou
	public static boolean conferirSeEstaOrdenado(int[] vetor, int tamanho) {
		int numAnterior = vetor[0];
		boolean isOrdenado = true;
		for (int i = 1; i < tamanho; i++) {
			if (numAnterior > vetor[i]) {
				isOrdenado = false;
				break;
			}
			numAnterior = vetor[i];
		}
		return isOrdenado;
	}
	
	// iEsq começa na esquerda, buscando valores menores que o pivo
	// iDir começa na direita, buscando valores maiores que o pivo
	public static void ordenarQuickSort(int[] vetor, int iPivo, int iEsq, int iDir, 
			int iLimInfVetor, int iLimSupVetor) {
		
		numeroIteracoes++;
		
		// Significa que é um vetor com 1 elemento. Ou, que foram informados
		// limites sem sentido.
		if (iLimInfVetor >= iLimSupVetor) {
			return;
		}
		
		while (true) {
			// Procura na esquerda um número maior que o pivô (ou seja, números iguais
			// ao pivô ficam na esquerda)
			while ((vetor[iEsq] <= vetor[iPivo]) && (iEsq <= iLimSupVetor - 1)) {
				iEsq++;
			}
			
			// Procura na direita um número menor ou igual ao pivô
			while (vetor[iDir] > vetor[iPivo] && (iDir >= iLimInfVetor + 1)) {
				iDir--;
			}
			
			boolean isIndicesEsqDirMesmaPosicaoOuCruzados = iEsq >= iDir;
			
			if (isIndicesEsqDirMesmaPosicaoOuCruzados) {
				// 	2	1
				//	p	ed		Troca pivo com direita. O pivô foi movido pra sua posição certa.
				//	
				//	5 	7	->	5	7
				//	p	ed		pd	e		Se p == d, não troca. Já está no lugar certo o pivô.
				if (iPivo != iDir) {
					int temp = vetor[iPivo];
					vetor[iPivo] = vetor[iDir];
					vetor[iDir] = temp;
					iPivo = iDir; // Necessário na recursão abaixo. O pivo mudou de posição.
					numeroTrocas++;
				}
				
				// Argumentos:   vetor	iPivo	      iEsq	            iDir	      iLimInfVetor	iLimSupVetor
				ordenarQuickSort(vetor, iLimInfVetor, iLimInfVetor + 1, iPivo - 1,    iLimInfVetor, iPivo - 1);
				ordenarQuickSort(vetor, iPivo + 1,    iPivo + 2,        iLimSupVetor, iPivo + 1,    iLimSupVetor);
					
				break; // Finaliza o loop externo (ele só serve pra mover o iEsq e iDir enquanto não estão cruzados).
			}
			
			if (!isIndicesEsqDirMesmaPosicaoOuCruzados) {
				int temp = vetor[iEsq];
				vetor[iEsq] = vetor[iDir];
				vetor[iDir] = temp;
				numeroTrocas++;
			} 
			
			numeroIteracoes++;
		}
	}
	
	
	public static void ordenarMergeSort(int[] vetor, int indiceInicio, int indiceFim) {	
		int tamanhoVetor = indiceFim - indiceInicio + 1;
		
		numeroIteracoes++;
		
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
				numeroTrocas++;
			}
		}
	}
	
	// Já foram ordenados pedacinhos esquerdo e direito do vetor. Aqui vamos unir eles de maneira ordenada no mesmo vetor.
	public static void ordenarPartesOrdenadasDumVetorNoMesmoVetor(int[] vetor, int indiceInicioMetadeEsquerdaOrdenada,
			int indiceFimMetadeEsquerdaOrdenada, int indiceInicioMetadeDireitaOrdenada, int indiceFimMetadeDireitaOrdenada) {

		int tamanhoVetorDestinoQueSeraOrdenado = indiceFimMetadeDireitaOrdenada - indiceInicioMetadeEsquerdaOrdenada + 1;
		
		// Este 'vetorAuxOrdenado' existe porque:
		// Se 'vetor' = ... 4, 5, 1, 2, ...
		// Sendo a parte esquerda 4, 5 e a parte direita 1, 2, então neste exemplo na ordenação
		// a parte direita seria salva por cima da parte esquerda, perdendo os dados da parte esquerda. Por isso preciso do
		// 'vetorAuxOrdenado' pra guardar o resultado ordenado para no final eu copiar os dados dele no 'vetor'.
		int[] vetorAuxOrdenado = new int[tamanhoVetorDestinoQueSeraOrdenado];
		
		for (int i = 0, e = indiceInicioMetadeEsquerdaOrdenada, 
				d = indiceInicioMetadeDireitaOrdenada; i < tamanhoVetorDestinoQueSeraOrdenado; i++) {
			
			numeroIteracoes++;
			
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
			numeroIteracoes++;
			numeroTrocas++;
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
					numeroTrocas++;
				}
				numeroIteracoes++;
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
