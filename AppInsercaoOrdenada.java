import java.util.Scanner;
import java.text.ParseException;

public class AppInsercaoOrdenada {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        ServicoDeSinalizacao servicoDeSinalizacao = new ServicoDeSinalizacao();
        servicoDeSinalizacao.lerArquivoComDados();

        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Apresentar o nome da rua/av/trav que tem mais sinalizações registradas");
            System.out
                    .println("2. Apresentar o mês em que mais foram implantadas mais sinalizações em uma rua/av/trav");
            System.out.println("3. Entrar em um modo de navegação");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println(servicoDeSinalizacao.getNomeRuaMaisSinalizacoes());
                    break;
                case 2:
                    System.out.println(servicoDeSinalizacao.getMesMaisImplatacoesDeSinalizacoes());
                    break;
                case 3:
                    servicoDeSinalizacao.iniciarModoNavegacao();
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
