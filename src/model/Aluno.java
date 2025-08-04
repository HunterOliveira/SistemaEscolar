package model;

public class Aluno {
    private  int id;
    private  String nome;
    private  int idade;
    private  String sexo;
    private  String nivel_ensino;

    public Aluno(int id, String nome, int idade, String sexo, String nivel_ensino) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.nivel_ensino = nivel_ensino;
    }

    //para novos cadastros!!
    public Aluno(String nome, int idade, String sexo, String nivel_ensino) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.nivel_ensino = nivel_ensino;
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public  int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public  String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public  String getNivel_ensino() {
        return nivel_ensino;
    }

    public void setNivel_ensino(String nivel_ensino) {
        this.nivel_ensino = nivel_ensino;
    }

    @Override
    public String toString() {
        return "aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                ", nivel_ensino=" + nivel_ensino +
                '}';
    }
}
