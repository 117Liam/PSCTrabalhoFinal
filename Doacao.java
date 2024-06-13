import java.time.LocalDate;

public class Doacao {
    private String tipo;
    private double quantidade;
    private LocalDate data;
    private String unidade;

    public Doacao(String tipo, double quantidade, LocalDate data, String unidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.unidade = unidade;
    }

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
