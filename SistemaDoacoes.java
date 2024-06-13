import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class SistemaDoacoes {
    private GerenciadorDoacoes gerenciador;
    private Scanner scanner;

    public SistemaDoacoes() {
        gerenciador = new GerenciadorDoacoes();
        scanner = new Scanner(System.in);
    }

    public void menuPrincipal() {
        int opcao;
        do {
            System.out.println("1. FAZER DOAÇÃO");
            System.out.println("2. CALCULAR TOTAL DE DOAÇÕES");
            System.out.println("3. CARREGAR DOAÇÕES EM ARQUIVO");
            System.out.println("4. SALVAR DOAÇÕES EM ARQUIVO");
            System.out.println("5. SAIR");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  

            try {
                switch (opcao) {
                    case 1:
                        receberDoacao();
                        break;
                    case 2:
                        exibirTotalDoacoes();
                        break;
                    case 3:
                        carregarDoacoes();
                        break;
                    case 4:
                        salvarDoacoes();
                        break;
                    case 5:
                        System.out.println("Saindo... Obrigado por doar!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (ExcecaoDoacao e) {
                System.err.println(e.getMessage());
            }
        } while (opcao != 5);
    }

    private void receberDoacao() throws ExcecaoDoacao {
        try {
            System.out.print("Qual o tipo de doação você gostaria de realizar? (Dinheiro, Roupas, Alimentos): ");
            String tipo = scanner.nextLine();
            System.out.print("Quantidade: ");
            double quantidade = scanner.nextDouble();
            scanner.nextLine();  
            System.out.print("Por favor, insira a data (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataStr, formatter);

            String unidade;
            switch (tipo.toLowerCase()) {
                case "dinheiro":
                    unidade = "reais";
                    break;
                case "roupas":
                case "alimentos":
                    unidade = "quilos";
                    break;
                default:
                    throw new ExcecaoDoacao("Tipo de doação inválido. Use 'dinheiro', 'roupas' ou 'alimentos'.");
            }

            Doacao doacao = new Doacao(tipo, quantidade, data, unidade);
            gerenciador.adicionarDoacao(doacao);
            System.out.println("Doação registrada com sucesso!");
        } catch (DateTimeParseException e) {
            throw new ExcecaoDoacao("Data inválida. Por favor, use o formato YYYY-MM-DD.");
        } catch (Exception e) {
            throw new ExcecaoDoacao("Erro ao registrar doação: " + e.getMessage());
        }
    }

    private void exibirTotalDoacoes() {
        Map<String, Map<String, Double>> totais = gerenciador.calcularTotalDoacoes();
        System.out.println("Total de doações:");
        for (Map.Entry<String, Map<String, Double>> entry : totais.entrySet()) {
            String tipo = entry.getKey();
            for (Map.Entry<String, Double> unidadeEntry : entry.getValue().entrySet()) {
                String unidade = unidadeEntry.getKey();
                Double total = unidadeEntry.getValue();
                System.out.println(tipo + ": " + total + " " + unidade);
            }
        }
    }

    private void carregarDoacoes() throws ExcecaoDoacao {
        System.out.print("Caminho do arquivo para carregar: ");
        String caminhoArquivo = scanner.nextLine();
        gerenciador.carregarDoacoes(caminhoArquivo);
        System.out.println("Doações carregadas com sucesso!");
    }

    private void salvarDoacoes() throws ExcecaoDoacao {
        System.out.print("Caminho do arquivo para salvar: ");
        String caminhoArquivo = scanner.nextLine();
        gerenciador.salvarDoacoes(caminhoArquivo);
        System.out.println("Doações salvas com sucesso!");
    }
}
