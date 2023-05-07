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

    public int getMesImplantadoMaisSinalizacao() {
        Node aux = head;
        int janeiro = 0;
        int fevereiro = 0;
        int marco = 0;
        int abril = 0;
        int maio = 0;
        int junho = 0;
        int julho = 0;
        int agosto = 0;
        int setembro = 0;
        int outubro = 0;
        int novembro = 0;
        int dezembro = 0;
        for (int i = 0; i < count; i++) {
            if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 1) {
                janeiro++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 2) {
                fevereiro++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 3) {
                marco++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 4) {
                abril++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 5) {
                maio++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 6) {
                junho++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 7) {
                julho++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 8) {
                agosto++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 9) {
                setembro++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 10) {
                outubro++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 11) {
                novembro++;
                aux = aux.next;
            } else if (aux.sinalizacao.getDataImplantacao().getMonthValue() == 12) {
                dezembro++;
                aux = aux.next;
            }

        }

        int maior = janeiro;

        for (int i = 0; i < 12; i++) {
            if (fevereiro > maior) {
                maior = fevereiro;
            } else if (marco > maior) {
                maior = marco;
            } else if (abril > maior) {
                maior = abril;
            } else if (maio > maior) {
                maior = maio;
            } else if (junho > maior) {
                maior = junho;
            } else if (julho > maior) {
                maior = julho;
            } else if (agosto > maior) {
                maior = agosto;
            } else if (setembro > maior) {
                maior = setembro;
            } else if (outubro > maior) {
                maior = outubro;
            } else if (novembro > maior) {
                maior = novembro;
            } else if (dezembro > maior) {
                maior = dezembro;
            }
        }

        for (int i = 0; i < 12; i++) {
            if (janeiro == maior) {
                return 1;
            } else if (fevereiro == maior) {
                return 2;
            } else if (marco == maior) {
                return 3;
            } else if (abril == maior) {
                return 4;
            } else if (maio == maior) {
                return 5;
            } else if (junho == maior) {
                return 6;
            } else if (julho == maior) {
                return 7;
            } else if (agosto == maior) {
                return 8;
            } else if (setembro == maior) {
                return 9;
            } else if (outubro == maior) {
                return 10;
            } else if (novembro == maior) {
                return 11;
            } else if (dezembro == maior) {
                return 12;
            }
        }

        return -1;

    }

}
