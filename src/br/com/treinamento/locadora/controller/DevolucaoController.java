package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.model.FilmeGenero;
import br.com.treinamento.locadora.model.ItemLocacao;
import br.com.treinamento.locadora.model.Locacao;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.ServiceUtil;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.view.DevolucaoView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class DevolucaoController {

    private DevolucaoView viewDevolucao;
    private Locacao locacao;
    private ArrayList<Locacao> listaLocacao = new ArrayList<>();
    private ArrayList<ItemLocacao> listaItemLocacao = new ArrayList<>();

    public DevolucaoController(DevolucaoView viewDevolucao) {
        this.viewDevolucao = viewDevolucao;
    }

    public void acaoBotaoOk() {
        if (this.viewDevolucao.getCbLocacao().getSelectedIndex() > 0) {
            locacao = listaLocacao.get(this.viewDevolucao.getCbLocacao().getSelectedIndex() - 1);
            carregarTela();
        } else {
            JOptionPaneUtil.erro(Mensagem.selecioneLocacao);
        }

    }

    public void carregarTela() {
        this.viewDevolucao.getTfVendedor().setText(locacao.getVendedorIdVendedor().getNome());
        this.viewDevolucao.getTfCliente().setText(locacao.getClienteIdCliente().getNome());
        this.viewDevolucao.getTfDataLocacao().setText(locacao.getDataLocacao());
        this.viewDevolucao.getTfDevolucao().setText(locacao.getDataDevolucao());
        if (atrasado()) {
            this.viewDevolucao.getRbSim().setSelected(true);

        } else {
            this.viewDevolucao.getRbNao().setSelected(true);

        }
        listaItemLocacao = new ItemLocacaoController().buscarLocacao(locacao.getIdLocacao());
        carregarTabela();

    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewDevolucao.getTbLocacaoFilme().getModel();
        modelo.setRowCount(0);
        for (ItemLocacao lista : listaItemLocacao) {
            ArrayList<FilmeGenero> listaFilmeGenero = new FilmeGeneroControlller().buscarFilme(lista.getIdItemLocacao());
            String genero = "";
            for (FilmeGenero objeto : listaFilmeGenero) {
                if (objeto.getAtivo().equals("SIM")) {
                    genero += objeto.getGeneroIdGenero().getNome() + " ";
                }
            }
            if (lista.getFilmeIdFilme().getPromocao().equals("SIM")) {
                modelo.addRow(new String[]{lista.getFilmeIdFilme().getIdFilme() + "", lista.getFilmeIdFilme().getNome(), genero, lista.getFilmeIdFilme().getValorPromocao() + ""});
            } else {
                modelo.addRow(new String[]{lista.getFilmeIdFilme().getIdFilme() + "", lista.getFilmeIdFilme().getNome(), genero, lista.getFilmeIdFilme().getValor() + ""});
            }

        }

    }

    public boolean atrasado() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = null;
        Date dataDevolucao = null;
        try {
            dataAtual = format.parse(ServiceUtil.dataAtual());
            dataDevolucao = format.parse(locacao.getDataDevolucao());
        } catch (ParseException ex) {
            Logger.getLogger(DevolucaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dataDevolucao.before(dataAtual)) {
            return true;
        } else {
            return false;
        }

    }

    public void acaoBotaoSalvar() {
        if (validarDados()) {
            locacao.setDevolvido("SIM");
            new LocacaoController().salvar(locacao);
            for (ItemLocacao lista : listaItemLocacao) {
                lista.getFilmeIdFilme().setDisponivel("SIM");
                new FilmeController().alterar(lista.getFilmeIdFilme());

            }
            acaoBotaoCancelar();
            JOptionPaneUtil.sucesso(Mensagem.devolucaoSucesso);

        }

    }

    public boolean validarDados() {
        if (this.viewDevolucao.getCbLocacao().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.selecioneLocacao);
            return false;
        }
        if (this.viewDevolucao.getTfVendedor().getText().equals("")) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }
        if (this.viewDevolucao.getTfCliente().getText().equals("")) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }
        if (Valida.verificaDataVazio(this.viewDevolucao.getTfDataLocacao().getText())) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }
        if (Valida.verificaDataVazio(this.viewDevolucao.getTfDevolucao().getText())) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }
        if (this.viewDevolucao.getButtonGroup1().isSelected(null)) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }
        if (listaItemLocacao.isEmpty()) {
            JOptionPaneUtil.erro(Mensagem.erroDevolucao);
            return false;
        }

        return true;
    }

    public void acaoBotaoCancelar() {
        this.viewDevolucao.getCbLocacao().setSelectedIndex(0);
        this.viewDevolucao.getTfVendedor().setText(null);
        this.viewDevolucao.getTfCliente().setText(null);
        this.viewDevolucao.getTfDataLocacao().setText(null);
        this.viewDevolucao.getTfDevolucao().setText(null);
        this.viewDevolucao.getButtonGroup1().clearSelection();
        DefaultTableModel modelo = (DefaultTableModel) this.viewDevolucao.getTbLocacaoFilme().getModel();
        modelo.setRowCount(0);

    }

    public void acaoBotaoSair() {
        this.viewDevolucao.setVisible(false);
    }

    public void carregarLocacao() {
        this.viewDevolucao.getCbLocacao().removeAllItems();
        this.viewDevolucao.getCbLocacao().addItem("- Selecione -");
        for (Locacao locacao : listaLocacao) {
            this.viewDevolucao.getCbLocacao().addItem("Código: " + locacao.getIdLocacao() + " - " + locacao.getClienteIdCliente().getNome());
        }
    }

    public void bloqueioInicial() {
        this.viewDevolucao.getTfVendedor().setEditable(false);
        this.viewDevolucao.getTfCliente().setEditable(false);
        this.viewDevolucao.getTfDataLocacao().setEditable(false);
        this.viewDevolucao.getTfDevolucao().setEditable(false);
        this.viewDevolucao.getRbSim().setEnabled(false);
        this.viewDevolucao.getRbNao().setEnabled(false);
        this.viewDevolucao.getTbLocacaoFilme().setEnabled(false);

    }

    public void listarLocacao() {

        for (Locacao listaLocacao1 : new LocacaoController().buscarTodos()) {
            
            if (listaLocacao1.getDevolvido().equals("NÃO")) {
                listaLocacao.add(listaLocacao1);
            }

        }
        carregarLocacao();
    }
}
