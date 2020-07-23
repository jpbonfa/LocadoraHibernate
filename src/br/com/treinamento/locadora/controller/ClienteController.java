package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.ClienteDAO;
import br.com.treinamento.locadora.model.Cidade;
import br.com.treinamento.locadora.model.Cliente;
import br.com.treinamento.locadora.model.Contato;
import br.com.treinamento.locadora.model.Endereco;
import br.com.treinamento.locadora.model.Estado;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import br.com.treinamento.locadora.util.ServiceUtil;
import br.com.treinamento.locadora.util.Valida;
import br.com.treinamento.locadora.view.ClienteView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsavel por encapsular os metodos de controle do cliente
 *
 * @author joaop
 * @since 07/07/2020
 */
public class ClienteController {

    private ClienteView viewCliente;
    private Cliente cliente;
    private Endereco endereco;
    private Contato contato;
    private Cidade cidade;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Cidade> listaCidade;
    private ArrayList<Estado> listaEstado;
    private boolean alterar = false;

    public ClienteController() {
    }

    public ClienteController(ClienteView viewCliente) {
        this.viewCliente = viewCliente;
    }

    public void carrregarCidade() {
        listaCidade = new CidadeController().buscarTodos();
        this.viewCliente.getCbCidade().removeAllItems();
        this.viewCliente.getCbCidade().addItem("- Selecione -");
        for (Cidade cidade : listaCidade) {
            this.viewCliente.getCbCidade().addItem(cidade.getNome());
        }
    }

    public void carregarEstado() {
        listaEstado = new EstadoController().buscarTodos();
        this.viewCliente.getCbUf().removeAllItems();
        this.viewCliente.getCbUf().addItem("- Selecione -");
        for (Estado estado : listaEstado) {
            this.viewCliente.getCbUf().addItem(estado.getUf());
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTbCliente().getModel();
        modelo.setRowCount(0);
        for (Cliente cliente : listaCliente) {

            // Adicioando a linha com os dados
            modelo.addRow(new String[]{cliente.getNome(), cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), cliente.getContatoIdContato().getTelefone(), cliente.getIdade() + "",});

        }
    }

    public void listarCliente() {
        try {
            listaCliente = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Cliente> buscarTodos() {
        return new ClienteDAO().buscarTodos();
    }

    public void bloquearCampos() {
        this.viewCliente.getBtSalvar().setEnabled(false);
        this.viewCliente.getBtCancelar().setEnabled(false);
        this.viewCliente.getBtNovo().setEnabled(true);
        this.viewCliente.getBtAlterar().setEnabled(true);
        this.viewCliente.getBtExcluir().setEnabled(true);
        this.viewCliente.getBtSair().setEnabled(true);
        this.viewCliente.getTfCpf().setEditable(false);
        this.viewCliente.getTfRg().setEditable(false);
        this.viewCliente.getTfDataNascimento().setEditable(false);
        this.viewCliente.getTfNome().setEditable(false);
        this.viewCliente.getTfIdade().setEditable(false);
        this.viewCliente.getCbSexo().setEnabled(false);
        this.viewCliente.getTfEndereco().setEditable(false);
        this.viewCliente.getTfNumero().setEditable(false);
        this.viewCliente.getTfBairro().setEditable(false);
        this.viewCliente.getTfTelefone().setEditable(false);
        this.viewCliente.getCbCidade().setEnabled(false);
        this.viewCliente.getCbUf().setEnabled(false);
        this.viewCliente.getTfPesquisaNome().grabFocus();
        this.viewCliente.getTfPesquisaNome().setEditable(true);
    }

    public void desbloquearCampos() {
        this.viewCliente.getBtSalvar().setEnabled(true);
        this.viewCliente.getBtCancelar().setEnabled(true);
        this.viewCliente.getTfCpf().setEditable(true);
        this.viewCliente.getTfRg().setEditable(true);
        this.viewCliente.getTfDataNascimento().setEditable(true);
        this.viewCliente.getTfNome().setEditable(true);
        this.viewCliente.getTfIdade().setEditable(true);
        this.viewCliente.getCbSexo().setEnabled(true);
        this.viewCliente.getTfEndereco().setEditable(true);
        this.viewCliente.getTfNumero().setEditable(true);
        this.viewCliente.getTfBairro().setEditable(true);
        this.viewCliente.getTfTelefone().setEditable(true);
        this.viewCliente.getCbCidade().setEnabled(true);
        // this.viewCliente.getCbUf().setEnabled(true);
    }

    public void desbloquiaAlterar() {
        this.viewCliente.getBtNovo().setEnabled(false);
        this.viewCliente.getBtAlterar().setEnabled(false);
        this.viewCliente.getBtExcluir().setEnabled(false);
        this.viewCliente.getBtSair().setEnabled(false);
        this.viewCliente.getBtSalvar().setEnabled(true);
        this.viewCliente.getBtCancelar().setEnabled(true);
        this.viewCliente.getTfPesquisaNome().setEditable(false);
        this.viewCliente.getTfCpf().setEditable(false);
        this.viewCliente.getTfRg().setEditable(false);
        this.viewCliente.getTfDataNascimento().setEditable(false);
        this.viewCliente.getTfNome().setEditable(false);
        this.viewCliente.getTfIdade().setEditable(false);
        this.viewCliente.getCbSexo().setEnabled(false);
        this.viewCliente.getTfEndereco().setEditable(true);
        this.viewCliente.getTfNumero().setEditable(true);
        this.viewCliente.getTfBairro().setEditable(true);
        this.viewCliente.getTfTelefone().setEditable(true);
        this.viewCliente.getCbCidade().setEnabled(true);
        this.viewCliente.getCbUf().setEnabled(false);
        this.viewCliente.getTfEmail().setEditable(true);
        this.viewCliente.getTfCelular().setEditable(true);
        this.viewCliente.getTfEndereco().grabFocus();

    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewCliente.getBtNovo().setEnabled(false);
        this.viewCliente.getBtAlterar().setEnabled(false);
        this.viewCliente.getBtExcluir().setEnabled(false);
        this.viewCliente.getBtSair().setEnabled(false);
        this.viewCliente.getTfPesquisaNome().setEditable(false);
        this.viewCliente.getTfCpf().grabFocus();

    }

    public void acaoBotaoAlterar() {

        if (this.viewCliente.getTbCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneCliente, Mensagem.erro, 0);
        } else {
            desbloquiaAlterar();
            alterar = true;
            cliente = listaCliente.get(this.viewCliente.getTbCliente().getSelectedRow());
            carregarTela();
        }
    }

    public void acaoBotaoExcluir() {
        if (this.viewCliente.getTbCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneCliente, Mensagem.selecione, 0);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirCliente);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    cliente = listaCliente.get(this.viewCliente.getTbCliente().getSelectedRow());
                    new ClienteDAO().excluir(cliente);
                    new EnderecoController().excluir(cliente.getEnderecoIdEndereco());
                    new ContatoController().excluir(cliente.getContatoIdContato());
                    JOptionPane.showMessageDialog(null, Mensagem.clienteExcluido, Mensagem.sucesso, 1);
                    listarCliente();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, Mensagem.clienteErroExcluir, Mensagem.erro, 0);
                    e.printStackTrace();
                }
            }

        }
    }

    public void acaoBotaoSair() {
        this.viewCliente.setVisible(false);

    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validarAlterar()) {
                endereco = cliente.getEnderecoIdEndereco();
                contato = cliente.getContatoIdContato();

                endereco.setEndereco(this.viewCliente.getTfEndereco().getText());
                endereco.setNumero(Integer.parseInt(this.viewCliente.getTfNumero().getText()));
                endereco.setBairro(this.viewCliente.getTfBairro().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewCliente.getCbCidade().getSelectedIndex()));
                new EnderecoController().salvar(endereco);

                contato.setTelefone(this.viewCliente.getTfTelefone().getText());
                contato.setEmail(this.viewCliente.getTfEmail().getText());
                contato.setCelular(this.viewCliente.getTfCelular().getText());
                new ContatoController().salvar(contato);

                cliente.setEnderecoIdEndereco(endereco);
                cliente.setContatoIdContato(contato);
                try {
                    new ClienteDAO().salvar(cliente);
                    limparCampos();
                    bloquearCampos();
                    listarCliente();
                    JOptionPaneUtil.sucesso(Mensagem.clienteAlterado);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.clienteErroAlterar);
                }
            }
        } else {
            if (validarIncluir()) {
                cliente = new Cliente();
                endereco = new Endereco();
                contato = new Contato();

                endereco.setEndereco(this.viewCliente.getTfEndereco().getText());
                endereco.setNumero(Integer.parseInt(this.viewCliente.getTfNumero().getText()));
                endereco.setBairro(this.viewCliente.getTfBairro().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewCliente.getCbCidade().getSelectedIndex()));
                new EnderecoController().salvar(endereco);

                contato.setTelefone(this.viewCliente.getTfTelefone().getText());
                contato.setEmail(this.viewCliente.getTfEmail().getText());
                contato.setCelular(this.viewCliente.getTfCelular().getText());
                new ContatoController().salvar(contato);

                cliente.setCpf(this.viewCliente.getTfCpf().getText());
                cliente.setRg(this.viewCliente.getTfRg().getText());
                cliente.setDataNascimento(this.viewCliente.getTfDataNascimento().getText());
                cliente.setNome(this.viewCliente.getTfNome().getText());
                cliente.setIdade(Integer.parseInt(this.viewCliente.getTfIdade().getText()));
                if (this.viewCliente.getCbSexo().getSelectedIndex() == 1) {
                    cliente.setSexo("M");
                } else {
                    cliente.setSexo("F");
                }

                cliente.setEnderecoIdEndereco(endereco);
                cliente.setContatoIdContato(contato);
                try {
                    new ClienteDAO().salvar(cliente);
                    limparCampos();
                    bloquearCampos();
                    listarCliente();
                    JOptionPaneUtil.sucesso(Mensagem.clienteSalvo);
                    alterar = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPaneUtil.erro(Mensagem.clienteErroSalvar);
                }

            }
        }
    }

    public boolean validarIncluir() {
        if (Valida.verificaCpfVazio(this.viewCliente.getTfCpf().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCpfCliente);
            return false;
        }
        if (Valida.verificaRgVazio(this.viewCliente.getTfRg().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeRgCliente);
            return false;
        }
        if (Valida.verificaDataVazio(this.viewCliente.getTfDataNascimento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeDataCliente);
            return false;
        } else if (Valida.validaData(ServiceUtil.quebraData(this.viewCliente.getTfDataNascimento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.dataInvalida);
            return false;
        }

        if (Valida.verificaStringVazio(this.viewCliente.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNomeCliente);
            return false;
        } else if (Valida.apenasLetras(this.viewCliente.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.nomeInvalido);
            return false;
        }
        if (Valida.verificaIntZero(this.viewCliente.getTfIdade().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeIdadeCliente);
            return false;
        }
        if (this.viewCliente.getCbSexo().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeSexoCliente);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfEndereco().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEnderecoCliente);
            return false;
        }
        if (Valida.verificaIntZero(this.viewCliente.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumeroCliente);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairroCliente);
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewCliente.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefoneCliente);
            return false;
        }

        if (this.viewCliente.getCbCidade().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeCidadeCliente);
            return false;
        }
        if (this.viewCliente.getCbUf().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeUfCliente);
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewCliente.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelularCliente);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailCliente);
            return false;
        }

        return true;
    }

    public boolean validarAlterar() {
        if (Valida.verificaStringVazio(this.viewCliente.getTfEndereco().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEnderecoCliente);
            return false;
        }
        if (Valida.verificaIntZero(this.viewCliente.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumeroCliente);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairroCliente);
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewCliente.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefoneCliente);
            return false;
        }

        if (this.viewCliente.getCbCidade().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeCidadeCliente);
            return false;
        }
        if (this.viewCliente.getCbUf().getSelectedIndex() == 0) {
            JOptionPaneUtil.erro(Mensagem.informeUfCliente);
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewCliente.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelularCliente);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailCliente);
            return false;
        }

        return true;

    }

    public void acaoBotaoCancelar() {
        bloquearCampos();
        this.viewCliente.getBtNovo().setEnabled(true);
        this.viewCliente.getBtAlterar().setEnabled(true);
        this.viewCliente.getBtExcluir().setEnabled(true);
        this.viewCliente.getBtSair().setEnabled(true);
        this.viewCliente.getTfPesquisaNome().setEditable(true);
        this.viewCliente.getTfPesquisaNome().grabFocus();
        limparCampos();
    }

    public void limparCampos() {

        this.viewCliente.getTfCpf().setText(null);
        this.viewCliente.getTfRg().setText(null);
        this.viewCliente.getTfDataNascimento().setText(null);
        this.viewCliente.getTfNome().setText(null);
        this.viewCliente.getTfIdade().setText(null);
        this.viewCliente.getCbSexo().setSelectedIndex(0);
        this.viewCliente.getTfEndereco().setText(null);
        this.viewCliente.getTfNumero().setText(null);
        this.viewCliente.getTfBairro().setText(null);
        this.viewCliente.getTfTelefone().setText(null);
        this.viewCliente.getCbCidade().setSelectedIndex(0);
        this.viewCliente.getCbUf().setSelectedIndex(0);
    }

    public void carregarUf() {
        this.viewCliente.getCbUf().setSelectedItem(listaCidade.get(this.viewCliente.getCbCidade().getSelectedIndex()).getEstadoIdEstado().getUf());
    }

    public void carregarTela() {
        this.viewCliente.getTfCpf().setText(cliente.getCpf());
        this.viewCliente.getTfRg().setText(cliente.getRg());
        this.viewCliente.getTfDataNascimento().setText(cliente.getDataNascimento());
        this.viewCliente.getTfNome().setText(cliente.getNome());
        this.viewCliente.getTfIdade().setText(cliente.getIdade() + "");
        if (cliente.getSexo().equals("M")) {
            this.viewCliente.getCbSexo().setSelectedIndex(1);
        } else {
            this.viewCliente.getCbSexo().setSelectedIndex(2);
        }
        this.viewCliente.getTfEndereco().setText(cliente.getEnderecoIdEndereco().getEndereco());
        this.viewCliente.getTfNumero().setText(cliente.getEnderecoIdEndereco().getNumero() + "");
        this.viewCliente.getTfBairro().setText(cliente.getEnderecoIdEndereco().getBairro());
        this.viewCliente.getTfTelefone().setText(cliente.getContatoIdContato().getTelefone());
        this.viewCliente.getCbCidade().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        this.viewCliente.getCbUf().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getUf());
        this.viewCliente.getTfEmail().setText(cliente.getContatoIdContato().getEmail());
        this.viewCliente.getTfCelular().setText(cliente.getContatoIdContato().getCelular());
    }

}
