//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.primeiro.projeto.maven.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class NomesBean {

    private String nome;
    private String sobrenome;
    private String mensagem;

    public void dizerOla() {
        mensagem = "Olá " + nome + " " + sobrenome;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getMensagem() {
        return mensagem;
    }
    // </editor-fold>
}
