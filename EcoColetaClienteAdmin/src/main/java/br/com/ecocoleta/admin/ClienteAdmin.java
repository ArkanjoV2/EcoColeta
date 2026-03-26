package br.com.ecocoleta.admin;

import br.com.ecocoleta.model.PontoDeColeta;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClienteAdmin {

    public static void main(String[] args) {
        // O endereço e a porta do servidor
        String host = "localhost"; // "localhost" significa "esta mesma máquina"
        int porta = 12345;


        try (
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(host, porta);
                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {


            System.out.println("✅ Conectado ao servidor EcoColeta!");

            // Envia a operação desejada
            saida.writeObject("CADASTRAR");

            // Coleta os dados do novo ponto de coleta
            System.out.print("Digite o nome do ponto de coleta: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Digite os tipos de resíduos aceitos (separados por vírgula): ");
            String tiposStr = scanner.nextLine();
            // Transforma a string "Papel, Plástico" em uma lista ["Papel", "Plástico"]
            List<String> tiposDeResiduos = Arrays.asList(tiposStr.split(",\\s*"));

            // Cria o objeto PontoDeColeta (sem ID, o servidor vai gerar)
            PontoDeColeta novoPonto = new PontoDeColeta(0, nome, endereco, tiposDeResiduos);

            // Envia o objeto para o servidor
            saida.writeObject(novoPonto);
            System.out.println("Enviando dados do novo ponto...");

            // Recebe a resposta do servidor
            String resposta = (String) entrada.readObject();
            System.out.println("Resposta do Servidor: " + resposta);


        } catch (Exception e) {
            System.err.println("❌ Erro no cliente admin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}