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

    public void add(Sinalizacao element)  {
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
        if (index == count-1)
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
        if (index == count-1)
            return tail.sinalizacao.getDataImplantacao();
        Node aux = head;
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return aux.sinalizacao.getDataImplantacao();
    }

    



}
