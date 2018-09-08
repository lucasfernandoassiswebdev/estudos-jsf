//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package br.mycompany.jsf.carros.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
    public static void addMessage(String sumary, String message, FacesMessage.Severity type) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(type, sumary, message);
        context.addMessage(null, fm);
    }
}
