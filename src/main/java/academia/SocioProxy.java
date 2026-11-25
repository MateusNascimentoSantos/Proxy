package academia;

import java.util.List;

public class SocioProxy implements ISocio {

    private Socio socio;

    private Integer matricula;

    public SocioProxy(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public List<String> obterDadosSocio() {
        if (this.socio == null) {
            this.socio = new Socio(this.matricula);
        }
        return this.socio.obterDadosSocio();
    }

    @Override
    public List<Float> obterMensalidadesEmAberto(Funcionario funcionario) {
        if (!funcionario.isTesouraria()) {
            throw new IllegalArgumentException("Funcionario nao autorizado");
        }
        if (this.socio == null) {
            this.socio = new Socio(this.matricula);
        }
        return this.socio.obterMensalidadesEmAberto(funcionario);
    }
}
