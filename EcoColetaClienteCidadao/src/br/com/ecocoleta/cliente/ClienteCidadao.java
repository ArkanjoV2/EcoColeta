package br.com.ecocoleta.cliente;

import br.com.ecocoleta.model.PontoDeColeta;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteCidadao {

    public static void main(String[] args) {
        String host = "localhost";
        int porta = 12345;

        // Estrutura correta com try-with-resources
        // Declara o Scanner, Socket e os Streams dentro dos parênteses
        try (
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(host, porta);
                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            // Todo o código que usa esses recursos fica AQUI DENTRO
            System.out.println("✅ Conectado ao servidor EcoColeta!");

            // Envia a operação de busca
            saida.writeObject("BUSCAR");

            System.out.print("Qual tipo de resíduo você quer descartar? (Ex: Plástico, Vidro, Metal): ");
            String tipoResiduo = scanner.nextLine();

            // Envia o tipo de resíduo para o servidor
            saida.writeObject(tipoResiduo);
            System.out.println("Buscando pontos que aceitam '" + tipoResiduo + "'...");

            // Recebe a lista de resultados do servidor
            // A anotação @SuppressWarnings desliga o aviso de "Unchecked cast", que é esperado aqui.
            @SuppressWarnings("unchecked")
            List<PontoDeColeta> resultados = (List<PontoDeColeta>) entrada.readObject();

            // Exibe os resultados
            if (resultados.isEmpty()) {
                System.out.println("\nNenhum ponto de coleta encontrado para este tipo de resíduo.");
            } else {
                System.out.println("\n--- Pontos de Coleta Encontrados ---");
                for (PontoDeColeta ponto : resultados) {
                    System.out.println(ponto); // Usando o método toString() da classe PontoDeColeta
                }
                System.out.println("------------------------------------");
            }

            // Bloco catch para tratar qualquer erro de conexão ou leitura
        } catch (Exception e) {
            System.err.println("❌ Erro no cliente cidadão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}