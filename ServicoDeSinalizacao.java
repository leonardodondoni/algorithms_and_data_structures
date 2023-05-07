
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ServicoDeSinalizacao {
    private ListaDeRuas listaDeRuas;
    private Scanner scanner;

    public ServicoDeSinalizacao() {
        this.scanner = new Scanner(System.in);
        this.listaDeRuas = new ListaDeRuas();
    }

    public void lerArquivoComDados() throws ParseException {

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

    public String getNomeRuaMaisSinalizacoes() {
        return this.listaDeRuas.pesquisaRuaComMaisSinalizacao().getS();
    }

    public String getMesMaisImplatacoesDeSinalizacoes() {
        return this.listaDeRuas.getMesMaisImplatacoesDeSinalizacoes();
    }

    public void iniciarModoNavegacao() {
        this.listaDeRuas.reset();
        
        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Avançar para o próximo elemento");
            System.out.println("2. Voltar para o elemento anterior");
            System.out.println("3. Mostrar o elemento atual");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Rua proximoElemento = this.listaDeRuas.next();
                    if (proximoElemento != null) {
                        System.out.println("Avançando para o próximo elemento...");
                    } else {
                        System.out.println("Você já está no último elemento.");
                    }
                    break;
                case 2:
                    Rua elementoAnterior = this.listaDeRuas.previous();
                    if (elementoAnterior != null) {
                        System.out.println("Voltando para o elemento anterior...");
                    } else {
                        System.out.println("Você já está no primeiro elemento.");
                    }
                    break;
                case 3:
                    Rua rua = this.listaDeRuas.getCurrentElement();
                    System.out.println("Rua: " + rua.getS());
                    System.out.println("Número total de sinalizações da rua: " + rua.quantidadeSinalizacao());
                    System.out.println("Primeira sinalização registrada: " + rua.getPrimeiraImplantacao().getDescricao());
                    System.out.println("Última sinalização registrada: " + rua.getUltimaImplantacao().getDescricao());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }
}
