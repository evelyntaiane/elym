package br.com.elym.model;

public abstract class Usuario {
    private final int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public Usuario(int id, String nome, String email, String telefone, String senha) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        this.id = id;
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setSenha(senha);
    }

    public boolean login(String email, String senha) {
        return email != null && senha != null
                && this.email.equalsIgnoreCase(email)
                && this.senha.equals(senha);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ficar vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("O telefone não pode ficar vazio.");
        }
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ficar vazia.");
        }
        this.senha = senha;
    }
}
