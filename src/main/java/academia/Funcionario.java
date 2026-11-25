package academia;

public class Funcionario {

    private String nome;
    private boolean tesouraria;

    public Funcionario(String nome, boolean tesouraria) {
        this.nome = nome;
        this.tesouraria = tesouraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTesouraria() {
        return tesouraria;
    }

    public void setTesouraria(boolean tesouraria) {
        this.tesouraria = tesouraria;
    }
}
