package br.com.elym.model;

public class Carro extends Veiculo {
    private int quantidadePortas;

    public Carro(String placa, String modelo, String cor, int quantidadePortas) {
        super(placa, modelo, cor);
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }
}
