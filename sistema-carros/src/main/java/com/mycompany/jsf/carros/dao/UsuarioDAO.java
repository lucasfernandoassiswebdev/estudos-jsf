//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao;

import br.mycompany.jsf.carros.utils.Erro;
import br.mycompany.jsf.carros.utils.FabricaConexao;
import com.mycompany.jsf.carros.dao.interfaces.CrudDAO;
import com.mycompany.jsf.carros.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CrudDAO<Usuario> {

    @Override
    public void save(Usuario usuario) throws Erro {
        Connection conexao = FabricaConexao.getConexao();
        try {
            String comando = usuario.getId() == null
                    ? "INSERT INTO USUARIO(LOGIN, SENHA) VALUES(?,?)"
                    : "UPDATE USUARIO SET LOGIN = ?, SENHA = ? WHERE ID = ?";
            PreparedStatement ps = conexao.prepareStatement(comando);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());

            if (usuario.getId() != null) {
                ps.setInt(3, usuario.getId());
            }
            ps.executeUpdate();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Erro.exibe(ex);
        }
    }

    @Override
    public void delete(Usuario usuario) throws Erro {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM USUARIO WHERE ID = ?");
            ps.setInt(1, usuario.getId());
            ps.execute();
        } catch (Exception ex) {
            Erro.exibe(ex);
        }
    }

    @Override
    public List<Usuario> get() {
        Connection conexao = FabricaConexao.getConexao();
        List<Usuario> lista_usuarios = new ArrayList();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM USUARIO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("ID"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setSenha(rs.getString("SENHA"));
                lista_usuarios.add(usuario);
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Erro.exibe(ex);
        }

        return lista_usuarios;
    }
}
