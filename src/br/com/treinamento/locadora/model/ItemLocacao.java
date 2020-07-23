/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinamento.locadora.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author joaop
 */
@Entity
@Table(name = "item_locacao")
public class ItemLocacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item_locacao")
    private Integer idItemLocacao;
    @JoinColumn(name = "filme_id_filme", referencedColumnName = "id_filme")
    @ManyToOne(optional = false)
    private Filme filmeIdFilme;
    @JoinColumn(name = "locacao_id_locacao", referencedColumnName = "id_locacao")
    @ManyToOne(optional = false)
    private Locacao locacaoIdLocacao;

    public ItemLocacao() {
    }

    public ItemLocacao(Integer idItemLocacao) {
        this.idItemLocacao = idItemLocacao;
    }

    public Integer getIdItemLocacao() {
        return idItemLocacao;
    }

    public void setIdItemLocacao(Integer idItemLocacao) {
        this.idItemLocacao = idItemLocacao;
    }

    public Filme getFilmeIdFilme() {
        return filmeIdFilme;
    }

    public void setFilmeIdFilme(Filme filmeIdFilme) {
        this.filmeIdFilme = filmeIdFilme;
    }

    public Locacao getLocacaoIdLocacao() {
        return locacaoIdLocacao;
    }

    public void setLocacaoIdLocacao(Locacao locacaoIdLocacao) {
        this.locacaoIdLocacao = locacaoIdLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemLocacao != null ? idItemLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemLocacao)) {
            return false;
        }
        ItemLocacao other = (ItemLocacao) object;
        if ((this.idItemLocacao == null && other.idItemLocacao != null) || (this.idItemLocacao != null && !this.idItemLocacao.equals(other.idItemLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinamento.locadora.model.ItemLocacao[ idItemLocacao=" + idItemLocacao + " ]";
    }
    
}
