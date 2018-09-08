//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao;

import br.mycompany.jsf.carros.utils.exceptions.ErroSistema;
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
    public void save(Usuario usuario) throws ErroSistema {
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
            throw new ErroSistema("Erro ao salvar usuário", ex);
        }
    }

    @Override
    public void delete(int id_usuario) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM USUARIO WHERE ID = ?");
            ps.setInt(1, id_usuario);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (ErroSistema | SQLException ex) {
            throw new ErroSistema("Erro ao excluir usuário", ex);
        }
    }

    @Override
    public List<Usuario> get() throws ErroSistema {
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
            throw new ErroSistema("Erro ao listar usuários", ex);
        }

        return lista_usuarios;
    }
}
