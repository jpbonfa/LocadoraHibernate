package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.view.ClienteView;
import br.com.treinamento.locadora.view.DevolucaoView;
import br.com.treinamento.locadora.view.FilmeView;
import br.com.treinamento.locadora.view.LocacaoView;
import br.com.treinamento.locadora.view.LoginView;
import br.com.treinamento.locadora.view.MenuView;
import br.com.treinamento.locadora.view.VendedorView;

/**
 *
 * @author joaop
 */
public class MenuController {

    private MenuView viewMenu;

    public MenuController(MenuView viewMenu) {
        this.viewMenu = viewMenu;
    }

    public void acaoBotaoCliente() {
        new ClienteView();
    }

    public void acaoBotaoVendedor() {
        new VendedorView();
    }

    public void acaoBotaoFilme() {
        new FilmeView();
    }

    public void acaoBotaoLocacao() {
        new LocacaoView();
    }

    public void acaoBotaoDevolucao() {
        new DevolucaoView();
    }

    public void acaoBotaoSair() {
        System.exit(0);
    }

    public void acaoCadastroCliente() {
        new ClienteView();
    }

    public void acaoCadastroVendedor() {
        new VendedorView();
    }

    public void acaoCadastroFilme() {
        new FilmeView();
    }

    public void acaoCadastroLocacao() {
        new LocacaoView();
    }

    public void acaoCadastroDevolucao() {
        new DevolucaoView();

    }

    public void acaoSistemaLogout() {
        this.viewMenu.setVisible(false);
        new LoginView();

    }

    public void acaoSistemaSair() {
        System.exit(0);
    }
}
