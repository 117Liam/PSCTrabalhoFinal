import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDoacoes {
    private List<Doacao> doacoes;

    public GerenciadorDoacoes() {
        doacoes = new ArrayList<>();
    }

    public void adicionarDoacao(Doacao doacao) {
        doacoes.add(doacao);
    }

    public Map<String, Map<String, Double>> calcularTotalDoacoes() {
        Map<String, Map<String, Double>> totais = new HashMap<>();
        for (Doacao doacao : doacoes) {
            String tipo = doacao.getTipo();
            String unidade = doacao.getUnidade();
            if (!totais.containsKey(tipo)) {
                totais.put(tipo, new HashMap<>());
                totais.get(tipo).put(unidade, 0.0);
            }
            Map<String, Double> unidadeMap = totais.get(tipo);
            unidadeMap.put(unidade, unidadeMap.getOrDefault(unidade, 0.0) + doacao.getQuantidade());
        }
        return totais;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void carregarDoacoes(String caminhoArquivo) throws ExcecaoDoacao {
        try {
            doacoes = ArquivoDoacoes.lerDoacoes(caminhoArquivo);
        } catch (IOException e) {
            throw new ExcecaoDoacao("Erro ao carregar doações: " + e.getMessage());
        }
    }

    public void salvarDoacoes(String caminhoArquivo) throws ExcecaoDoacao {
        try {
            ArquivoDoacoes.escreverDoacoes(caminhoArquivo, doacoes);
        } catch (IOException e) {
            throw new ExcecaoDoacao("Erro ao salvar doações: " + e.getMessage());
        }
    }
}
