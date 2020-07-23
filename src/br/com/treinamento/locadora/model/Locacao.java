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
@Table(name = "locacao")
@NamedQueries({
    @NamedQuery(name = "Locacao.findAll", query = "SELECT l FROM Locacao l"),
    @NamedQuery(name = "Locacao.findByIdLocacao", query = "SELECT l FROM Locacao l WHERE l.idLocacao = :idLocacao"),
    @NamedQuery(name = "Locacao.findByDataLocacao", query = "SELECT l FROM Locacao l WHERE l.dataLocacao = :dataLocacao"),
    @NamedQuery(name = "Locacao.findByDataDevolucao", query = "SELECT l FROM Locacao l WHERE l.dataDevolucao = :dataDevolucao"),
    @NamedQuery(name = "Locacao.findByValor", query = "SELECT l FROM Locacao l WHERE l.valor = :valor"),
    @NamedQuery(name = "Locacao.findByFormaPagamento", query = "SELECT l FROM Locacao l WHERE l.formaPagamento = :formaPagamento"),
    @NamedQuery(name = "Locacao.findByDevolvido", query = "SELECT l FROM Locacao l WHERE l.devolvido = :devolvido")})
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_locacao")
    private Integer idLocacao;
    @Basic(optional = false)
    @Column(name = "data_locacao")
    private String dataLocacao;
    @Basic(optional = false)
    @Column(name = "data_devolucao")
    private String dataDevolucao;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @Basic(optional = false)
    @Column(name = "devolvido")
    private String devolvido;
    @JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;
    @JoinColumn(name = "vendedor_id_vendedor", referencedColumnName = "id_vendedor")
    @ManyToOne(optional = false)
    private Vendedor vendedorIdVendedor;

    public Locacao() {
    }

    public Locacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Locacao(Integer idLocacao, String dataLocacao, String dataDevolucao, double valor, String formaPagamento, String devolvido) {
        this.idLocacao = idLocacao;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.devolvido = devolvido;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(String devolvido) {
        this.devolvido = devolvido;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public Vendedor getVendedorIdVendedor() {
        return vendedorIdVendedor;
    }

    public void setVendedorIdVendedor(Vendedor vendedorIdVendedor) {
        this.vendedorIdVendedor = vendedorIdVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacao != null ? idLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locacao)) {
            return false;
        }
        Locacao other = (Locacao) object;
        if ((this.idLocacao == null && other.idLocacao != null) || (this.idLocacao != null && !this.idLocacao.equals(other.idLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinamento.locadora.model.Locacao[ idLocacao=" + idLocacao + " ]";
    }
    
}
