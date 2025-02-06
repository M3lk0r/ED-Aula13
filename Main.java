import java.util.Random;

class No {
    int valor;
    No anterior;
    No proximo;

    public No(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    private No inicio;
    private No fim;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    public void inserirOrdenado(int valor) {
        No novoNo = new No(valor);
        if (inicio == null) {
            inicio = fim = novoNo;
        } else if (valor <= inicio.valor) {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        } else if (valor >= fim.valor) {
            novoNo.anterior = fim;
            fim.proximo = novoNo;
            fim = novoNo;
        } else {
            No atual = inicio;
            while (atual != null && atual.valor < valor) {
                atual = atual.proximo;
            }
            if (atual != null) {
                novoNo.proximo = atual;
                novoNo.anterior = atual.anterior;
                if (atual.anterior != null) {
                    atual.anterior.proximo = novoNo;
                }
                atual.anterior = novoNo;
            }
        }
    }

    public void imprimirCrescente() {
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void imprimirDecrescente() {
        No atual = fim;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    public void removerPrimos() {
        No atual = inicio;
        while (atual != null) {
            No proximo = atual.proximo;
            if (ehPrimo(atual.valor)) {
                if (atual.anterior == null) {
                    inicio = atual.proximo;
                    if (inicio != null) {
                        inicio.anterior = null;
                    }
                } else if (atual.proximo == null) {
                    fim = atual.anterior;
                    if (fim != null) {
                        fim.proximo = null;
                    }
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
            }
            atual = proximo;
        }
    }

    private boolean ehPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] vetor = new int[1000];
        Random random = new Random();
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(19999) - 9999;
        }

        System.out.println("\n");

        for (int num : vetor) {
            lista.inserirOrdenado(num);
        }

        System.out.println("Lista em ordem crescente:");
        lista.imprimirCrescente();

        System.out.println("\nLista em ordem decrescente:");
        lista.imprimirDecrescente();

        lista.removerPrimos();

        System.out.println("\nLista após remoção dos números primos:");
        lista.imprimirCrescente();
    }
}