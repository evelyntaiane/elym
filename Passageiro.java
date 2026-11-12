package br.com.elym.model;

import java.util.ArrayList;
import java.util.List;

public class Passageiro extends Usuario {
    private List<Corrida> historicoCorridas;

    public Passageiro(int id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone, senha);
        this.historicoCorridas = new ArrayList<>();
    }

    public Corrida solicitarCorrida(int idCorrida, String origem, String destino, double distanciaKm) {
        return new Corrida(idCorrida, this, origem, destino, distanciaKm);
    }

    public void adicionarCorrida(Corrida corrida) {
        this.historicoCorridas.add(corrida);
    }

    public List<Corrida> getHistoricoCorridas() {
        return historicoCorridas;
    }
}
