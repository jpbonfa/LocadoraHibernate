package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.ItemLocacaoDAO;
import br.com.treinamento.locadora.model.ItemLocacao;
import br.com.treinamento.locadora.util.JOptionPaneUtil;
import br.com.treinamento.locadora.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class ItemLocacaoController {

    public ArrayList<ItemLocacao> buscarTodos() {
        return new ItemLocacaoDAO().buscarTodos();
    }

    public ArrayList<ItemLocacao> buscarFilmesLocacao(int idLocacao) {
        ArrayList<ItemLocacao> retorno = buscarTodos();
        for (ItemLocacao filmeLocacao : retorno) {
            if (filmeLocacao.getLocacaoIdLocacao().getIdLocacao() == idLocacao) {
                retorno.add(filmeLocacao);
            }
        }

        return retorno;
    }

    public void salvar(ItemLocacao itemLocacao) {
        try {
            new ItemLocacaoDAO().salvar(itemLocacao);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.itemLocacaoErroSalvar);

        }
    }

    public ArrayList<ItemLocacao> buscarLocacao(int codigo) {
        ArrayList<ItemLocacao> lista = new ArrayList<>();
        for (ItemLocacao item : buscarTodos()) {
            if (item.getLocacaoIdLocacao().getIdLocacao() == codigo) {
                lista.add(item);
            }
        }
        return lista;
    }

}
