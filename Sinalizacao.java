import java.time.LocalDate;

public class Sinalizacao {
    
    private String descricao;
    private LocalDate dataImplantacao;
    private double numInicial;
    private double numFinal;
    private String lado;
    private String localDeInstalacao;

    public Sinalizacao(String descricao, LocalDate dataImplantacao, double numInicial, double numFinal, String lado, String localDeInstalacao) {
        this.descricao = descricao;
        this.dataImplantacao = dataImplantacao;
        this.numInicial = numInicial;
        this.numFinal = numFinal;
        this.localDeInstalacao = localDeInstalacao;
        this.lado = lado;
        this.localDeInstalacao = localDeInstalacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataImplantacao() {
        return dataImplantacao;
    }

    public double getNumInicial() {
        return numInicial;
    }

    public double getNumFinal(){
        return numFinal;
    }

    public String getLado(){
        return lado;
    }

    public String localDeInstalacao(){
        return localDeInstalacao;
    }

    public String toString() {
        return "Sinalização{" +
                "descricao='" + descricao + '\'' +
                ", dataImplantacao=" + dataImplantacao +
                ", numInicial=" + numInicial +
                ", numFinal=" + numFinal +
                ", lado='" + lado + '\'' +
                ", localDeInstalacao='" + localDeInstalacao + '\'' +
                '}';
    }

}
