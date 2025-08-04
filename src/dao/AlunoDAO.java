package dao;

import model.Aluno;
import java.util.List;
import java.sql.SQLException;

//interface para implementaçao da criação,remoção e atualização do aluno
public interface AlunoDAO {
    void insert(Aluno aluno) throws SQLException;
    Aluno findById(int id) throws SQLException;
    void update(Aluno aluno) throws SQLException;
    void delete(int id) throws SQLException;
    List<Aluno> findAll() throws SQLException;
}
