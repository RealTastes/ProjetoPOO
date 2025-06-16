package model;

import java.util.List;

public class Turma {
    private Persistencia persistencia;

    public Turma() {
        this.persistencia = new Persistencia();
    }

    public void adicionarAluno(Aluno aluno) {
        persistencia.adicionarAluno(aluno);
    }

    public List<Aluno> listarAlunos() {
        return persistencia.listarAlunos();
    }
}