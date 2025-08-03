package dao;

import model.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO{

    @Override
    /*
     * Insere um novo registro de aluno no banco de dados.
     * Utiliza PreparedStatement para evitar ataques de SQL Injection.
     * O bloco try-with-resources garante que a conexão e o statement sejam fechados automaticamente.
     *
     * @param aluno O objeto Aluno a ser inserido.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public void insert(aluno aluno) throws SQLException {
        // A string SQL define a consulta de inserção com '?' como placeholders para os valores.
        String sql = "Insert INTO aluno (id, nome, idade, sexo, nivel_ensino) VALUES(?,?,?,?,?)";
        try (Connection connection = ConexaoDao.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, aluno.getId());
            psmt.setString(2, aluno.getNome());
            psmt.setInt(3, aluno.getIdade());
            psmt.setInt(4, aluno.getSexo());
            psmt.setInt(5, aluno.getNivel_ensino());
            psmt.executeUpdate();
            System.out.println("Aluno" + aluno + " inserido com sucesso");
        }
    }

    @Override
    /*
      Busca um aluno no banco de dados pelo seu ID.

      @param id O ID do aluno a ser buscado.
     * @return O objeto Aluno encontrado, ou null se nenhum aluno for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public aluno findById(int id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = ";
        try (Connection connection = ConexaoDao.getConnection()){
            PreparedStatement psmt = connection.prepareStatement(sql);
            psmt.setInt(1, id);
           try (ResultSet rs = psmt.executeQuery()){
               if (rs.next()){
                   return new aluno (
                           rs.getInt("id"),
                           rs.getString("nome"),
                           rs.getInt("idade"),
                           rs.getString("sexo"),
                           rs.getString("nivel_ensino")
                   );
               }
           }

        }
        return null;
    }

    @Override
    public void update(aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET  nome = ?, idade = ?,sexo = ?, nivel_ensino = ? WHERE id = ? ";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {


            psmt.setString(1, aluno.getNome());
            psmt.setInt(2, aluno.getIdade());
            psmt.setInt(3, aluno.getSexo());
            psmt.setInt(4, aluno.getNivel_ensino());

            // Define o ID do aluno que será atualizado (cláusula WHERE).
            psmt.setInt(5, aluno.getId());

            // Executa a atualização e verifica se alguma linha foi afetada.
            int linhasAtualizadas = psmt.executeUpdate();
            if (linhasAtualizadas > 0) {
                System.out.println("Aluno com ID " + aluno.getId() + " atualizado com sucesso!");
            } else {
                System.out.println("Aluno com ID" + aluno.getId() + "não identificado");
            }

        }
    }


    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)){

            // Define o ID para o placeholder.
             psmt.setInt(1, id);

            // Executa a atualização e verifica se alguma linha foi afetada.
             int linhasAtualizadas = psmt.executeUpdate();
             if (linhasAtualizadas > 0){
                 System.out.println("Aluno com ID " + id + " excluido com sucesso!");
             } else{
                 System.out.println("Aluno com ID " + id + "não encontrado");
             }
        }

    }

    @Override
    public List<aluno> findAll() throws SQLException {
        List <aluno> alunos = new ArrayList<>();

        String sql = "SELECT id, nome, idade, sexo, nivel_ensino FROM aluno ORDER BY name";

        try (Connection connection = ConexaoDao.getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery()){
            // Itera sobre todos os registros no ResultSet.
            while (rs.next()) {
                // Para cada linha, cria um novo objeto Aluno e o adiciona à lista.
                aluno aluno = new aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("sexo"),
                        rs.getString("nivel_ensino")
                );
                alunos.add(aluno);
            }

        }

        return alunos;
    }
}
