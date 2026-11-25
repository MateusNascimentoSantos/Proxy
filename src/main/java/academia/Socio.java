package academia;

import java.util.Arrays;
import java.util.List;

public class Socio implements ISocio {

    private Integer matricula;
    private String nome;
    private String plano;
    private String validade;
    private List<Float> mensalidades;

    public Socio(Integer matricula) {
        this.matricula = matricula;
        Socio objeto = BD.getSocio(matricula);
        this.nome = objeto.nome;
        this.plano = objeto.plano;
        this.validade = objeto.validade;
        this.mensalidades = objeto.mensalidades;
    }

    public Socio(Integer matricula, String nome, String plano, String validade, List<Float> mensalidades) {
        this.matricula = matricula;
        this.nome = nome;
        this.plano = plano;
        this.validade = validade;
        this.mensalidades = mensalidades;
    }

    public Integer getMatricula() {
        return matricula;
    }

    @Override
    public List<String> obterDadosSocio() {
        return Arrays.asList(this.nome, this.plano, this.validade);
    }

    @Override
    public List<Float> obterMensalidadesEmAberto(Funcionario funcionario) {
        return this.mensalidades;
    }
}
