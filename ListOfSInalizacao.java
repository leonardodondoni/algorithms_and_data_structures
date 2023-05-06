public class ListOfSinalizacao {
    
    private class Node {
		public Sinalizacao element;
		public Node next;
        public Node(Sinalizacao element) {
            this.element = element;
            next = null;
        }
        public Node(Sinalizacao element, Node next) {
            this.element = element;
            this.next = next;
        }   
	}

    private Node head;
    private Node tail;
    private int count;

    public ListOfSinalizacao() {
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



}
