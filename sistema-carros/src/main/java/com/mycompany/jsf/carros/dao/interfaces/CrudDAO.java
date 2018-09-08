//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao.interfaces;

import br.mycompany.jsf.carros.utils.exceptions.ErroSistema;
import java.util.List;

public interface CrudDAO<T> {

    public void save(T entity) throws ErroSistema;
    public void delete(int id) throws ErroSistema;
    public List<T> get() throws ErroSistema;
}
