package br.com.elym.model;

public class Moto extends Veiculo {
    private int cilindradas;

    public Moto(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
