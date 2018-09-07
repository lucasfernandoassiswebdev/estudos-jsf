//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package com.mycompany.jsf.carros.dao;

import br.mycompany.jsf.carros.utils.Erro;
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

public class CarroDAO implements CrudDAO<Carro>{

    @Override
    public void save(Carro carro) {
        Connection conexao = FabricaConexao.getConexao();
        try {
            String comando = carro.getId() == null
                    ? "INSERT INTO CARRO(MODELO, FABRICANTE, COR, ANO) VALUES(?,?,?,?)"
                    : "UPDATE CARRO SET MODELO = ?, FABRICANTE = ?, COR = ?, ANO = ? WHERE ID = ?";
            PreparedStatement ps = conexao.prepareStatement(comando);
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getCor());
            ps.setDate(4, new Date(carro.getAno().getTime()));
            if (carro.getId() != null) {
                ps.setInt(5, carro.getId());
            }
            ps.executeUpdate();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Erro.exibe(ex);
        }
    }

    @Override
    public List<Carro> get() {
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
            Erro.exibe(ex);
        }

        return lista_carros;
    }

    @Override
    public void delete(Carro carro) throws Erro {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM CARRO WHERE ID = ?");
            ps.setInt(1, carro.getId());
            ps.execute();
        } catch (Exception ex) {
            Erro.exibe(ex);
        }
    }
}
