public class Rua {
    private String s;
    private String sId;
    private String sNome;

    public Rua(String s) {
        this.s = s;
        this.sId = s . split (" ", 2) [0];
        this.sNome = s . split (" ", 2) [1];
    }
}
