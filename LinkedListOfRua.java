
public class LinkedListOfRua {
    // Classe interna Node
    private class Node {
        public Rua element;
        public Node next;
        public Node prev;
        public Node(Rua element) {
            this.element = element;
            next = null;
        }
    }

    // Referência para o primeiro elemento da lista encadeada.
    private Node header;
    // Referência para o último elemento da lista encadeada.
    private Node trailer;
    // Referencia para a posicao corrente.
    private Node current;      
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    
    /**
     * Construtor da lista.
     */
    public LinkedListOfRua() {
        header = new Node(null);
        trailer = new Node(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }    

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void orderedAdd (Rua element, Sinalizacao sinalizacao)  { 
        Node aux = containsElement(element.getS()); // verifica se ja contem element para não inserir duplicado
        if (aux == null) {  // se nao contem element, insere
            Node n = new Node(element);
            n.element.adicionarSinalizacao(sinalizacao);

            if (header.next == trailer) { 
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } 
            else if (element.getS().compareTo(header.next.element.getS())<0) { 
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            }
            else if (element.getS().compareTo(trailer.prev.element.getS())>0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            }
            else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu=false;
                while (aux!=trailer && !inseriu) {
                    if (element.getS().compareTo(aux.element.getS())<0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev=aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;

            return;
        }
        aux.element.adicionarSinalizacao(sinalizacao);
    }
    
    private Node containsElement(String s) {
        Node aux = header.next;
        
        while (aux != trailer) {
            if (aux.element.getS().equals(s)) {
                return aux;
            }
            aux = aux.next;
        }
        
        return null;
    }    
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Rua get(int index) { 
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = getNodeIndex(index);
        return aux.element;
    }
    
    // Metodo que tem como objetivo retornar uma referencia
    // para o nodo da posicao "index" recebida como parametro.
    // Por exemplo, se index for 2, ele retorna a referencia
    // para o nodo da posicao 2.
    private Node getNodeIndex(int index) {
        Node aux = null;
        if (index < count/2) { // caminha do inicio para o meio
            aux = header.next;
            for(int i=0; i<index; i++)
                aux = aux.next;
        }
        else { // caminha do fim para o meio
            aux = trailer.prev;
            for(int i=count-1; i>index; i--)
                aux = aux.prev;
        }
        return aux;
    }
    
    /**
     * Inicializa o current na primeira posicao (para percorrer do inicio para o fim).
     */
    public void reset() {
        current = header.next;
    }

    /**
     * Retorna o elemento da posicao corrente e faz current apontar para o proximo
     * elemento da lista.
     * @return elemento da posicao corrente
     */
    public Rua next() {
        if (current != trailer) {
            Rua rua = current.element;
            current = current.next;
            return rua;
        }
        return null;
    }         
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            s.append(aux.element);
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }    
    

}
