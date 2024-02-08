package tree;

import estrut.Tree;

public class BinarySearchTree implements Tree {
    private Node raiz;

    private class Node {
        int valor;
        Node esquerda, direita;

        public Node(int item) {
            valor = item;
            esquerda = direita = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRec(raiz, valor);
    }

    private boolean buscaElementoRec(Node no, int valor) {
        if (no == null)
            return false;

        if (valor == no.valor)
            return true;

        if (valor < no.valor)
            return buscaElementoRec(no.esquerda, valor);

        return buscaElementoRec(no.direita, valor);
    }

    @Override
    public int minimo() {
        if (raiz == null)
            throw new NullPointerException("Árvore vazia");
        
        Node minimoNode = encontraMinimo(raiz);
        return minimoNode.valor;
    }

    private Node encontraMinimo(Node no) {
        while (no.esquerda != null)
            no = no.esquerda;
        
        return no;
    }

    @Override
    public int maximo() {
        if (raiz == null)
            throw new NullPointerException("Árvore vazia");
        
        Node maximoNode = encontraMaximo(raiz);
        return maximoNode.valor;
    }

    private Node encontraMaximo(Node no) {
        while (no.direita != null)
            no = no.direita;
        
        return no;
    }

    @Override
    public void insereElemento(int valor) {
        raiz = insereElementoRec(raiz, valor);
    }

    private Node insereElementoRec(Node no, int valor) {
        if (no == null)
            return new Node(valor);
        
        if (valor < no.valor)
            no.esquerda = insereElementoRec(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = insereElementoRec(no.direita, valor);
        
        return no;
    }

    @Override
    public void remove(int valor) {
        raiz = removeRec(raiz, valor);
    }

    private Node removeRec(Node no, int valor) {
        if (no == null)
            return null;
        
        if (valor < no.valor)
            no.esquerda = removeRec(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = removeRec(no.direita, valor);
        else {
            if (no.esquerda == null)
                return no.direita;
            else if (no.direita == null)
                return no.esquerda;

            no.valor = minimoValor(no.direita);
            no.direita = removeRec(no.direita, no.valor);
        }
        return no;
    }

    private int minimoValor(Node node) {
        int minValor = node.valor;
        while (node.esquerda != null) {
            minValor = node.esquerda.valor;
            node = node.esquerda;
        }
        return minValor;
    }

    @Override
    public int[] preOrdem() {
        int[] resultado = new int[countNodes(raiz)];
        preOrdemRec(raiz, resultado, 0);
        return resultado;
    }

    private int preOrdemRec(Node no, int[] array, int indice) {
        if (no == null)
            return indice;
        
        array[indice++] = no.valor;
        indice = preOrdemRec(no.esquerda, array, indice);
        return preOrdemRec(no.direita, array, indice);
    }

    @Override
    public int[] emOrdem() {
        int[] resultado = new int[countNodes(raiz)];
        emOrdemRec(raiz, resultado, 0);
        return resultado;
    }

    private int emOrdemRec(Node no, int[] array, int indice) {
        if (no == null)
            return indice;
        
        indice = emOrdemRec(no.esquerda, array, indice);
        array[indice++] = no.valor;
        return emOrdemRec(no.direita, array, indice);
    }

    @Override
    public int[] posOrdem() {
        int[] resultado = new int[countNodes(raiz)];
        posOrdemRec(raiz, resultado, 0);
        return resultado;
    }

    private int posOrdemRec(Node no, int[] array, int indice) {
        if (no == null)
            return indice;
        
        indice = posOrdemRec(no.esquerda, array, indice);
        indice = posOrdemRec(no.direita, array, indice);
        array[indice++] = no.valor;
        return indice;
    }

    private int countNodes(Node node) {
        if (node == null)
            return 0;
        else
            return countNodes(node.esquerda) + 1 + countNodes(node.direita);
    }
}
