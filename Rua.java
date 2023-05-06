public class Rua {
    private String s;
    private String sId;
    private String sNome;
    private ListOfSinalizacao listOfSinalizacao;

    public Rua(String s) {
        this.s = s;
        this.sId = s . split (" ", 2) [0];
        this.sNome = s . split (" ", 2) [1];
        this.listOfSinalizacao = new ListOfSinalizacao();
    }

    public String getS() {
        return this.s;
    }

    public void adicionarSinalizacao(Sinalizacao sinalizacao) {
        this.listOfSinalizacao.add(sinalizacao);
    }
}
