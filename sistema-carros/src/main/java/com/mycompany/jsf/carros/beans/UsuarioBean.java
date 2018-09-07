//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.beans;

import com.mycompany.jsf.carros.dao.UsuarioDAO;
import com.mycompany.jsf.carros.entidades.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class UsuarioBean extends CrudBean<Usuario, UsuarioDAO>{
    private UsuarioDAO dao;
    
    @Override
    public UsuarioDAO getDao() {
        if(dao == null){
            dao = new UsuarioDAO();
        }
        
        return dao;
    }

    @Override
    public Usuario createNewEntity() {
        return new Usuario();
    }
}
