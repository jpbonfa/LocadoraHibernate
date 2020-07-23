package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.VendedorDAO;
import br.com.treinamento.locadora.model.Cidade;
import br.com.treinamento.locadora.model.Estado;
import br.com.treinamento.locadora.model.Vendedor;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.view.VendedorView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsavel por encapsular os metodos de controle do vendedor
 *
 * @author joaop
 * @since 07/07/2020
 */
public class VendedorController {
    
    private VendedorView viewVendedor;
    private Vendedor vendedor;
    private Cidade cidade;
    private ArrayList<Vendedor> listaVendedor;
    private ArrayList<Cidade> listaCidade;
    private ArrayList<Estado> listaEstado;
    private boolean alterar = false;
    
    public VendedorController() {
    }
    
    public VendedorController(VendedorView viewVendedor) {
        this.viewVendedor = viewVendedor;
    }
    
    public void carrregarCidade() {
        listaCidade = new CidadeController().buscarTodos();
        this.viewVendedor.getCbCidade().removeAllItems();
        this.viewVendedor.getCbCidade().addItem("- Selecione -");
        for (Cidade cidade : listaCidade) {
            this.viewVendedor.getCbCidade().addItem(cidade.getNome());
        }
    }
    
    public void carregarEstado() {
        listaEstado = new EstadoController().buscarTodos();
        this.viewVendedor.getCbUf().removeAllItems();
        this.viewVendedor.getCbUf().addItem("- Selecione -");
        for (Estado estado : listaEstado) {
            this.viewVendedor.getCbUf().addItem(estado.getUf());
        }
    }
    
    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendedor.getTbVendedor().getModel();
        modelo.setRowCount(0);
        for (Vendedor vendedor : listaVendedor) {
            // Adicioando a linha com os dados
            modelo.addRow(new String[]{vendedor.getNome(), vendedor.getCidadeIdCidade().getNome(), vendedor.getAreaVenda()});
        }
    }
    
    public void listarVendedor() {
        try {
            listaVendedor = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }
    
    public ArrayList<Vendedor> buscarTodos() {
        return new VendedorDAO().buscarTodos();
    }
    
    public void bloquearCampos() {
        this.viewVendedor.getBtNovo().setEnabled(true);
        this.viewVendedor.getBtAlterar().setEnabled(true);
        this.viewVendedor.getBtExcluir().setEnabled(true);
        this.viewVendedor.getBtSair().setEnabled(true);
        this.viewVendedor.getBtSalvar().setEnabled(false);
        this.viewVendedor.getBtCancelar().setEnabled(false);
        this.viewVendedor.getTfNome().setEditable(false);
        this.viewVendedor.getCbSexo().setEnabled(false);
        this.viewVendedor.getTfIdade().setEditable(false);
        this.viewVendedor.getTfArea().setEditable(false);
        this.viewVendedor.getCbCidade().setEnabled(false);
        this.viewVendedor.getCbUf().setEnabled(false);
        this.viewVendedor.getTfSalario().setEditable(false);
        this.viewVendedor.getTfPesquisaNome().setEditable(true);
    }
    
    public void desbloquearCampos() {
        this.viewVendedor.getBtNovo().setEnabled(false);
        this.viewVendedor.getBtAlterar().setEnabled(false);
        this.viewVendedor.getBtExcluir().setEnabled(false);
        this.viewVendedor.getBtSair().setEnabled(false);
        this.viewVendedor.getBtSalvar().setEnabled(true);
        this.viewVendedor.getBtCancelar().setEnabled(true);
        this.viewVendedor.getTfNome().setEditable(true);
        this.viewVendedor.getCbSexo().setEnabled(true);
        this.viewVendedor.getTfIdade().setEditable(true);
        this.viewVendedor.getTfArea().setEditable(true);
        this.viewVendedor.getCbCidade().setEnabled(true);
        this.viewVendedor.getTfSalario().setEditable(true);
        
    }
    
    public void desbloquearAlterar() {
        this.viewVendedor.getBtNovo().setEnabled(false);
        this.viewVendedor.getBtAlterar().setEnabled(false);
        this.viewVendedor.getBtExcluir().setEnabled(false);
        this.viewVendedor.getBtSair().setEnabled(false);
        this.viewVendedor.getBtSalvar().setEnabled(true);
        this.viewVendedor.getBtCancelar().setEnabled(true);
        this.viewVendedor.getTfArea().setEditable(true);
        this.viewVendedor.getCbCidade().setEnabled(true);
        this.viewVendedor.getTfSalario().setEditable(true);
        this.viewVendedor.getTfArea().grabFocus();
        this.viewVendedor.getTfPesquisaNome().setEditable(false);
        
    }
    
    public void bloqueioInicial() {
        bloquearCampos();
        this.viewVendedor.getTfPesquisaNome().grabFocus();
    }
    
    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewVendedor.getBtNovo().setEnabled(false);
        this.viewVendedor.getBtAlterar().setEnabled(false);
        this.viewVendedor.getBtExcluir().setEnabled(false);
        this.viewVendedor.getBtSair().setEnabled(false);
        this.viewVendedor.getTfPesquisaNome().setEditable(false);
        this.viewVendedor.getTfNome().grabFocus();
        
    }
    
    public void acaoBotaoAlterar() {
        if (this.viewVendedor.getTbVendedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneVendedor, Mensagem.erro, 0);
        } else {
            alterar = true;
            vendedor = listaVendedor.get(this.viewVendedor.getTbVendedor().getSelectedRow());
            carregarTela();
            desbloquearAlterar();
        }
    }
    
    public void acaoBotaoExcluir() {
        if (this.viewVendedor.getTbVendedor().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneVendedor, Mensagem.selecione, 0);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirVendedor);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    new VendedorDAO().excluir(listaVendedor.get(this.viewVendedor.getTbVendedor().getSelectedRow()));
                    JOptionPane.showMessageDialog(null, Mensagem.vendedorExcluido, Mensagem.sucesso, 1);
                    listarVendedor();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Mensagem.vendedorExcluirErro, Mensagem.erro, 0);
                    e.printStackTrace();
                }
                
            }
        }
        
    }
    
    public void acaoBotaoSair() {
        this.viewVendedor.setVisible(false);
        
    }
    
    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validarAlterar()) {
                vendedor.setAreaVenda(this.viewVendedor.getTfArea().getText());
                vendedor.setCidadeIdCidade(listaCidade.get(this.viewVendedor.getCbCidade().getSelectedIndex()));
                vendedor.setSalario(Double.parseDouble(this.viewVendedor.getTfSalario().getText()));
                try {
                    new VendedorDAO().salvar(vendedor);
                    limparCampos();
                    bloqueioInicial();
                    listarVendedor();
                    JOptionPaneUtil.sucesso(Mensagem.vendedorAlterado);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.vendedorAlterarErro);
                }
            }
        } else {
            if (validarIncluir()) {
                //procedimentos de inclusao
                vendedor = new Vendedor();
                vendedor.setNome(this.viewVendedor.getTfNome().getText());
                if (this.viewVendedor.getCbSexo().getSelectedIndex() == 1) {
                    vendedor.setSexo("M");
                    
                } else {
                    vendedor.setSexo("F");
                }
                vendedor.setIdade(Integer.parseInt(this.viewVendedor.getTfIdade().getText()));
                vendedor.setAreaVenda(this.viewVendedor.getTfArea().getText());
                vendedor.setCidadeIdCidade(listaCidade.get(this.viewVendedor.getCbCidade().getSelectedIndex()));
                vendedor.setSalario(Double.parseDouble(this.viewVendedor.getTfSalario().getText()));
                
                try {
                    new VendedorDAO().salvar(vendedor);
                    limparCampos();
                    bloqueioInicial();
                    listarVendedor();
                    JOptionPaneUtil.sucesso(Mensagem.vendedorSalvo);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.vendedorSalvarErro);
                }
                
            }
        }
        
    }
    
    public boolean validarIncluir() {
        
        if (Valida.verificaStringVazio(this.viewVendedor.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNomeVendedor);
            return false;
        } else if (Valida.apenasLetras(this.viewVendedor.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.nomeInvalido);
            return false;
        }
        if (this.viewVendedor.getCbSexo().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeSexoVendedor);
            return false;
        }
        if (Valida.verificaIntZero(this.viewVendedor.getTfIdade().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeIdadeVendedor);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewVendedor.getTfArea().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeAreaVendedor);
            return false;
        }else if (Valida.apenasLetras(this.viewVendedor.getTfArea().getText())) {
            JOptionPaneUtil.erro(Mensagem.nomeInvalido);
            return false;
        }
        if (this.viewVendedor.getCbCidade().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeCidadeVendedor);
            return false;
        }
        if (this.viewVendedor.getCbUf().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeUfVendedor);
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewVendedor.getTfSalario().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeSalarioVendedor);
            return false;
        }
        return true;
    }
    
    public boolean validarAlterar() {
        if (Valida.verificaStringVazio(this.viewVendedor.getTfArea().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeAreaVendedor);
            return false;
        }
        if (this.viewVendedor.getCbCidade().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeCidadeVendedor);
            return false;
        }
        if (this.viewVendedor.getCbUf().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeUfVendedor);
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewVendedor.getTfSalario().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeSalarioVendedor);
            return false;
        }
        return true;
    }
    
    public void acaoBotaoCancelar() {
        bloquearCampos();
        this.viewVendedor.getTfPesquisaNome().grabFocus();
        limparCampos();
    }
    
    public void pesquisarVendedor() {
        try {
            listaVendedor = new VendedorDAO().buscarNome(this.viewVendedor.getTfPesquisaNome().getText());
            carregarTabela();
        } catch (Exception e) {
        }
        
    }
    
    public void limparCampos() {
        
        this.viewVendedor.getTfNome().setText(null);
        this.viewVendedor.getCbSexo().setSelectedIndex(0);
        this.viewVendedor.getTfIdade().setText(null);
        this.viewVendedor.getTfArea().setText(null);
        this.viewVendedor.getCbCidade().setSelectedIndex(0);
        this.viewVendedor.getCbUf().setSelectedIndex(0);
        this.viewVendedor.getTfSalario().setText(null);
        
    }
    
    public void carregarUf() {
        this.viewVendedor.getCbUf().setSelectedItem(listaCidade.get(this.viewVendedor.getCbCidade().getSelectedIndex()).getEstadoIdEstado().getUf());
    }
    
    public void carregarTela() {
        this.viewVendedor.getTfNome().setText(vendedor.getNome());
        if (vendedor.getSexo().equals("M")) {
            this.viewVendedor.getCbSexo().setSelectedIndex(1);
        } else {
            this.viewVendedor.getCbSexo().setSelectedIndex(2);
        }
        this.viewVendedor.getTfIdade().setText(vendedor.getIdade() + "");
        this.viewVendedor.getTfArea().setText(vendedor.getAreaVenda());
        this.viewVendedor.getCbCidade().setSelectedItem(vendedor.getCidadeIdCidade().getNome());
        this.viewVendedor.getCbUf().setSelectedItem(vendedor.getCidadeIdCidade().getEstadoIdEstado().getUf());
        this.viewVendedor.getTfSalario().setText(vendedor.getSalario() + "");
    }
}
