package br.com.elym.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Passageiro extends Usuario {
    private final List<Corrida> historicoCorridas = new ArrayList<>();

    public Passageiro(int id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone, senha);
    }

    public Corrida solicitarCorrida(int idCorrida, String origem, String destino, double distanciaKm) {
        return new Corrida(idCorrida, this, origem, destino, distanciaKm);
    }

    public void adicionarCorrida(Corrida corrida) { historicoCorridas.add(corrida); }
    public List<Corrida> getHistoricoCorridas() { return Collections.unmodifiableList(historicoCorridas); }
}
