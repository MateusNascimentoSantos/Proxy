package academia;

import java.util.HashMap;
import java.util.Map;

public class BD {

    private static Map<Integer, Socio> socios = new HashMap<>();
    private static int consultas = 0;

    public static Socio getSocio(Integer matricula) {
        consultas++;
        return socios.get(matricula);
    }

    public static void addSocio(Socio socio) {
        socios.put(socio.getMatricula(), socio);
    }

    public static int getTotalConsultas() {
        return consultas;
    }

    public static void limpar() {
        socios.clear();
        consultas = 0;
    }
}
