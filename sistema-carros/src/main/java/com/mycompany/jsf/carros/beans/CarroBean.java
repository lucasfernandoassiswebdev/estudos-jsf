//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.beans;

import com.mycompany.jsf.carros.dao.CarroDAO;
import com.mycompany.jsf.carros.entidades.Carro;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class CarroBean extends CrudBean<Carro, CarroDAO> {
    private CarroDAO dao;

    @Override
    public CarroDAO getDao() {
        if(dao == null){
            dao = new CarroDAO();
        }
        
        return dao;
    }

    @Override
    public Carro createNewEntity() {
        return new Carro();
    }
}
