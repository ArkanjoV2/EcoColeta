import br.com.ecocoleta.model.PontoDeColeta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Servidor {

    private static List<PontoDeColeta> pontoDeColeta = new ArrayList<>();
    private static int proximoId = 1;

    public static void main(String[] args) {

        pontoDeColeta.add(new PontoDeColeta(proximoId++, "Mercado Central", "Rua A, 100", List.of("Plástico", "Papel")));
        pontoDeColeta.add(new PontoDeColeta(proximoId++, "Parque da cidade", "Av. B, 200", List.of("Vidro", "Metal", "Organico")));

        int porta = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor EcoColeta iniciado na porta " + porta);
            System.out.println("Aguardando conexoes de clientes...");

            while (true) {

                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Novo cliente conectado:" + clienteSocket.getInetAddress().getHostAddress());

                try (
                        ObjectOutputStream saida = new ObjectOutputStream(clienteSocket.getOutputStream());
                        ObjectInputStream entrada = new ObjectInputStream(clienteSocket.getInputStream())

                ) {
                    String operacao = (String) entrada.readObject();
                    System.out.println("Operaçao recebida: " + operacao);

                    if ("CADASTRAR".equalsIgnoreCase(operacao)) {
                        PontoDeColeta novoPonto = (PontoDeColeta) entrada.readObject();
                        novoPonto.setId(proximoId++);
                        pontoDeColeta.add(novoPonto);
                        System.out.println("Ponto cadastrado" + novoPonto.getNome());
                        saida.writeObject("SUCESSO: Ponto de coleta cadastrado");

                    } else if ("BUSCAR".equalsIgnoreCase(operacao)) {
                        String tipoResiduo = (String) entrada.readObject();
                        System.out.println("Buscando por tipo: " + tipoResiduo);

                        List<PontoDeColeta> resultado = pontoDeColeta.stream()
                                .filter(ponto -> ponto.getTiposDeResiduos().stream()
                                        .anyMatch(tipo -> tipo.equalsIgnoreCase(tipoResiduo)))
                                .collect(Collectors.toList());

                        saida.writeObject(resultado);
                        System.out.println("Enviando " + resultado.size() + " pontos para o cliente");

                    }

                } catch (Exception e) {
                    System.err.println("Erro ao processar cliente: " + e.getMessage());
                } finally {
                    try {
                        clienteSocket.close();
                        System.out.println("Cliente desconectado");
                    } catch (IOException e) {
                        System.err.println(" Erro ao fechar o socket do cliente: " + e.getMessage());

                    }

                }

            }
        } catch (IOException e) {
            System.err.println(" Erro no servidor: " + e.getMessage());
        }


    }
}
