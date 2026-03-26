# EcoColeta

Um sistema distribuído desenvolvido em Java para a gestão e acesso a informações sobre pontos de coleta seletiva.

## Sobre o Projeto

O EcoColeta foi criado para facilitar a conexão entre cidadãos que desejam descartar materiais recicláveis corretamente e os pontos de coleta disponíveis. O sistema utiliza uma arquitetura cliente-servidor para gerenciar essas informações de forma eficiente e centralizada.

O projeto foi dividido em três módulos principais para separar as responsabilidades:

1.  **EcoColetaServidor (`br.com.ecocoleta.servidor`):** O coração do sistema. Gerencia o banco de dados (ou a lista em memória) dos pontos de coleta e processa as requisições enviadas pelos clientes.
2.  **EcoColetaClienteAdmin (`br.com.ecocoleta.admin`):** A interface para os administradores do sistema. Permite o cadastro, edição e remoção de pontos de coleta (CRUD).
3.  **EcoColetaClienteCidadao (`br.com.ecocoleta.cliente`):** A interface para o usuário final. Permite que os cidadãos consultem os pontos de coleta disponíveis com base em [inserir critérios de busca, ex: tipo de material, localização].

Além disso, o projeto utiliza um pacote comum (`br.com.ecocoleta.model`) compartilhado entre os módulos para garantir a padronização dos dados trocados pela rede.

## Tecnologias Utilizadas

* **Linguagem:** Java (versão 17)
* **Comunicação:** Sockets TCP/IP, RMI
* **Armazenamento:** Terminal
* **Interface:** Terminal/Console

## Como Executar

Para rodar este projeto localmente, siga os passos abaixo:

### Pré-requisitos
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) instalado.
* Uma IDE (como Eclipse, IntelliJ ou VS Code) ou familiaridade com o terminal.

### Passos

1.  Clone este repositório:
    ```bash
    git clone [https://github.com/ArkanjoV2/EcoColeta.git](https://github.com/ArkanjoV2/EcoColeta.git)
    ```
2.  Importe o projeto na sua IDE favorita.
3.  **Inicie o Servidor:**
    * Navegue até o módulo `EcoColetaServidor`.
    * Execute a classe principal `Servidor.java`. O servidor ficará aguardando conexões.
4.  **Inicie os Clientes:**
    * Com o servidor rodando, abra o módulo `EcoColetaClienteAdmin` ou `EcoColetaClienteCidadao`.
    * Execute a classe principal do cliente desejado (`ClienteAdmin.java` ou `ClienteCidadao.java`).

## Autor

**Miguel Arcanjo**
* [LinkedIn](www.linkedin.com/in/miguel-arcanjo-a26597309)
* [E-mail](marcanjo17@gmail.com)