//@author LUCAS FERNANDO DE ASSIS
//Data de criação 07/09/2018
package com.mycompany.jsf.carros.entidades;

import java.util.Date;

public class Carro {

    private Integer id;
    private String modelo;
    public String fabricante;
    private String marca;
    private String cor;
    private Date ano;

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }
    // </editor-fold>
}
