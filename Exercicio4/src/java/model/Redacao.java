/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "redacao")
@NamedQueries({
    @NamedQuery(name = "Redacao.findAll", query = "SELECT r FROM Redacao r"),
    @NamedQuery(name = "Redacao.findByCodigo", query = "SELECT r FROM Redacao r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Redacao.findByTitulo", query = "SELECT r FROM Redacao r WHERE r.titulo = :titulo"),
    @NamedQuery(name = "Redacao.findByTexto", query = "SELECT r FROM Redacao r WHERE r.texto = :texto"),
    @NamedQuery(name = "Redacao.findByNota", query = "SELECT r FROM Redacao r WHERE r.nota = :nota")})
public class Redacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "texto")
    private String texto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota")
    private Double nota;

    public Redacao() {
    }

    public Redacao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Redacao)) {
            return false;
        }
        Redacao other = (Redacao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Redacao[ codigo=" + codigo + " ]";
    }
    
}
