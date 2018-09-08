//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao;

import br.mycompany.jsf.carros.utils.exceptions.ErroSistema;
import br.mycompany.jsf.carros.utils.FabricaConexao;
import com.mycompany.jsf.carros.dao.interfaces.CrudDAO;
import com.mycompany.jsf.carros.entidades.Carro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements CrudDAO<Carro> {

    @Override
    public void save(Carro carro) throws ErroSistema {
        Connection conexao = FabricaConexao.getConexao();
        try {
            String comando = carro.getId() == null
                    ? "INSERT INTO CARRO(MODELO, FABRICANTE, COR, ANO, MARCA) VALUES(?,?,?,?,?)"
                    : "UPDATE CARRO SET MODELO = ?, FABRICANTE = ?, COR = ?, ANO = ?, MARCA = ? WHERE ID = ?";
            PreparedStatement ps = conexao.prepareStatement(comando);
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getCor());
            ps.setDate(4, new Date(carro.getAno().getTime()));
            ps.setString(5, carro.getMarca());
            if (carro.getId() != null) {
                ps.setInt(6, carro.getId());
            }
            ps.executeUpdate();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar carro", ex);
        }
    }

    @Override
    public List<Carro> get() throws ErroSistema {
        Connection conexao = FabricaConexao.getConexao();
        List<Carro> lista_carros = new ArrayList();
        try {
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM CARRO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("ID"));
                carro.setModelo(rs.getString("MODELO"));
                carro.setFabricante(rs.getString("FABRICANTE"));
                carro.setMarca(rs.getString("MARCA"));
                carro.setCor(rs.getString("COR"));
                carro.setAno(rs.getDate("ANO"));
                lista_carros.add(carro);
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao listar carros",ex);
        }

        return lista_carros;
    }

    @Override
    public void delete(int id_carro) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM CARRO WHERE ID = ?");
            ps.setInt(1, id_carro);
            ps.execute();
        } catch (ErroSistema | SQLException ex) {
            throw new ErroSistema("Erro ao excluir carro",ex);
        }
    }
}
