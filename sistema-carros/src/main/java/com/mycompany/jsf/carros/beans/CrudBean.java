//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.beans;

import br.mycompany.jsf.carros.utils.Messages;
import br.mycompany.jsf.carros.utils.exceptions.ErroSistema;
import com.mycompany.jsf.carros.dao.interfaces.CrudDAO;
import java.util.List;
import javax.faces.application.FacesMessage;

public abstract class CrudBean<T, D extends CrudDAO> {

    private String screen_state = "search"; //insert, edit, search
    private T entity;
    private List<T> entities;

    public void newEntity() {
        entity = createNewEntity();
        changeStateToInsert();
    }

    public void save() throws ErroSistema {
        getDao().save(entity);
        entity = createNewEntity();
        Messages.addMessage("Sucesso!", "Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        changeStateToSearch();
    }

    public void put(T entity) {
        this.entity = entity;
        changeStateToEdit();
    }

    public void delete(int id) throws ErroSistema {
        getDao().delete(id);
        entities.remove(id);
        Messages.addMessage("Sucesso!", "Deletado com sucesso", FacesMessage.SEVERITY_INFO);
    }

    public void get() throws ErroSistema {
        if (!isSearch()) {
            changeStateToSearch();
            return;
        }

        entities = getDao().get();

        if (entities == null || entities.isEmpty()) {
            Messages.addMessage("Nenhum dado encontrado!", "Não há registros para exibir", FacesMessage.SEVERITY_WARN);
        }
    }

    public abstract D getDao();

    public abstract T createNewEntity();

    // <editor-fold defaultstate="collapsed" desc="Métodos para controle da tela">
    public boolean isInsert() {
        return screen_state.equals("insert");
    }

    public boolean isEdit() {
        return screen_state.equals("edit");
    }

    public boolean isSearch() {
        return screen_state.equals("search");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos para mudar o estado da tela">
    public void changeStateToInsert() {
        screen_state = "insert";
    }

    public void changeStateToEdit() {
        screen_state = "edit";
    }

    public void changeStateToSearch() {
        screen_state = "search";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }
    // </editor-fold>
}
