import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArquivoDoacoes {

    public static void escreverDoacoes(String caminhoArquivo, List<Doacao> doacoes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Doacao doacao : doacoes) {
                writer.write(doacao.getTipo() + "," + doacao.getQuantidade() + "," + doacao.getData() + "," + doacao.getUnidade());
                writer.newLine();
            }
        }
    }

    public static List<Doacao> lerDoacoes(String caminhoArquivo) throws IOException {
        List<Doacao> doacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String tipo = partes[0];
                double quantidade = Double.parseDouble(partes[1]);
                LocalDate data = LocalDate.parse(partes[2]);
                String unidade = partes[3];
                doacoes.add(new Doacao(tipo, quantidade, data, unidade));
            }
        }
        return doacoes;
    }
}
