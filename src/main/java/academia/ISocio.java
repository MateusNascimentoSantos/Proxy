package academia;

import java.util.List;

public interface ISocio {
    List<String> obterDadosSocio();
    List<Float> obterMensalidadesEmAberto(Funcionario funcionario);
}
