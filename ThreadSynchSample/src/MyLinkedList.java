/**
 * Created by dinesh.k.masthaiah on 1/22/2018.
 */

public class MyLinkedList<T> {
    private Node<T> head;

    public MyLinkedList() {
        head = null;
    }

    public void remove(T object) {
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.object.equals(object)) {
                prev.next = temp.next;
                System.out.println("Item found. deleting..");
                temp.object = null;
                temp.next = null;
                temp = null;
                break;
            }
            prev = temp;
            temp = temp.next;
        }
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println("" + temp.object);
            temp = temp.next;
        }
    }

    public void add(T object) {
        Node temp = new Node();
        temp.object = object;
        temp.next = null;
        if (head == null) {
            head = temp;
            return;
        }
        Node temp1 = head;
        while (temp1.next != null) {
            temp1 = temp1.next;
        }
        temp1.next = temp;
        return;
    }

    public void insert(T object) {
        if (head == null) {
            add(object);
            return;
        }
        Node newNode = new Node();
        newNode.object = object;
        newNode.next = null;
        if (head.next == null) {
            head.next = newNode;
            return;
        }
        Node current = head;
        Node prev = null;
        while (current != null) {
            prev = current;
            current = current.next;
            if (current == null) {
                prev.next = newNode;
                return;
            }
            if (prev.object.hashCode() < newNode.object.hashCode() && current.object.hashCode() > newNode.object.hashCode()) {
                prev.next = newNode;
                newNode.next = current;
                return;
            }

        }

    }

    class Node<T> {
        T object;
        Node<T> next;
    }
}
