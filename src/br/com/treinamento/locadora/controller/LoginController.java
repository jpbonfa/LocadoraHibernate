package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.model.Usuario;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.view.LoginView;
import br.com.treinamento.locadora.view.MenuView;

/**
 *
 * @author joaop
 *
 */
public class LoginController {

    private LoginView viewLogin;
    private Usuario usuario;

    public LoginController(LoginView viewLogin) {
        this.viewLogin = viewLogin;
    }

    public void acaoBotaoConfirmar() {
        if (validar()) {
            if (new UsuarioController().verificaLogin(this.viewLogin.getTfLogin().getText(), this.viewLogin.getTfSenha().getText())) {
                new MenuView();
                this.viewLogin.setVisible(false);
            } else {
                JOptionPaneUtil.erro(Mensagem.erroLogin);
            }
        }

    }

    public void acaoBotaoCancelar() {
        System.exit(0);
    }

    public boolean validar() {
        if (Valida.verificaStringVazio(this.viewLogin.getTfLogin().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewLogin.getTfSenha().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeSenha);
            return false;
        }
        return true;
    }

}
