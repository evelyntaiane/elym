package br.com.elym.model;

public class Motorista extends Usuario {
    private String cnh;
    private boolean disponivel;
    private Veiculo veiculo;

    public Motorista(int id, String nome, String email, String telefone,
                     String senha, String cnh, Veiculo veiculo) {
        super(id, nome, email, telefone, senha);
        setCnh(cnh);
        setVeiculo(veiculo);
        this.disponivel = true;
    }

    public boolean aceitarCorrida(Corrida corrida) {
        if (!disponivel || corrida == null) {
            return false;
        }

        try {
            corrida.atribuirMotorista(this);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public void finalizarCorrida() {
        disponivel = true;
    }

    public void alternarDisponibilidade() {
        disponivel = !disponivel;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        if (cnh == null || cnh.trim().isEmpty()) {
            throw new IllegalArgumentException("A CNH não pode ficar vazia.");
        }
        this.cnh = cnh;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("O motorista precisa ter um veículo.");
        }
        this.veiculo = veiculo;
    }
}
