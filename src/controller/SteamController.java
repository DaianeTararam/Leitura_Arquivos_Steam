package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Jogo {
	String nome;
	int ano;
	String mes;
	double mediaJogadores;

	public Jogo(String nome, int ano, String mes, double mediaJogadores) {
		this.nome = nome;
		this.ano = ano;
		this.mes = mes;
		this.mediaJogadores = mediaJogadores;
	}
}

public class SteamController {
	public List<Jogo> lerArquivo(String arquivo) throws IOException {
		List<Jogo> jogos = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
			String linha;
			br.readLine(); 
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				Jogo jogo = new Jogo(dados[0], Integer.parseInt(dados[1]), dados[2], Double.parseDouble(dados[3]));
				jogos.add(jogo);
			}
		}
		return jogos;
	}

	public void exibirJogos(int ano, String mes, double mediaEsperada, String arquivo) {
		try {
			List<Jogo> jogos = lerArquivo(arquivo);
			for (Jogo jogo : jogos) {
				if (jogo.ano == ano && jogo.mes.equalsIgnoreCase(mes) && jogo.mediaJogadores >= mediaEsperada) {
					System.out.println(jogo.nome + " | " + jogo.mediaJogadores);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gerarArquivo(int ano, String mes, String diretorio, String nomeArquivo, String arquivo) {
		File dir = new File(diretorio);
		if (!dir.exists()) {
			System.out.println("Diretório inválido.");
			return;
		}
		try (BufferedReader ler = new BufferedReader(new FileReader(arquivo));
				BufferedWriter escrever = new BufferedWriter(new FileWriter(new File(dir, nomeArquivo)))) {
			String linha;
			ler.readLine();
			while ((linha = ler.readLine()) != null) {
				String[] dados = linha.split(",");
				int anoJogo = Integer.parseInt(dados[1]);
				String mesJogo = dados[2];
				double mediaJogadores = Double.parseDouble(dados[3]);
				if (anoJogo == ano && mesJogo.equalsIgnoreCase(mes)) {
					escrever.write(dados[0] + ";" + mediaJogadores);
					escrever.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
