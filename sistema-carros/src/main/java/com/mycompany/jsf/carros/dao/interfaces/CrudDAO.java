//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao.interfaces;

import br.mycompany.jsf.carros.utils.Erro;
import java.util.List;

public interface CrudDAO<T> {

    public void save(T entity) throws Erro;
    public void delete(T entity) throws Erro;
    public List<T> get();
}
