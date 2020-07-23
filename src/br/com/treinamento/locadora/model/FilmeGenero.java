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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaop
 */
@Entity
@Table(name = "filme_genero")
@NamedQueries({
    @NamedQuery(name = "FilmeGenero.findAll", query = "SELECT f FROM FilmeGenero f"),
    @NamedQuery(name = "FilmeGenero.findByIdFilmeGenero", query = "SELECT f FROM FilmeGenero f WHERE f.idFilmeGenero = :idFilmeGenero"),
    @NamedQuery(name = "FilmeGenero.findByAtivo", query = "SELECT f FROM FilmeGenero f WHERE f.ativo = :ativo")})
public class FilmeGenero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_filme_genero")
    private Integer idFilmeGenero;
    @Basic(optional = false)
    @Column(name = "ativo")
    private String ativo;
    @JoinColumn(name = "filme_id_filme", referencedColumnName = "id_filme")
    @ManyToOne(optional = false)
    private Filme filmeIdFilme;
    @JoinColumn(name = "genero_id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero generoIdGenero;

    public FilmeGenero() {
    }

    public FilmeGenero(Integer idFilmeGenero) {
        this.idFilmeGenero = idFilmeGenero;
    }

    public FilmeGenero(Integer idFilmeGenero, String ativo) {
        this.idFilmeGenero = idFilmeGenero;
        this.ativo = ativo;
    }

    public FilmeGenero(String ativo, Filme filmeIdFilme, Genero generoIdGenero) {
        this.ativo = ativo;
        this.filmeIdFilme = filmeIdFilme;
        this.generoIdGenero = generoIdGenero;
    }

    public Integer getIdFilmeGenero() {
        return idFilmeGenero;
    }

    public void setIdFilmeGenero(Integer idFilmeGenero) {
        this.idFilmeGenero = idFilmeGenero;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Filme getFilmeIdFilme() {
        return filmeIdFilme;
    }

    public void setFilmeIdFilme(Filme filmeIdFilme) {
        this.filmeIdFilme = filmeIdFilme;
    }

    public Genero getGeneroIdGenero() {
        return generoIdGenero;
    }

    public void setGeneroIdGenero(Genero generoIdGenero) {
        this.generoIdGenero = generoIdGenero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilmeGenero != null ? idFilmeGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FilmeGenero)) {
            return false;
        }
        FilmeGenero other = (FilmeGenero) object;
        if ((this.idFilmeGenero == null && other.idFilmeGenero != null) || (this.idFilmeGenero != null && !this.idFilmeGenero.equals(other.idFilmeGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinamento.locadora.model.FilmeGenero[ idFilmeGenero=" + idFilmeGenero + " ]";
    }
    
}
