/**
 * @author MuratErbilici
 * @since 03.04.2023
 * LinkedList with LazyDeletion(LD).
 */


import java.util.*;


public class LDLinkedList<E> extends AbstractList<E> implements List<E>{
    private int size;
    private Node<E> head;
    private int deletednum;
    
    /**
     * inner class for nodes.
     * it is public because we need to use it in other classes to checking lazy deletion.
     */
    public class Node<E>{
        private E data;
        private boolean isdeleted;
        private Node<E> next;

        public Node(E data){
            this.data = data;
            this.isdeleted = false;
            this.next = null;
        }
        public boolean getisdeleted(){
            return isdeleted;
        }
    }

    public LDLinkedList(){
        size = 0;
        head = null;
        deletednum = 0;
    }

    public void add(int index, E obj){
        Node<E> newnode = new Node<E>(obj);
        if(index==0){
            head = newnode;
            head.next = null;
        }
        else if(index==size){
            getNode(size-1).next = newnode;
            newnode.next = null;
        }
        else{
            newnode.next = getNode(index-1).next;
            getNode(index-1).next = newnode;
        }
        ++size;
    }
    public boolean add(E obj){
        Node<E> newnode = new Node<E>(obj);
        this.add(size, obj);
        return true;
    }
    public E get(int index){
        if(index<0 && index >=size){
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        return getNode(index).data;
    }
    public int size(){
        return size;
    }
    public Node<E> getNode(int index){
        Node<E> node = head;
        for(int i=0;i<index;++i){
            node = node.next;
        }
        return node;
    }

    /**
     * After one removing operation, object that we 
     * want to remove is only marked, variable(deletednum) that keeps the numbers of marked object is 
     * increased by one. As we are removing another object secondly, invoked removeperm function to 
     * delete these two nodes physically.
     */
    public boolean remove(Object obj){
        Node<E> node = head;
        for(int i=0;i<size;++i){
            if(node.data==obj && node.isdeleted==false){
                if(deletednum==1){
                    node.isdeleted = true;
                    removeperm();
                //    System.out.println("      remove perm \n");
                    deletednum=0;
                    return true;
                }
                else{
                    deletednum=1;
                //    System.out.printf("       lazy deletion \n");
                    node.isdeleted=true;
                    return true;
                }
            }
            node = node.next;
        }
        return false;

    }

    private void removeperm(){
        int newSize = size;
        Node<E> node = head;
        Node<E> prenode = head;

        for(int i=0;i<size;++i){
            if(node.isdeleted == true){
                if(node == head){
                    head = node.next;
                    node = head;
                    --newSize;
                }
                else{
                    prenode.next = node.next;
                    --newSize;
                }
            }
            else{
            prenode = node;
            node = node.next;
            }
        }
        size = newSize;
    }
    int getdeletednum(){
        return deletednum;
    }
    

}