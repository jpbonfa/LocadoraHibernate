/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinamento.locadora.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joaop
 */
@Entity
@Table(name = "filme")
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT f FROM Filme f"),
    @NamedQuery(name = "Filme.findByIdFilme", query = "SELECT f FROM Filme f WHERE f.idFilme = :idFilme"),
    @NamedQuery(name = "Filme.findByNome", query = "SELECT f FROM Filme f WHERE f.nome = :nome"),
    @NamedQuery(name = "Filme.findByValor", query = "SELECT f FROM Filme f WHERE f.valor = :valor"),
    @NamedQuery(name = "Filme.findByValorPromocao", query = "SELECT f FROM Filme f WHERE f.valorPromocao = :valorPromocao"),
    @NamedQuery(name = "Filme.findByDisponivel", query = "SELECT f FROM Filme f WHERE f.disponivel = :disponivel"),
    @NamedQuery(name = "Filme.findByPromocao", query = "SELECT f FROM Filme f WHERE f.promocao = :promocao")})
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_filme")
    private Integer idFilme;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_promocao")
    private Double valorPromocao;
    @Basic(optional = false)
    @Column(name = "disponivel")
    private String disponivel;
    @Basic(optional = false)
    @Column(name = "promocao")
    private String promocao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmeIdFilme")
    private List<FilmeGenero> filmeGeneroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filmeIdFilme")
    private List<ItemLocacao> itemLocacaoList;

    public Filme() {
    }

    public Filme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public Filme(Integer idFilme, String nome, double valor, String disponivel, String promocao) {
        this.idFilme = idFilme;
        this.nome = nome;
        this.valor = valor;
        this.disponivel = disponivel;
        this.promocao = promocao;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Double getValorPromocao() {
        return valorPromocao;
    }

    public void setValorPromocao(Double valorPromocao) {
        this.valorPromocao = valorPromocao;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public List<FilmeGenero> getFilmeGeneroList() {
        return filmeGeneroList;
    }

    public void setFilmeGeneroList(List<FilmeGenero> filmeGeneroList) {
        this.filmeGeneroList = filmeGeneroList;
    }

    public List<ItemLocacao> getItemLocacaoList() {
        return itemLocacaoList;
    }

    public void setItemLocacaoList(List<ItemLocacao> itemLocacaoList) {
        this.itemLocacaoList = itemLocacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilme != null ? idFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filme)) {
            return false;
        }
        Filme other = (Filme) object;
        if ((this.idFilme == null && other.idFilme != null) || (this.idFilme != null && !this.idFilme.equals(other.idFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinamento.locadora.model.Filme[ idFilme=" + idFilme + " ]";
    }
    
}
