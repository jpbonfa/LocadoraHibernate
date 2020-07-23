package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.LocacaoDAO;
import br.com.treinamento.locadora.model.Cliente;
import br.com.treinamento.locadora.model.Filme;
import br.com.treinamento.locadora.model.ItemLocacao;
import br.com.treinamento.locadora.model.Locacao;
import br.com.treinamento.locadora.model.Vendedor;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.ServiceUtil;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.view.LocacaoView;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsavel por encapsular os metodos de controle da Locação
 *
 * @author joaop
 * @since 16/07/2020
 */
public class LocacaoController {

    private LocacaoView viewLocacao;
    private Locacao locacao;
    private Filme filme;
    private ArrayList<Cliente> listaCliente = new ArrayList<>();
    private ArrayList<Filme> listaFilme = new ArrayList<>();
    private ArrayList<Vendedor> listaVendedor = new ArrayList<>();
    private ArrayList<Filme> listaFilmeLocacao = new ArrayList<>();
    private double valorTotal = 0.0;
    private double troco = 0.0;

    public LocacaoController() {
    }

    public LocacaoController(LocacaoView viewLocacao) {
        this.viewLocacao = viewLocacao;
    }

    public ArrayList<Locacao> buscarTodos() {
        return new LocacaoDAO().buscarTodos();
    }

    public void carregarComboCliente() {

        listaCliente = new ClienteController().buscarTodos();
        this.viewLocacao.getCbCliente().removeAllItems();
        this.viewLocacao.getCbCliente().addItem("- Selecione -");
        for (Cliente cliente : listaCliente) {
            this.viewLocacao.getCbCliente().addItem(cliente.getNome());
        }
    }

    public void carregarComboVendedor() {
        listaVendedor = new VendedorController().buscarTodos();
        this.viewLocacao.getCbVendedor().removeAllItems();
        this.viewLocacao.getCbVendedor().addItem("- Selecione -");
        for (Vendedor vendedor : listaVendedor) {
            this.viewLocacao.getCbVendedor().addItem(vendedor.getNome());
        }
    }

    public void carregarComboFilme() {
        this.viewLocacao.getCbFilme().removeAllItems();
        this.viewLocacao.getCbFilme().addItem("- Selecione -");
        for (Filme filme : listaFilme) {
            this.viewLocacao.getCbFilme().addItem(filme.getNome());
        }
    }

    public void carregarData() {
        this.viewLocacao.getTfDataLocacao().setText(ServiceUtil.dataAtual());
        this.viewLocacao.getTfDataLocacao().setEditable(false);
    }

    public void listarFilme() {
        try {
            for (Filme filme : new FilmeController().buscarTodos()) {
                if (filme.getDisponivel().equals("SIM")) {
                    listaFilme.add(filme);
                }
            }
            carregarComboFilme();
        } catch (Exception e) {
        }

    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTbLocacao().getModel();
        modelo.setRowCount(0);
        for (Filme filme : listaFilmeLocacao) {
            if (filme.getPromocao().equals("SIM")) {

                modelo.addRow(new String[]{filme.getIdFilme() + "", filme.getNome(), filme.getValorPromocao() + "", filme.getPromocao()});

            } else {
                modelo.addRow(new String[]{filme.getIdFilme() + "", filme.getNome(), filme.getValor() + "", filme.getPromocao()});

            }
        }

    }

    public void acaoBotaoIncluir() {
        if (this.viewLocacao.getCbFilme().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.selecioneFilme);
        } else {
            filme = listaFilme.get(this.viewLocacao.getCbFilme().getSelectedIndex() - 1);
            filme.setDisponivel("NÃO");
            if (filme.getPromocao().equals("SIM")) {
                valorTotal += filme.getValorPromocao();
            } else {
                valorTotal += filme.getValor();
            }
            listaFilme.remove(filme);
            listaFilmeLocacao.add(filme);
            carregarTabela();
            carregarComboFilme();
            atualizarTotal();
        }

    }

    public void acaoBotaoExcluir() {
        if (this.viewLocacao.getTbLocacao().getSelectedRow() < 0) {
            JOptionPaneUtil.erro(Mensagem.selecioneFilme);
        } else {
            filme = listaFilmeLocacao.get(this.viewLocacao.getTbLocacao().getSelectedRow());
            filme.setDisponivel("SIM");
            if (filme.getPromocao().equals("SIM")) {
                valorTotal -= filme.getValorPromocao();
            } else {
                valorTotal -= filme.getValor();
            }
            listaFilmeLocacao.remove(filme);
            listaFilme.add(filme);
            carregarTabela();
            carregarComboFilme();
            atualizarTotal();
        }
    }

    public void acaoBotaoSalvar() {
        if (validarDados()) {
            locacao = new Locacao();
            locacao.setDataLocacao(ServiceUtil.dataAtual());
            locacao.setDataDevolucao(this.viewLocacao.getTfDataDevolucao().getText());
            locacao.setValor(valorTotal);
            if (this.viewLocacao.getRbDinheiro().isSelected()) {
                locacao.setFormaPagamento(this.viewLocacao.getRbDinheiro().getText());
            }
            if (this.viewLocacao.getRbCheque().isSelected()) {
                locacao.setFormaPagamento(this.viewLocacao.getRbCheque().getText());
            }
            if (this.viewLocacao.getRbDebito().isSelected()) {
                locacao.setFormaPagamento(this.viewLocacao.getRbDebito().getText());
            }
            if (this.viewLocacao.getRbCredito().isSelected()) {
                locacao.setFormaPagamento(this.viewLocacao.getRbCredito().getText());
            }
            locacao.setDevolvido("NÃO");
            locacao.setClienteIdCliente(listaCliente.get(this.viewLocacao.getCbCliente().getSelectedIndex() - 1));
            locacao.setVendedorIdVendedor(listaVendedor.get(this.viewLocacao.getCbVendedor().getSelectedIndex() - 1));
            try {
                new LocacaoDAO().salvar(locacao);

            } catch (Exception e) {
                JOptionPaneUtil.erro(Mensagem.locacaoErroSalvar);
            }

            for (Filme filme : listaFilmeLocacao) {
                ItemLocacao item = new ItemLocacao();
                item.setFilmeIdFilme(filme);
                item.setLocacaoIdLocacao(locacao);
                new ItemLocacaoController().salvar(item);
                new FilmeController().alterar(filme);

            }

            JOptionPaneUtil.sucesso(Mensagem.locacaoSalva);
            acaoBotaoCancelar();
            carregarComboFilme();

        }
    }

    public void acaoBotaoCancelar() {

        this.viewLocacao.getCbCliente().setSelectedIndex(0);
        this.viewLocacao.getCbVendedor().setSelectedIndex(0);
        this.viewLocacao.getCbFilme().setSelectedIndex(0);
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTbLocacao().getModel();
        modelo.setRowCount(0);
        this.viewLocacao.getTfDataDevolucao().setText(null);
        this.viewLocacao.getTfValorTotal().setText(null);
        this.viewLocacao.getTfValorPago().setText(null);
        this.viewLocacao.getTfTroco().setText(null);
        this.viewLocacao.getButtonGroup1().clearSelection();

    }

    public void acaoBotaoSair() {
        this.viewLocacao.setVisible(false);

    }

    public boolean validarDados() {
        if (this.viewLocacao.getCbCliente().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.selecioneCliente);
            return false;
        }
        if (this.viewLocacao.getCbVendedor().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.selecioneVendedor);
            return false;
        }
        if (listaFilmeLocacao.isEmpty()) {
            JOptionPaneUtil.erro(Mensagem.selecioneFilme);
            return false;
        }
        if (Valida.verificaDataVazio(this.viewLocacao.getTfDataDevolucao().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeDataDevolucao);
            return false;
        }else if (Valida.validaData(ServiceUtil.quebraData(this.viewLocacao.getTfDataDevolucao().getText()))) {
            JOptionPaneUtil.erro(Mensagem.dataInvalida);
            return false;
            
        }
        if (this.viewLocacao.getButtonGroup1().isSelected(null)) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaPagamento);
            return false;
        }
        if (this.viewLocacao.getTfValorPago().getText().equals("")) {
            JOptionPaneUtil.erro(Mensagem.informeValorPago);
            return false;
        }
        if (Double.parseDouble(this.viewLocacao.getTfValorPago().getText()) < valorTotal) {
            JOptionPaneUtil.erro(Mensagem.valorInsulficiente);
            return false;
        }
        return true;
    }

    public void atualizarTotal() {

        this.viewLocacao.getTfValorTotal().setText("R$ " + valorTotal);
    }

    public void calcularTroco() {
        troco = Double.parseDouble(this.viewLocacao.getTfValorPago().getText()) - valorTotal;
        this.viewLocacao.getTfTroco().setText("R$ " + troco);
    }

    public void salvar(Locacao locacao) {
        try {
            new LocacaoDAO().salvar(locacao);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.locacaoErroSalvar);
        }

    }

}
