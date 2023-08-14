package dao;

import connection.ConnectionFactory;
import model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection connection;

    public FuncionarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios "
                +"(nome, usuario, senha)"
                +"VALUES (?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, usuario = ?, senha = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setLong(4, funcionario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Funcionario funcionario) {
        String sql = "DELETE FROM funcionarios WHERE id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, funcionario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> getLista() {
        try {
            List<Funcionario> funcionarios = new ArrayList<Funcionario>();
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM funcionarios");
            ResultSet rset = stmt.executeQuery(); // Retorna todas as tuplas da pesquisa

            while (rset.next()) {
                Funcionario funcionario = new Funcionario();
                fillContato(funcionario, rset);

                funcionarios.add(funcionario);
                System.out.println(funcionario);
            }
            rset.close();
            stmt.close();
            return funcionarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Funcionario pesquisar(Long id) {
        Funcionario funcionario = new Funcionario();

        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM funcionarios WHERE id = (?) LIMIT 1");
            stmt.setLong(1, id);
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                fillContato(funcionario, rset);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return funcionario;
    }

    private void fillContato(Funcionario funcionario, ResultSet rset) throws SQLException {
        funcionario.setId(rset.getLong("id"));
        funcionario.setNome(rset.getString("nome"));
        funcionario.setUsuario(rset.getString("usuario"));
        funcionario.setSenha(rset.getString("senha"));
    }
}
