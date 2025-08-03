package model;

public class aluno {
    private int id;
    private String nome;
    private int idade;
    private String sexo;
    private String nivel_ensino;

    public aluno(int id, String nome, int idade, String sexo, String nivelEnsino) {
        this.id = this.id;
        this.nome = this.nome;
        this.idade = this.idade;
        this.sexo = this.sexo;
        this.nivel_ensino = nivel_ensino;
    }

    //para novos cadastros!!
    public aluno(String nome, int idade, String sexo, String nivel_ensino) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.nivel_ensino = nivel_ensino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getNivel_ensino() {
        return nivel_ensino;
    }

    public void setNivel_ensino(char nivel_ensino) {
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
