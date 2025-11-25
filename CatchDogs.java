import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CatchDogs {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        int opcao = 0;
        do {
            
            imprimir("\n===========================================");
            imprimir("=== CATchDOGs - Sistema de Ajuda a Pets ===");
            imprimir("1. Cadastrar pet para adoção");
            imprimir("2. Registrar pet abandonado/perdido");
            imprimir("3. Listar pets disponíveis para adoção");
            imprimir("4. Buscar pets perdidos por região");
            imprimir("5. Realizar doação");
            imprimir("6. Listar pets abandonados/perdidos");
            imprimir("7. Mostrar Dados");
            imprimir("0. Sair");
            imprimir("===========================================");
            imprimir("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch(opcao) {
                case 1:
                    cadastrarPetAdocao();
                    break;
                case 2:
                    registrarPetAbandonado();
                    break;
                case 3:
                    listarPetsAdocao();
                    break;
                case 4:
                    buscarPorRegiao();
                    break;
                case 5:
                    realizarDoacao();
                    break;
                case 6:
                    listarPetsAbandonados();
                    break;
                case 7:
                    mostrarEstatisticas();
                    break;
                case 0:
                    imprimir("Obrigado por usar o CATchDOGs!");
                    break;
                default:
                    imprimir("Opção inválida!");
            }
            if (opcao == 0){

            } else {
            imprimir("\n\npressione ENTER para prosseguir...");
            }
            System.in.read();
        } while(opcao != 0);
        
        scanner.close();
    }


    public static void cadastrarPetAdocao() {
        imprimir("\n===========================================");
        imprimir("======= Cadastro de Pet para Adoção =======");
        
        String nome = lerTerminal("Nome do pet: ");
        String tipo = lerTerminal("Tipo (Cachorro/Gato/Outro): ");
        String idade = lerTerminal("Idade: ");
        String raca = lerTerminal("Raça: ");
        String regiao = lerTerminal("Região: ");
        String descricao = lerTerminal("Descrição: ");
        
        // Formatar os dados para salvar no arquivo
        String dadosPet = nome + ";" + tipo + ";" + idade + ";" + raca + ";" + regiao + ";" + descricao;
        
        // Salvar no arquivo adocao.txt
        salvarNoArquivo("adocao.txt", dadosPet);
        
        imprimir("\nPet cadastrado com sucesso para adoção!");
    }

    public static void salvarNoArquivo(String nomeArquivo, String dados) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, true);
            PrintWriter gravarArq = new PrintWriter(arquivo);
            gravarArq.println(dados);
            arquivo.close();
        } catch (IOException e) {
            imprimir("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static void listarPetsAdocao() {
        imprimir("\n===========================================");
        imprimir("====== Pets Disponíveis para Adoção =======");
        
        try {
            File arquivo = new File("adocao.txt");
            Scanner leitor = new Scanner(arquivo);
            
            int contador = 1;
            while(leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");
                
                imprimir("\nPet #" + contador++);
                imprimir("Nome: " + dados[0]);
                imprimir("Tipo: " + dados[1]);
                imprimir("Idade: " + dados[2]);
                imprimir("Raça: " + dados[3]);
                imprimir("Região: " + dados[4]);
                imprimir("Descrição: " + dados[5]);
                imprimir("-------------------");
            }
            
            leitor.close();
        } catch (FileNotFoundException e) {
            imprimir("Nenhum pet cadastrado para adoção ainda.");
        }
    }

    public static void buscarPorRegiao() {
        imprimir("\n=== Buscar Pets por Região ===");
        System.out.print("Digite a região para buscar: ");
        String regiaoBusca = scanner.nextLine();
        
        try {
            File arquivo = new File("abandonados.txt");
            Scanner leitor = new Scanner(arquivo);
            
            int encontrados = 0;
            while(leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");
                
                if(dados[4].toUpperCase().contains(regiaoBusca.toUpperCase())) {
                    imprimir("\nPet encontrado:");
                    imprimir("Nome: " + dados[0]);
                    imprimir("Tipo: " + dados[1]);
                    imprimir("Idade: " + dados[2]);
                    imprimir("Raça: " + dados[3]);
                    imprimir("Região: " + dados[4]);
                    imprimir("Descrição: " + dados[5]);
                    imprimir("-------------------");
                    encontrados++;
                }
            }
            
            if(encontrados == 0) {
                imprimir("Nenhum pet encontrado na região " + regiaoBusca);
            } else {
                imprimir("Total de pets encontrados: " + encontrados);
            }
            
            leitor.close();
        } catch (FileNotFoundException e) {
            imprimir("Nenhum pet cadastrado para adoção ainda.");
        }
    }

    public static void registrarPetAbandonado() {
        imprimir("\n===========================================");
        imprimir("==== Registrar Pet Abandonado/Perdido =====");

        String nome = lerTerminal("Nome do pet (se conhecido): ");
        
        String tipo = lerTerminal("Tipo (Cachorro/Gato/Outro): ");
        
        String idade = lerTerminal("Aproximadamente idade: ");
        
        String cor = lerTerminal("Cor predominante: ");
        
        String regiao = lerTerminal("Região onde foi encontrado: ");
        
        String data = lerTerminal("Data do registro (dd/mm/aaaa): ");
        
        String info = lerTerminal("Informações adicionais: ");
        
        String dadosPet = nome + ";" + tipo + ";" + idade + ";" + cor + ";" + regiao + ";" + data + ";" + info;
        salvarNoArquivo("abandonados.txt", dadosPet);
        
        imprimir("\nPet registrado com sucesso!");
    }
   
    public static void realizarDoacao() {
        imprimir("\n===========================================");
        imprimir("=========== Sistema de Doações ============");
        
        String nome = lerTerminal("Seu nome: ");
        
        System.out.print("Tipo de doação (dinheiro/ração/medicamento/outros): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Valor/Quantidade: ");
        String valor = scanner.nextLine();
        
        System.out.print("Região: ");
        String regiao = scanner.nextLine();
        
        System.out.print("Contato: ");
        String contato = scanner.nextLine();
        
        String dadosDoacao = nome + ";" + tipo + ";" + valor + ";" + regiao + ";" + contato + ";" + java.time.LocalDate.now();
        salvarNoArquivo("doacoes.txt", dadosDoacao);
        
        imprimir("\nObrigado pela sua doação!");
    }

    public static void listarPetsAbandonados() {
        imprimir("\n===========================================");
        imprimir("======== Pets Abandonados/Perdidos ========");
        
        try {
            File arquivo = new File("abandonados.txt");
            Scanner leitor = new Scanner(arquivo);
            
            int contador = 1;
            while(leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");
                
                imprimir("\nRegistro #" + contador++);
                imprimir("Nome: " + dados[0]);
                imprimir("Tipo: " + dados[1]);
                imprimir("Idade aproximada: " + dados[2]);
                imprimir("Cor: " + dados[3]);
                imprimir("Região: " + dados[4]);
                imprimir("Data: " + dados[5]);
                imprimir("Informações: " + dados[6]);
                imprimir("-------------------");
            }
            
            leitor.close();
        } catch (FileNotFoundException e) {
            imprimir("Nenhum pet abandonado/perdido registrado ainda.");
        }
    }

    public static void limparArquivo(String nomeArquivo) {
        try {
            new FileWriter(nomeArquivo, false).close();
            imprimir("Arquivo " + nomeArquivo + " limpo com sucesso!");
        } catch (IOException e) {
            imprimir("Erro ao limpar arquivo: " + e.getMessage());
        }
    }

    public static void mostrarEstatisticas() {
        int totalDoacoes = contarDadosArquivo("doacoes.txt");
        int totalAbandonados = contarDadosArquivo("abandonados.txt");
        int totalAdocao = contarDadosArquivo("adocao.txt");

        imprimir("\n===========================================");
        imprimir("========= Estatísticas CATchDOGs ==========");
        imprimir("Pets disponíveis para adoção: " + totalAdocao);
        imprimir("Pets abandonados/perdidos registrados: " + totalAbandonados);
        imprimir("Doações recebidas: " + totalDoacoes);
        imprimir("\n===========================================");
       
    }

    public static int contarDadosArquivo(String nomeArquivo) {
        int numeroTotal = 0;
        try {
            // Contar pets abandonados
            File arquivo = new File(nomeArquivo);
            
            if(arquivo.exists()) {
                Scanner scanner = new Scanner(arquivo);
                numeroTotal = scanner.useDelimiter("\\Z").next().split("\n").length;
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            imprimir(String.format("Erro ao ler arquivo %s", nomeArquivo));
        }
        return numeroTotal;
    }

    public static String lerTerminal(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static void imprimir(String msg) {
        System.out.println(msg);
    }
}
