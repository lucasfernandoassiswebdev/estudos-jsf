//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package br.mycompany.jsf.carros.utils;

import javax.swing.JOptionPane;

public class Erro extends Exception {

    public Erro(String message) {
        super(message);
    }

    public Erro(String message, Throwable cause) {
        super(message, cause);
    }

    public static void exibe(String mensagem) {
        JOptionPane.showMessageDialog(null, "Erro", mensagem, JOptionPane.ERROR_MESSAGE);
    }

    public static void exibe(Exception ex) {
        System.out.println("Erro: " + ex.getMessage());
    }

    public static void exibe(ClassNotFoundException ex) {
        System.out.println("Erro: " + ex.getMessage());
    }
}
