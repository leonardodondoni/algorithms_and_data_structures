
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Repositorio {
    private ListaDeRuas listaDeRuas;

    public Repositorio() {
        this.listaDeRuas = new ListaDeRuas();
    }

    public void leArquivo() throws ParseException {

        String linhas[] = new String[110000];
        int numLinhas = 0;

        Path filePath = Paths.get("dataEditado.csv");
        
        // Ler o arquivo
        try ( BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                linhas[numLinhas] = line;
                numLinhas++;
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo: %s", e.getMessage());
        }

        // Mude numLinhas para algum numero pequeno para executar testes mais rapidamente.
        // Ex:
        // for (int i = 0; i < 50; i++) {
        for (int i = 0; i < 50; i++) {
            String[] campos = linhas[i].split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String descricao = campos[1];

            LocalDate data = null;    
            if(!campos[4].equals("")) {        
                if(campos[4].contains("-"))
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                else
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                data = LocalDate.parse(campos[4], formatter);
            }
            
            double numInicial;
            if(campos[6].equals(""))
                numInicial = 0;
            else
                numInicial = Double.parseDouble(campos[6]);
            
            double numFinal;
            if(campos[7].equals(""))
                numFinal = 0;
            else
                numFinal = Double.parseDouble(campos[7]);
            
            String lado = campos[10];
            String localInstalacao = "";
            if(campos.length>=13)
                localInstalacao = campos[12];
            
            Sinalizacao sinalizacao = new Sinalizacao(
                descricao, data, numInicial, numFinal, lado, localInstalacao
            );

            this.listaDeRuas.orderedAdd(campos[5], sinalizacao);
        }
    }

    public String nomeDaRuaComMaisSinalizacoes() {
        return this.listaDeRuas.pesquisaRuaComMaisSinalizacao().getS();
    }
}