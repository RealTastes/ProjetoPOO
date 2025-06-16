package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.AlunoEspecial;
import model.Turma;
import exception.NotaInvalidaException;
import exception.OpcaoInvalidaAlunoEspecialException;
import exception.IdadeInvalidaException;
import exception.MatriculaVaziaException;
import exception.OpcaoInvalidaNecessidadeEspecialException;
import exception.AcompanhamentoInvalidoException;

public class Menu {
    private Turma turma;
    private Scanner scanner;

    private String nome = "";
    private int idade = 0;
    private String matricula = "";
    private double n1;
    private double n2;
    private double n3;
    private String resposta = "";
    private String necessidades = "";
    private String acompanhamento = "";
    
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

    private String adicionarNome() {
        System.out.print("Nome: ");
        return scanner.nextLine();
        
    }

    private int adicionarIdade(){
        System.out.print("Idade: ");
        return scanner.nextInt();
    }

    private String adicionarMatricula(){
        scanner.nextLine();
        System.out.print("Matrícula: ");
        return scanner.nextLine();
    }

    private double adicionarN1(){
        System.out.print("Nota da N1 (0-10): ");
        return scanner.nextDouble();
    }

    private double adicionarN2(){
        System.out.print("Nota da N2 (0-10): ");
        return scanner.nextDouble();
    }

    private double adicionarN3(){
        System.out.print("Nota da N3 (0-10): ");
        return scanner.nextDouble();
    }
    private String adicionarAlunoEspecial(){
        scanner.nextLine();
        System.out.print("É Aluno Especial? (S/N): ");
        return scanner.nextLine();
    }
    private void adicionarAluno() {
        try {

            if(this.nome.isEmpty()){
                this.nome = adicionarNome();
            }

            if(this.idade == 0){
                this.idade = adicionarIdade();
                if (this.idade <= 17) {
                    throw new IdadeInvalidaException("Idade deve ser maior que 17 anos");
                }

                if(this.idade <= 0){
                    throw new IdadeInvalidaException("Idade deve ser maior que zero");
                }
            }

            if(this.matricula.isEmpty()){
                this.matricula = adicionarMatricula();
                if (this.matricula.isEmpty()) {
                    throw new MatriculaVaziaException("Matrícula não pode ser vazia: ");
                }
            }

            if(this.n1 == 0.0){
                this.n1 = adicionarN1();
                validarNota(n1);
            }

            if(this.n2 == 0.0){
                this.n2 = adicionarN2();
                validarNota(n2);
            }

            if(this.n3 == 0.0){
                this.n3 = adicionarN3();
                validarNota(n3);
            }

            if(this.resposta.isEmpty()){
                this.resposta = adicionarAlunoEspecial();
            }

            if (!(this.resposta.equalsIgnoreCase("S") || this.resposta.equalsIgnoreCase("N"))){
                throw new OpcaoInvalidaAlunoEspecialException("Opção de Aluno Especial inválida. Aperte enter e tente novamente.");
            }
            if (this.resposta.equalsIgnoreCase("S")) {

                if(this.necessidades.isEmpty()){
                    System.out.print("Necessidades especiais? (S/N): ");
                    this.necessidades = scanner.nextLine();
                }

                if(!(this.necessidades.equalsIgnoreCase("S") || this.necessidades.equalsIgnoreCase("N"))){
                    throw new OpcaoInvalidaNecessidadeEspecialException("Opção de Necessidade Especial inválida. Aperte enter e tente novamente.");
                }

                boolean necessidadesEspeciais = this.necessidades.equalsIgnoreCase("S");

                System.out.print("Tipo de acompanhamento: ");
                this.acompanhamento = scanner.nextLine();
                if(this.acompanhamento.isEmpty()){
                    throw new AcompanhamentoInvalidoException("É obrigatório informar o tipo de acompanhamento.");
                }

                AlunoEspecial aluno = new AlunoEspecial(this.nome, this.idade, this.matricula, this.n1, this.n2, this.n3,
                        necessidadesEspeciais, this.acompanhamento);
                turma.adicionarAluno(aluno);
            } else {
                Aluno aluno = new Aluno(this.nome, this.idade, this.matricula, this.n1, this.n2, this.n3);
                turma.adicionarAluno(aluno);
            }

            System.out.println("Aluno adicionado com sucesso!");
            this.nome = "";
            this.idade = 0;
            this.matricula = "";
            this.n1 = 0.0;
            this.n2 = 0.0;
            this.n3 = 0.0;
            this.resposta = "";
            this.necessidades = "";
            this.acompanhamento = "";
            
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida para número!");
            this.n1 = 0.0;
            this.n2 = 0.0;
            this.n3 = 0.0;
            scanner.nextLine();
        } catch (NotaInvalidaException | IdadeInvalidaException | MatriculaVaziaException e) {
            System.out.println("Erro: " + e.getMessage());
            this.idade = 0;
        } catch (OpcaoInvalidaAlunoEspecialException e) {
            System.out.println(e.getMessage());
            this.resposta = "";
            adicionarAluno();
        } catch (OpcaoInvalidaNecessidadeEspecialException e) {
            System.out.println(e.getMessage());
            this.necessidades = "";
            adicionarAluno();
        } catch (AcompanhamentoInvalidoException e) {
            System.out.println(e.getMessage());
            this.acompanhamento = "";
            adicionarAluno();
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