package model;

public class AlunoEspecial extends Aluno {
    private boolean necessidadesEspeciais;
    private String tipoAcompanhamento;

    public AlunoEspecial(String nome, int idade, String matricula, double n1, double n2, double n3,
                         boolean necessidadesEspeciais, String tipoAcompanhamento) {
        super(nome, idade, matricula, n1, n2, n3);
        this.necessidadesEspeciais = necessidadesEspeciais;
        this.tipoAcompanhamento = tipoAcompanhamento;
    }

    @Override
    public String getSituacao() {
        double media = calcularMedia();
        if (necessidadesEspeciais && media >= 5) {
            return "Aprovado com Acompanhamento";
        }
        return super.getSituacao();
    }

    public void realizarAcompanhamento() {
        System.out.println("Realizando acompanhamento especial do tipo: " + tipoAcompanhamento);
    }

    public boolean isNecessidadesEspeciais() {
        return necessidadesEspeciais;
    }

    public void setNecessidadesEspeciais(boolean necessidadesEspeciais) {
        this.necessidadesEspeciais = necessidadesEspeciais;
    }

    public String getTipoAcompanhamento() {
        return tipoAcompanhamento;
    }

    public void setTipoAcompanhamento(String tipoAcompanhamento) {
        this.tipoAcompanhamento = tipoAcompanhamento;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Necessidades Especiais: %s, Acompanhamento: %s",
                necessidadesEspeciais ? "Sim" : "Não", tipoAcompanhamento);
    }
}