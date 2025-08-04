package view;


import dao.AlunoDAO;
import dao.AlunoDAOImpl;
import model.Aluno;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class mainView {
    private static final AlunoDAO alunoDAO = new AlunoDAOImpl();

    public static void main(String[] args) {
        //bloco do do-while tratado
        try(Scanner sc = new Scanner(System.in)) {
            int opc = 0;
            do {
                menuexibicao();
                try {
                    opc = sc.nextInt();
                    sc.nextLine();
                    switch (opc) {
                        case 1:
                            criarAluno(sc);
                            break;
                        case 2:
                            lerAluno();
                            break;
                        case 3:
                            atualizarAluno(sc);
                            break;
                        case 4:
                            deletarAluno(sc);
                            break;
                        case 5:
                            System.out.println("Saindo");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("DIGITE UM NUMERO");
                    sc.nextLine();
                    opc = 0;
                }
            } while (opc != 5);
        } catch (Exception e) {
            System.out.println("ERRO");
            e.printStackTrace();
        }


        

    }

    private static void deletarAluno(Scanner sc) {
    }


    private static void menuexibicao() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Criar novo aluno(a)");
        System.out.println("2. Listar todos os alunos(as)");
        System.out.println("3. Atualizar dados de um aluno(a)");
        System.out.println("4. Deletar um aluno(a)");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarAluno(Scanner sc) throws SQLException {
        System.out.println("Digite o nome do(a) aluno(a) ");
        String nome = sc.nextLine();
        sc.nextLine();
        System.out.println("Digite a idade do(a) aluno(a) ");
        int idade = Integer.parseInt(sc.nextLine());
        System.out.println("Digite o seu sexo(a) aluno(a) ");
        String sexo = sc.nextLine();
        System.out.println("Digite o nivel de ensino do(a) aluno(a) ");
        String nivel =  sc.nextLine();

        Aluno novoAluno = new Aluno(0, nome, idade, sexo, nivel);
        alunoDAO.insert(novoAluno);
    }

    private static void lerAluno() throws SQLException {
        System.out.println("Lista de alunos: ");
        List<Aluno> listaAlunos = alunoDAO.findAll();

        if (listaAlunos.isEmpty()){
            System.out.println("Nenhum aluno encontrado");
            return;
        }

        System.out.printf("%-5s | %-30s | %-5s | %-10s | %-20s%n", "ID", "Nome", "Idade", "Sexo", "Nível de Ensino");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Aluno aluno : listaAlunos) {
            // 3. Use a variável 'aluno' do loop para pegar os dados de CADA objeto
            System.out.printf("%-5d | %-30s | %-5d | %-10s | %-20s%n",
                    aluno.getId(), aluno.getNome(), aluno.getIdade(), aluno.getSexo(), aluno.getNivel_ensino());
        }


    }

    private static void atualizarAluno(Scanner sc) throws SQLException {
        System.out.println("Digite o id do(a) aluno(a) ");
        int id = Integer.parseInt(sc.nextLine());

        Aluno alunoExiste = alunoDAO.findById(id);
        if (alunoExiste == null) {
            System.out.println("O aluno de id" + id + "não foi encontrado");
            return;
        }

        System.out.println("Dados atuais do(a) aluna" + alunoExiste);
        System.out.println("\n\n");

        System.out.println("Digite o novo nome do(a) aluno(a) " + alunoExiste.getNome() + "'):");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) {
            alunoExiste.setNome(nome);
        }

        System.out.println("Digite o novo sexo do(a) aluno(a)" + alunoExiste.getSexo() + "'):");
        String sexo = sc.nextLine();
        if (!sexo.isEmpty()) {
            alunoExiste.setSexo(sexo);
        }

        System.out.println("Digite a nova idade do(a) aluno(a)" + alunoExiste.getIdade() + "'):");
        int idade = Integer.parseInt(sc.nextLine());
        alunoExiste.setIdade(idade);

        System.out.println("Digite o novo nivel escolar do(a) aluno(a)" + alunoExiste.getNivel_ensino() + "'):");
        String nivel_ensino = sc.nextLine();
        if (!nivel_ensino.isEmpty()) {
            alunoExiste.setNivel_ensino(nivel_ensino);
        }

    }
}
