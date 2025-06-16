package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.AlunoEspecial;
import model.Turma;
import exception.NotaInvalidaException;
import exception.IdadeInvalidaException;
import exception.MatriculaVaziaException;

public class Menu {
    private Turma turma;
    private Scanner scanner;

    public Menu() {
        this.turma = new Turma();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Adicionar novo aluno");
            System.out.println("2. Listar todos os alunos");
            System.out.println("3. Sair do programa");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpando buffer toda vez, como o senhor ensinou na revisão kkkk

                switch (opcao) {
                    case 1:
                        adicionarAluno();
                        break;
                    case 2:
                        listarAlunos();
                        break;
                    case 3:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine();
            }
        } while (opcao != 3);
    }

    private void adicionarAluno() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            if (idade <= 17) {
                throw new IdadeInvalidaException("Idade deve ser maior que 17 anos");
            }

            System.out.print("Matrícula: ");
            scanner.nextLine();
            String matricula = scanner.nextLine();
            if (matricula.isEmpty()) {
                throw new MatriculaVaziaException("Matrícula não pode ser vazia");
            }

            System.out.print("Nota N1 (0-10): ");
            double n1 = scanner.nextDouble();
            validarNota(n1);

            System.out.print("Nota N2 (0-10): ");
            double n2 = scanner.nextDouble();
            validarNota(n2);

            System.out.print("Nota N3 (0-10): ");
            double n3 = scanner.nextDouble();
            validarNota(n3);

            System.out.print("É aluno especial? (S/N): ");
            scanner.nextLine();
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Necessidades especiais? (S/N): ");
                boolean necessidadesEspeciais = scanner.nextLine().equalsIgnoreCase("S");

                System.out.print("Tipo de acompanhamento: ");
                String tipoAcompanhamento = scanner.nextLine();

                AlunoEspecial aluno = new AlunoEspecial(nome, idade, matricula, n1, n2, n3,
                        necessidadesEspeciais, tipoAcompanhamento);
                turma.adicionarAluno(aluno);
            } else {
                Aluno aluno = new Aluno(nome, idade, matricula, n1, n2, n3);
                turma.adicionarAluno(aluno);
            }

            System.out.println("Aluno adicionado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida para número!");
            scanner.nextLine();
        } catch (NotaInvalidaException | IdadeInvalidaException | MatriculaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void validarNota(double nota) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException("Nota deve estar entre 0 e 10");
        }
    }

    private void listarAlunos() {
        List<Aluno> alunos = turma.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE ALUNOS ===");
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }
        }
    }
}