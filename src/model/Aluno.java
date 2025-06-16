package model;

public class Aluno {
    private String nome;
    private int idade;
    private String matricula;
    private double n1;
    private double n2;
    private double n3;

    public Aluno(String nome, int idade, String matricula, double n1, double n2, double n3) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    public double calcularMedia() {
        double menorNota = Math.min(n1, Math.min(n2, n3));
        double soma = n1 + n2 + n3 - menorNota;
        return soma / 2;
    }

    public String getSituacao() {
        double media = calcularMedia();
        return media >= 6 ? "Aprovado" : "Reprovado";
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public double getN3() {
        return n3;
    }

    public void setN3(double n3) {
        this.n3 = n3;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, Idade: %d, Matrícula: %s, Notas: %.1f, %.1f, %.1f, Média: %.1f, Situação: %s",
                nome, idade, matricula, n1, n2, n3, calcularMedia(), getSituacao());
    }
}