package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.FilmeDAO;
import br.com.treinamento.locadora.model.Filme;
import br.com.treinamento.locadora.model.FilmeGenero;
import br.com.treinamento.locadora.model.Genero;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.util.GeneroENUM;
import br.com.treinamento.locadora.view.FilmeView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsavel por encapsular os metodos de controle do vendedor
 *
 * @author joaop
 * @since 07/07/2020
 */
public class FilmeController {

    private FilmeView viewFilme;
    private Filme filme;
    private Genero genero;
    private ArrayList<Filme> listaFilme;
    private boolean alterar = false;

    public FilmeController() {
    }

    public FilmeController(FilmeView viewFilme) {
        this.viewFilme = viewFilme;
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTbFilmes().getModel();
        modelo.setRowCount(0);
        for (Filme filme : listaFilme) {

            ArrayList<FilmeGenero> listaFilmeGenero = new FilmeGeneroControlller().buscarFilme(filme.getIdFilme());
            String genero = "";
            for (FilmeGenero objeto : listaFilmeGenero) {
                if (objeto.getAtivo().equals("SIM")) {
                    genero += objeto.getGeneroIdGenero().getNome() + " ";
                }
            }

            // Adicioando a linha com os dados
            modelo.addRow(new String[]{filme.getNome(), genero, filme.getValor() + "", filme.getDisponivel(), filme.getPromocao(), filme.getValorPromocao() + ""});
        }
    }

    public void listarFilme() {
        try {
            listaFilme = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            JOptionPaneUtil.erro(Mensagem.filmeErroListar);
        }
    }

    public ArrayList<Filme> buscarTodos() {
        return new FilmeDAO().buscarTodos();
    }

    public void bloquearCampos() {

        this.viewFilme.getBtNovo().setEnabled(true);
        this.viewFilme.getBtAlterar().setEnabled(true);
        this.viewFilme.getBtExcluir().setEnabled(true);
        this.viewFilme.getBtSair().setEnabled(true);
        this.viewFilme.getBtSalvar().setEnabled(false);
        this.viewFilme.getBtCancelar().setEnabled(false);
        this.viewFilme.getTfNome().setEditable(false);
        this.viewFilme.getTfValor().setEditable(false);
        this.viewFilme.getTfValorPromocao().setEditable(false);
        this.viewFilme.getCbDisponivel().setEnabled(false);
        this.viewFilme.getCbPromocao().setEnabled(false);
        this.viewFilme.getCbAcao().setEnabled(false);
        this.viewFilme.getCbFiccao().setEnabled(false);
        this.viewFilme.getCbComedia().setEnabled(false);
        this.viewFilme.getCbTerror().setEnabled(false);
        this.viewFilme.getCbInfantil().setEnabled(false);
        this.viewFilme.getCbAnimacao().setEnabled(false);
        this.viewFilme.getCbAventura().setEnabled(false);
        this.viewFilme.getCbOutros().setEnabled(false);
        this.viewFilme.getTfPesquisaNome().grabFocus();
        this.viewFilme.getTfPesquisaNome().setEditable(true);

    }

    public void desbloquiaAlterar() {
        this.viewFilme.getBtNovo().setEnabled(false);
        this.viewFilme.getBtAlterar().setEnabled(false);
        this.viewFilme.getBtExcluir().setEnabled(false);
        this.viewFilme.getBtSair().setEnabled(false);
        this.viewFilme.getBtSalvar().setEnabled(true);
        this.viewFilme.getBtCancelar().setEnabled(true);
        this.viewFilme.getTfPesquisaNome().setEditable(false);
        this.viewFilme.getTfNome().setEditable(false);
        this.viewFilme.getTfValor().setEditable(true);
        this.viewFilme.getTfValorPromocao().setEditable(true);
        this.viewFilme.getCbDisponivel().setEnabled(true);
        this.viewFilme.getCbPromocao().setEnabled(true);
        this.viewFilme.getTfValor().grabFocus();

    }

    public void desbloquearCampos() {
        this.viewFilme.getBtNovo().setEnabled(false);
        this.viewFilme.getBtAlterar().setEnabled(false);
        this.viewFilme.getBtExcluir().setEnabled(false);
        this.viewFilme.getBtSair().setEnabled(false);
        this.viewFilme.getBtSalvar().setEnabled(true);
        this.viewFilme.getBtCancelar().setEnabled(true);
        this.viewFilme.getTfNome().setEditable(true);
        this.viewFilme.getTfValor().setEditable(true);
        this.viewFilme.getTfValorPromocao().setEditable(false);
        this.viewFilme.getCbDisponivel().setEnabled(true);
        this.viewFilme.getCbPromocao().setEnabled(true);
        this.viewFilme.getCbAcao().setEnabled(true);
        this.viewFilme.getCbFiccao().setEnabled(true);
        this.viewFilme.getCbTerror().setEnabled(true);
        this.viewFilme.getCbComedia().setEnabled(true);
        this.viewFilme.getCbInfantil().setEnabled(true);
        this.viewFilme.getCbAnimacao().setEnabled(true);
        this.viewFilme.getCbAventura().setEnabled(true);
        this.viewFilme.getCbOutros().setEnabled(true);
    }

    public void bloqueioInicial() {
        bloquearCampos();

    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewFilme.getBtNovo().setEnabled(false);
        this.viewFilme.getBtAlterar().setEnabled(false);
        this.viewFilme.getBtExcluir().setEnabled(false);
        this.viewFilme.getBtSair().setEnabled(false);
        this.viewFilme.getTfPesquisaNome().setEditable(false);
        this.viewFilme.getTfNome().grabFocus();

    }

    public void acaoBotaoAlterar() {

        if (this.viewFilme.getTbFilmes().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneFilme, Mensagem.selecione, 0);
        } else {
            desbloquiaAlterar();
            filme = listaFilme.get(this.viewFilme.getTbFilmes().getSelectedRow());
            alterar = true;
            carregarTela();
        }
    }

    public void carregarTela() {
        this.viewFilme.getTfNome().setText(filme.getNome());
        this.viewFilme.getTfValor().setText(filme.getValor() + "");
        if (filme.getDisponivel().equals("SIM")) {
            this.viewFilme.getCbDisponivel().setSelectedIndex(1);
        } else {
            this.viewFilme.getCbDisponivel().setSelectedIndex(2);
        }
        if (filme.getPromocao().equals("SIM")) {
            this.viewFilme.getCbPromocao().setSelectedIndex(1);
        } else {
            this.viewFilme.getCbPromocao().setSelectedIndex(2);
        }
        this.viewFilme.getTfValorPromocao().setText(filme.getValorPromocao() + "");
        carregarGenero();
    }

    public void carregarGenero() {
        for (FilmeGenero lista : new FilmeGeneroControlller().buscarFilme(filme.getIdFilme())) {
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.ACAO.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbAcao().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.FICCAO.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbFiccao().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.TERROR.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbTerror().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.COMEDIA.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbComedia().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.INFANTIL.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbInfantil().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.ANIMACAO.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbAnimacao().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.AVENTURA.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbAventura().setSelected(true);
                }
            }
            if (lista.getGeneroIdGenero().getIdGenero() == GeneroENUM.OUTROS.getCodigo()) {
                if (lista.getAtivo().equals("SIM")) {
                    this.viewFilme.getCbOutros().setSelected(true);
                }
            }
        }
    }

    public boolean validarIncluir() {
        int cont = 0;

        if (Valida.verificaStringVazio(this.viewFilme.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNomeFilme);
            return false;
        } else if (Valida.apenasLetras(this.viewFilme.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.nomeInvalido);
            return false;
        }

        if (Valida.verificaDoubleZero(this.viewFilme.getTfValor().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorFilme);
            return false;
        }

        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
            if (Valida.verificaDoubleZero(this.viewFilme.getTfValorPromocao().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeValorPromocaoFilme);
                return false;
            }
        }
        if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeDisponibilidadeFilme);
            return false;
        }
        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informePromocaoFilme);
            return false;
        }
        if (this.viewFilme.getCbAcao().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbFiccao().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbTerror().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbComedia().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbInfantil().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbAnimacao().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbAventura().isSelected()) {
            cont = 1;

        }
        if (this.viewFilme.getCbOutros().isSelected()) {
            cont = 1;

        }
        if (cont == 0) {
            JOptionPaneUtil.erro(Mensagem.informeGeneroFilme);
            return false;
        }

        return true;
    }

    public boolean validarAlterar() {
        if (Valida.verificaDoubleZero(this.viewFilme.getTfValor().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorFilme);
            return false;
        }
        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
            if (Valida.verificaDoubleZero(this.viewFilme.getTfValorPromocao().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeValorPromocaoFilme);
                return false;
            }
        }
        if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeDisponibilidadeFilme);
            return false;
        }
        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informePromocaoFilme);
            return false;
        }

        return true;

    }

    public void acaoBotaoExcluir() {
        if (this.viewFilme.getTbFilmes().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneFilme, Mensagem.selecione, 0);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirFilme);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    new FilmeGeneroControlller().excluir(listaFilme.get(this.viewFilme.getTbFilmes().getSelectedRow()).getIdFilme());
                    new FilmeDAO().excluir(listaFilme.get(this.viewFilme.getTbFilmes().getSelectedRow()));
                    JOptionPane.showMessageDialog(null, Mensagem.filmeExcluido, Mensagem.sucesso, 0);
                    listarFilme();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Mensagem.filmeErroExcluir, Mensagem.erro, 0);
                    e.printStackTrace();
                }

            }
        }
    }

    public void acaoBotaoSair() {
        this.viewFilme.setVisible(false);

    }

    public void acaoBotaoSalvar() {

        if (alterar) {
            if (validarAlterar()) {
                filme.setValor(Double.parseDouble(this.viewFilme.getTfValor().getText()));
                if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 1) {
                    filme.setDisponivel("SIM");
                } else {
                    filme.setDisponivel("NÃO");
                }
                if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
                    filme.setPromocao("SIM");
                    filme.setValorPromocao(Double.parseDouble(this.viewFilme.getTfValorPromocao().getText()));
                } else {
                    filme.setPromocao("NÃO");
                    filme.setValorPromocao(0.0);
                }
                try {
                    new FilmeDAO().salvar(filme);
                    tratarGenero();
                    limparCampos();
                    bloqueioInicial();
                    listarFilme();
                    JOptionPaneUtil.sucesso(Mensagem.filmeAlterado);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.filmeErroAlterar);
                }
            }
        } else {
            if (validarIncluir()) {
                //procedimentos de inclusao
                filme = new Filme();
                filme.setNome(this.viewFilme.getTfNome().getText());
                filme.setValor(Double.parseDouble(this.viewFilme.getTfValor().getText()));
                if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 1) {
                    filme.setDisponivel("SIM");
                } else {
                    filme.setDisponivel("NÃO");
                }
                if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
                    filme.setPromocao("SIM");
                    filme.setValorPromocao(Double.parseDouble(this.viewFilme.getTfValorPromocao().getText()));
                } else {
                    filme.setPromocao("NÃO");
                    filme.setValorPromocao(0.0);
                }

                try {
                    new FilmeDAO().salvar(filme);
                    tratarGenero();
                    limparCampos();
                    bloqueioInicial();
                    listarFilme();
                    JOptionPaneUtil.sucesso(Mensagem.filmeSalvo);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.filmeErroSalvar);
                }
            }
        }
    }

    public void tratarGenero() {
        String ativo = "";

        if (this.viewFilme.getCbAcao().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.ACAO.getCodigo(), GeneroENUM.ACAO.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbFiccao().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.FICCAO.getCodigo(), GeneroENUM.FICCAO.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbTerror().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.TERROR.getCodigo(), GeneroENUM.TERROR.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbComedia().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.COMEDIA.getCodigo(), GeneroENUM.COMEDIA.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbInfantil().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.INFANTIL.getCodigo(), GeneroENUM.INFANTIL.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbAnimacao().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.ANIMACAO.getCodigo(), GeneroENUM.ANIMACAO.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbAventura().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.AVENTURA.getCodigo(), GeneroENUM.AVENTURA.getDescricao());
        gravarGenero(genero, ativo);

        if (this.viewFilme.getCbOutros().isSelected()) {
            ativo = "SIM";
        } else {
            ativo = "NÃO";
        }
        genero = new Genero(GeneroENUM.OUTROS.getCodigo(), GeneroENUM.OUTROS.getDescricao());
        gravarGenero(genero, ativo);
    }

    public void gravarGenero(Genero genero, String ativo) {
        try {
            new FilmeGeneroControlller().salvar(new FilmeGenero(ativo, filme, genero));
        } catch (Exception e) {
            JOptionPaneUtil.erro(Mensagem.filmeGeneroErroSalvar);
        }
    }

    public void acaoBotaoCancelar() {
        bloquearCampos();
        this.viewFilme.getBtNovo().setEnabled(true);
        this.viewFilme.getBtAlterar().setEnabled(true);
        this.viewFilme.getBtExcluir().setEnabled(true);
        this.viewFilme.getBtSair().setEnabled(true);
        this.viewFilme.getTfPesquisaNome().setEditable(true);
        this.viewFilme.getTfPesquisaNome().grabFocus();
        limparCampos();
    }

    public void limparCampos() {
        this.viewFilme.getTfNome().setText(null);
        this.viewFilme.getTfValor().setText(null);
        this.viewFilme.getTfValorPromocao().setText(null);
        this.viewFilme.getCbDisponivel().setSelectedIndex(0);
        this.viewFilme.getCbPromocao().setSelectedIndex(0);
        this.viewFilme.getCbAcao().setSelected(false);
        this.viewFilme.getCbFiccao().setSelected(false);
        this.viewFilme.getCbTerror().setSelected(false);
        this.viewFilme.getCbComedia().setSelected(false);
        this.viewFilme.getCbInfantil().setSelected(false);
        this.viewFilme.getCbAnimacao().setSelected(false);
        this.viewFilme.getCbAventura().setSelected(false);
        this.viewFilme.getCbOutros().setSelected(false);
    }

    public void bloquearValor() {
        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
            this.viewFilme.getTfValorPromocao().setEditable(true);
        } else {
            this.viewFilme.getTfValorPromocao().setEditable(false);
        }

    }

    public void alterar(Filme filme) {
        try {
            new FilmeDAO().salvar(filme);
        } catch (Exception e) {
            JOptionPaneUtil.erro(Mensagem.filmeErroAlterar);
        }

    }

}
