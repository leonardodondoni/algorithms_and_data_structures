
public class ListaDeRuas {
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
    public ListaDeRuas() {
        header = new Node(null);
        trailer = new Node(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * 
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos da lista.
     * 
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
     * 
     * @param element elemento a ser adicionado ao final da lista
     */
    public void orderedAdd(String element, Sinalizacao sinalizacao) {
        Node aux = containsElement(element.split(" ", 2)[1]); // verifica se ja contem element para não inserir duplicado
        if (aux == null) { // se nao contem element, insere
            Rua novaRua = new Rua(element);
            Node n = new Node(novaRua);
            n.element.adicionarSinalizacao(sinalizacao);

            if (header.next == trailer) {
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } else if (novaRua.getSNome().compareTo(header.next.element.getSNome()) < 0) {
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            } else if (novaRua.getSNome().compareTo(trailer.prev.element.getSNome()) > 0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            } else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu = false;
                while (aux != trailer && !inseriu) {
                    if (novaRua.getSNome().compareTo(aux.element.getSNome()) < 0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev = aux.prev;
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
            if (aux.element.getSNome().equals(s)) {
                return aux;
            }
            aux = aux.next;
        }

        return null;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * 
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
        if (index < count / 2) { // caminha do inicio para o meio
            aux = header.next;
            for (int i = 0; i < index; i++)
                aux = aux.next;
        } else { // caminha do fim para o meio
            aux = trailer.prev;
            for (int i = count - 1; i > index; i--)
                aux = aux.prev;
        }
        return aux;
    }

    /**
     * Inicializa o this.current na primeira posicao (para percorrer do inicio para o
     * fim).
     */
    public void reset() {
        this.current = header.next;
    }

    /**
     * Retorna o elemento da posicao corrente e faz this.current apontar para o proximo
     * elemento da lista.
     * 
     * @return elemento da posicao corrente
     */
    public Rua next() {
        if (this.current != trailer.prev) {
            Rua rua = this.current.element;
            this.current = this.current.next;
            return rua;
        }
        return null;
    }

    public Rua previous() {
        if (this.current != header.next) {
            Rua rua = this.current.element;
            this.current = this.current.prev;
            return rua;
        }

        return null;
    }

    public Rua getCurrentElement() {
        return this.current.element;
    }

    public Rua pesquisaRuaComMaisSinalizacao() {
        Node aux = header.next;
        Rua rua = header.next.element;
        for (int i = 0; i < count; i++) {
            if (aux.element.quantidadeSinalizacao() > rua.quantidadeSinalizacao()) {
                rua = aux.element;
            }
            aux = aux.next;
        }

        return rua;
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

    public String getMesMaisImplatacoesDeSinalizacoes() {
        Node aux = header.next;
        int[] mesesTotal = new int[12];

        for (int i = 0; i < count; i++) {
            int[] meses = aux.element.getSinalizacaoPorMes();

            mesesTotal[0] += meses[0];
            mesesTotal[1] += meses[1];
            mesesTotal[2] += meses[2];
            mesesTotal[3] += meses[3];
            mesesTotal[4] += meses[4];
            mesesTotal[5] += meses[5];
            mesesTotal[6] += meses[6];
            mesesTotal[7] += meses[7];
            mesesTotal[8] += meses[8];
            mesesTotal[9] += meses[9];
            mesesTotal[10] += meses[10];
            mesesTotal[11] += meses[11];

            aux = aux.next;
        }

        int maior = 0;
        int maiorMes = 0;
        for(int i = 0;i <= 11; i++) {
            if(mesesTotal[i] > maior){
                maior = mesesTotal[i];
                maiorMes = i;
            }
        }

        String mes = "O maior número de implantações foi de: " + mesesTotal[maiorMes] + ", no mês de: ";
        if (maiorMes == 0) {
            mes += "Janeiro";
        } else if (maiorMes == 1) {
            mes += "Fevereiro";
        } else if (maiorMes == 2) {
            mes += "Março";
        } else if (maiorMes == 3) {
            mes += "Abril";
        } else if (maiorMes == 4) {
            mes += "Maio";
        } else if (maiorMes == 5) {
            mes += "Junho";
        } else if (maiorMes == 6) {
            mes += "Julho";
        } else if (maiorMes == 7) {
            mes += "Agosto";
        } else if (maiorMes == 8) {
            mes += "Setembro";
        } else if (maiorMes == 9) {
            mes += "Outubro";
        } else if (maiorMes == 10) {
            mes += "Novembro";
        } else if (maiorMes == 11) {
            mes += "Dezembro";
        } else {
            return null;
        }

        return mes;

    }

}
