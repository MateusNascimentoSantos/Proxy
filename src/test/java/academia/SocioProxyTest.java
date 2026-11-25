package academia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SocioProxyTest {

    @BeforeEach
    void setUp() {
        BD.limpar();
        BD.addSocio(new Socio(1, "Carlos Siqueira", "Plano Mensal", "31/12/2026", Arrays.asList()));
        BD.addSocio(new Socio(2, "Renata Alves", "Plano Trimestral", "15/03/2027", Arrays.asList(150.0f, 150.0f)));
    }

    @Test
    void deveRetornarDadosSocio() {
        SocioProxy socio = new SocioProxy(1);

        assertEquals(Arrays.asList("Carlos Siqueira", "Plano Mensal", "31/12/2026"), socio.obterDadosSocio());
    }

    @Test
    void deveRetornarMensalidadesEmAbertoParaTesouraria() {
        Funcionario funcionario = new Funcionario("Beatriz", true);
        SocioProxy socio = new SocioProxy(2);

        assertEquals(Arrays.asList(150.0f, 150.0f), socio.obterMensalidadesEmAberto(funcionario));
    }

    @Test
    void deveRetornarListaVaziaParaSocioEmDia() {
        Funcionario funcionario = new Funcionario("Beatriz", true);
        SocioProxy socio = new SocioProxy(1);

        assertEquals(Arrays.asList(), socio.obterMensalidadesEmAberto(funcionario));
    }

    @Test
    void deveRetornarExcecaoFuncionarioNaoAutorizado() {
        try {
            Funcionario funcionario = new Funcionario("Tiago", false);
            SocioProxy socio = new SocioProxy(2);

            socio.obterMensalidadesEmAberto(funcionario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Funcionario nao autorizado", e.getMessage());
        }
    }

    @Test
    void naoDeveConsultarBancoAoCriarOProxy() {
        for (int i = 0; i < 500; i++) {
            new SocioProxy(1);
        }

        assertEquals(0, BD.getTotalConsultas());
    }

    @Test
    void deveConsultarBancoUmaVezSoAindaQueChamadoVariasVezes() {
        Funcionario funcionario = new Funcionario("Beatriz", true);
        SocioProxy socio = new SocioProxy(2);

        socio.obterDadosSocio();
        socio.obterDadosSocio();
        socio.obterMensalidadesEmAberto(funcionario);

        assertEquals(1, BD.getTotalConsultas());
    }

    @Test
    void naoDeveConsultarBancoQuandoFuncionarioNaoAutorizado() {
        try {
            Funcionario funcionario = new Funcionario("Tiago", false);
            SocioProxy socio = new SocioProxy(2);

            socio.obterMensalidadesEmAberto(funcionario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals(0, BD.getTotalConsultas());
        }
    }

    @Test
    void deveEntregarOsMesmosDadosQueOSocioReal() {
        ISocio real = new Socio(1);
        ISocio proxy = new SocioProxy(1);

        assertEquals(real.obterDadosSocio(), proxy.obterDadosSocio());
    }
}
