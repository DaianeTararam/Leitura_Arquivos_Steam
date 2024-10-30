package view;
import controller.SteamController;

public class Principal {
	public static void main(String[] args) {
		SteamController controller = new SteamController();
		String arquivo = "C://TEMP//SteamCharts.csv";
		controller.exibirJogos(2018, "July", 50000, arquivo);
		controller.gerarArquivo(2020, "June", "C:/TEMP", "nome.csv", arquivo);
	}
}