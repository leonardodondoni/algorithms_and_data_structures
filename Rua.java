public class Rua {
    private String s;
    private String sId;
    private String sNome;
    private ListaDeSinalizacoes listOfSinalizacao;

    public Rua(String s) {
        this.s = s;
        this.sId = s.split(" ", 2)[0];
        this.sNome = s.split(" ", 2)[1];
        this.listOfSinalizacao = new ListaDeSinalizacoes();
    }

    public String getS() {
        return this.s;
    }

    public String getSNome() {
        return this.sNome;
    }

    public void adicionarSinalizacao(Sinalizacao sinalizacao) {
        this.listOfSinalizacao.add(sinalizacao);
    }

    public int quantidadeSinalizacao() {
        return listOfSinalizacao.size();
    }

    public int[] getSinalizacaoPorMes() {
        return this.listOfSinalizacao.totalSinalizacoesMes();
    }

    public Sinalizacao getUltimaImplantacao() {
        return this.listOfSinalizacao.getUltimaImplantacao();
    }

    public Sinalizacao getPrimeiraImplantacao() {
        return this.listOfSinalizacao.getPrimeiraImplantacao();
    }

    @Override
    public String toString() {
        return "MinhaClasse{" +
                "s='" + s + '\'' +
                ", sId='" + sId + '\'' +
                ", sNome='" + sNome + '\'' +
                '}';
    }
}
