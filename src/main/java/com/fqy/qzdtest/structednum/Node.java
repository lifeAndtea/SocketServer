package com.fqy.qzdtest.structednum;

public class Node {

    private Object data;//数据域
    private Node next;//指针域

    public Node(Object data){
        this.data = data;
    }

    public Node(Object data,Node next){
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static String nodeToString(Node node){
        StringBuilder str = new StringBuilder(node.getData().toString() + "-> ");
        while (node.getNext()!=null){
            node = node.getNext();
            str.append(node.getData().toString() + "-> ");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Node aNew = createNew();
        System.out.println(nodeToString(aNew));
        Node node = reverseNode(aNew);
        System.out.println(nodeToString(node));
        Node node1 = reverseListNode(node);
        System.out.println(nodeToString(node1));
    }

    public static Node createNew(){
        Node node = new Node("1");
        Node node1 = new Node("2");
        node.setNext(node1);
        Node node2 = new Node("3");
        node1.setNext(node2);
        Node node3 = new Node("4");

        node2.setNext(node3);
        return node;
    }

    public static Node reverseNode(Node curnode){

        if (curnode==null||curnode.next==null){
            return curnode;
        }
        Node preNode = null;

        Node nextNode;

        while (curnode.next!=null){
            //System.out.println(curnode.getData());
            nextNode = curnode.getNext();
            curnode.setNext(preNode);
            preNode=curnode;
            curnode=nextNode;
        }
        curnode.setNext(preNode);
        return curnode;
    }

    public static Node reverseListNode(Node head){
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null){
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        //下一个节点指针
        Node nextNode = null;

        while (curNode != null){
            nextNode = curNode.getNext();//nextNode 指向下一个节点
            curNode.setNext(preNode);//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }

        return preNode;
    }
}