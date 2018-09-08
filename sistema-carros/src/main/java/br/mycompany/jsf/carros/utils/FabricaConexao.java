//@author LUCAS FERNANDO DE ASSIS 
//Data de criação - 07/09/2018
package br.mycompany.jsf.carros.utils;

import br.mycompany.jsf.carros.utils.exceptions.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static Connection conexao;

    public static Connection getConexao() throws ErroSistema {
        if (conexao == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexao = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=CarrosDB;", "sa", "a");
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possível conectar ao banco de dados", ex);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("Driver do banco de dados não foi encontrado", ex);
            }
        }

        return conexao;
    }

    public static void fecharConexao() throws ErroSistema {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possível encerrar a conexão com o banco de dados", ex);
            }
        }
    }
}
