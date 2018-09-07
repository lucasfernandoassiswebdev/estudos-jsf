//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package br.mycompany.jsf.carros.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexao = DriverManager.getConnection("jdbc:sqlserver://localhost;database=CarrosDB;integratedSecurity=true;");
            } catch (SQLException ex) {
                Erro.exibe(ex);
            } catch (ClassNotFoundException ex) {
                Erro.exibe(ex);
            }
        }

        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Erro.exibe(ex);
            }
            conexao = null;
        }
    }
}
