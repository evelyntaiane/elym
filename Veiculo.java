package br.com.elym.model;

public abstract class Veiculo {
    private String placa;
    private String modelo;
    private String cor;

    public Veiculo(String placa, String modelo, String cor) {
        setPlaca(placa);
        setModelo(modelo);
        setCor(cor);
    }

    public abstract String getTipo();

    public String getDetalhes() {
        return getTipo() + ": " + modelo + " (" + cor + "), placa " + placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa não pode ficar vazia.");
        }
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo não pode ficar vazio.");
        }
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        if (cor == null || cor.trim().isEmpty()) {
            throw new IllegalArgumentException("A cor não pode ficar vazia.");
        }
        this.cor = cor;
    }
}
