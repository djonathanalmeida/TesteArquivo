package aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Produto;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produto> list = new ArrayList<>();
		
		System.out.println("Informe o caminho do arquivo: ");
		String caminhoArquivoStr = sc.nextLine();
		
		File caminhoArquivo = new File(caminhoArquivoStr);
		String caminhoPastaStr = caminhoArquivo.getParent();
		
		boolean sucesso = new File(caminhoPastaStr + "\\saida").mkdir();
		
		String destinoArquivoStr = caminhoPastaStr + "\\saida\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoStr))){
			
			String itemCsv = br.readLine();
			while(itemCsv != null) {
				
				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);
				
				list.add(new Produto(nome, preco, quantidade));
				
				itemCsv = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(destinoArquivoStr))){
				
				for(Produto item : list) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
				System.out.println(destinoArquivoStr + " CRIADO");
				
			}catch(IOException e) {
				System.out.println("Erro ao gravar arquivo: " + e.getMessage());
			}
			
		} catch (IOException e){
			System.out.println("Erro ao gravar o arquivo" + e.getMessage());
		}
		
		sc.close();
		
	}

}
