package dao;

import model.aluno;
import java.util.List;
import java.sql.SQLException;

//interface para implementaçao da criação,remoção e atualização do aluno
public interface AlunoDAO {
    void insert(aluno aluno) throws SQLException;
    aluno findById(int id) throws SQLException;
    void update(aluno aluno) throws SQLException;
    void delete(int id) throws SQLException;
    List<aluno> findAll() throws SQLException;

}
