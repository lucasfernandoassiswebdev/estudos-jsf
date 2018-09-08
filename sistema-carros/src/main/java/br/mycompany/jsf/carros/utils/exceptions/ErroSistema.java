//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package br.mycompany.jsf.carros.utils.exceptions;

import br.mycompany.jsf.carros.utils.Messages;
import javax.faces.application.FacesMessage;

public class ErroSistema extends Exception {

    public ErroSistema(String message) {
        super(message);
        Messages.addMessage("Erro", message, FacesMessage.SEVERITY_ERROR);
    }

    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
        Messages.addMessage(cause.getMessage(), cause.getMessage(), FacesMessage.SEVERITY_ERROR);
    }
}
