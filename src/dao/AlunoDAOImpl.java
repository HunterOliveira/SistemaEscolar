package dao;

import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO{

    @Override
    public void insert(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (nome, idade, sexo, nivel_ensino) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoDao.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getIdade());
            pstmt.setString(3, aluno.getSexo());
            pstmt.setString(4, aluno.getNivel_ensino());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long idGerado = rs.getLong(1);
                    aluno.setId((int) idGerado);
                }
            }
            System.out.println("Aluno " + aluno.getNome() + " inserido com sucesso! ID gerado: " + aluno.getId());
        }
    }

    @Override
    public Aluno findById(int id) throws SQLException {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    return new Aluno(
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
    public void update(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET  nome = ?, idade = ?, sexo = ?, nivel_ensino = ? WHERE id = ? ";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, aluno.getNome());
            psmt.setInt(2, aluno.getIdade());
            psmt.setString(3, aluno.getSexo());
            psmt.setString(4, aluno.getNivel_ensino());
            psmt.setInt(5, aluno.getId());
            int linhasAtualizadas = psmt.executeUpdate();
            if (linhasAtualizadas > 0) {
                System.out.println("Aluno com ID " + aluno.getId() + " atualizado com sucesso!");
            } else {
                System.out.println("Aluno com ID" + aluno.getId() + " não identificado");
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setInt(1, id);
            int linhasAtualizadas = psmt.executeUpdate();
            if (linhasAtualizadas > 0){
                System.out.println("Aluno com ID " + id + " excluido com sucesso!");
            } else{
                System.out.println("Aluno com ID " + id + " não encontrado");
            }
        }
    }

    @Override
    public List<Aluno> findAll() throws SQLException {
        List <Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, idade, sexo, nivel_ensino FROM alunos ORDER BY nome";
        try (Connection connection = ConexaoDao.getConnection();
             PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()){
            while (rs.next()) {
                Aluno aluno = new Aluno(
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