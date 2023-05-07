import java.time.LocalDate;

public class ListaDeSinalizacoes {

    private class Node {
        public Sinalizacao sinalizacao;
        public Node next;

        public Node(Sinalizacao sinalizacao) {
            this.sinalizacao = sinalizacao;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public ListaDeSinalizacoes() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(Sinalizacao element) {
        Node n = new Node(element);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
    }

    public int size() {
        return count;
    }

    public int getMes(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count - 1)
            return tail.sinalizacao.getDataImplantacao().getMonthValue();
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return aux.sinalizacao.getDataImplantacao().getMonthValue();
    }

    public LocalDate getDataImplantacao(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count - 1)
            return tail.sinalizacao.getDataImplantacao();
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return aux.sinalizacao.getDataImplantacao();
    }

    public int[] totalSinalizacoesMes() {
        Node aux = head;
        int[] meses = new int[11];
        for (int i = 0; i < count; i++) {
            if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 1) {
                meses[0]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 2) {
                meses[1]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 3) {
                meses[2]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 4) {
                meses[3]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 5) {
                meses[4]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 6) {
                meses[5]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 7) {
                meses[6]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 8) {
                meses[7]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 9) {
                meses[8]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 10) {
                meses[9]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 11) {
                meses[10]++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 12) {
                meses[11]++;
                aux = aux.next;
            }
        }
        return meses;

    }

    public Sinalizacao getUltimaImplantacao() {
        Sinalizacao sinalizacao = head.sinalizacao;
        Node aux = head;
        int c = 0;
        while (c <= count) {
            if (aux.sinalizacao.getDataImplantacao().isAfter(sinalizacao.getDataImplantacao())) {
                sinalizacao = aux.sinalizacao;
            }
            aux = aux.next;
            c++;
        }

        return sinalizacao;
    }

    public Sinalizacao getPrimeiraImplantacao() {
        Sinalizacao sinalizacao = head.sinalizacao;
        Node aux = head;
        int c = 0;
        while (c <= count) {
            if (aux.sinalizacao.getDataImplantacao().isBefore(sinalizacao.getDataImplantacao())) {
                sinalizacao = aux.sinalizacao;
            }
            aux = aux.next;
            c++;
        }

        return sinalizacao;
    }
}
