package Tree;

import java.util.NoSuchElementException;

public class BST <T extends Comparable<T>> implements Dict<T> {
    private class Node <T extends Comparable<T>> implements Dict<T> {
        private Node<T> left, right, parent;
        private T data;
        Node(Node <T> _left, Node <T> _right,Node <T> _parent, T _data){
            left=_left;
            right=_right;
            parent=_parent;
            data=_data;
        }
        @Override
        public String toString() {
            String result="";
            if(this.data==null)
                return result;
            result+=left.toString();
            result+="Node("+data.toString()+") ";
            result+=right.toString();
            return result;
        }
        public boolean search(T searchingValue){
            if(data==null)
                return false;
            if(data.compareTo(searchingValue)==0)
                return true;
            return left.search(searchingValue) || right.search(searchingValue);
        }
        public boolean insert(T insertingValue){
            if(data==null){
                this.data = insertingValue;
                this.left = new Node<T>(null, null, this, null);
                this.right = new Node<T>(null, null, this, null);
                return true;
            }
            if(data.compareTo(insertingValue)==0)
                return false;
            if(data.compareTo(insertingValue)>0){
                if(left.data==null) {
                    left.data = insertingValue;
                    left.left = new Node<T>(null, null, this, null);
                    left.right = new Node<T>(null, null, this, null);
                    return true;
                }
                return left.insert(insertingValue);
            }
            if(right==null) {
                right.data = insertingValue;
                right.left = new Node<T>(null, null, this, null);
                right.right = new Node<T>(null, null, this, null);
                return true;
            }
            return right.insert(insertingValue);
        }
        public boolean remove(T removingValue){
            if(this.data==null)
                return false;
            if(data.compareTo(removingValue)==0) {
                if (left.data==null) {
                    this.data=right.data;
                    if (right.data != null) {
                        right.left.parent=this;
                        right.right.parent=this;
                    }
                    this.left=right.left;
                    this.right=right.right;
                    return true;
                }
                else if (right.data==null) {
                    this.data=left.data;
                    left.left.parent=this;
                    left.right.parent=this;
                    this.right=left.right;
                    this.left=left.left;
                    return true;
                }
                Node<T> prevLeftestInRightNode = this;
                Node<T> currLeftestInRightNode = right;
                while (currLeftestInRightNode.left.data != null) {
                    prevLeftestInRightNode = currLeftestInRightNode;
                    currLeftestInRightNode = currLeftestInRightNode.left;
                }
                this.data = currLeftestInRightNode.data;
                if (prevLeftestInRightNode != this)
                    prevLeftestInRightNode.left = currLeftestInRightNode.right;
                else
                    this.right = currLeftestInRightNode.right;
                currLeftestInRightNode.right.parent = prevLeftestInRightNode;
                return true;
            }
            return left.remove(removingValue) || right.remove(removingValue);
        }
        public T min(){
            if (this.data==null)
                return null;
            if(left.data==null)
                return this.data;
            return left.min();
        }
        public T max(){
            if (this.data==null)
                return null;
            if(right.data==null)
                return this.data;
            return right.max();
        }
    }
    private int elemCount = 0;
    Node<T> root = null;

    public BST(T value){
        root = new Node<T>(new Node<T>(null, null, root, null),
                new Node<T>(null, null, root, null),
                null, value);
        elemCount = 1;
    }
    public BST(){
        root = new Node<T>(null, null, null, null);
        elemCount = 0;
    }
    public boolean search(T searchingValue) throws NullPointerException{
        return root.search(searchingValue);
    }
    public boolean insert(T insertingValue){
        if(insertingValue == null)
            throw new NullPointerException("Proba wstawic puste znaczenie");
        boolean res = root.insert(insertingValue);
        if(res)
            elemCount++;
        return res;
    }
    public boolean remove(T removingValue){
        boolean res = root.remove(removingValue);
        if(res)
            elemCount--;
        return res;
    }

    public T min(){
        return root.min();
    }
    public T max(){
        return root.max();
    }

    public String toString () {
        return root.toString();
    }
    public int size(){
        return elemCount;
    }
    public void clear(){
        root = new Node<T>(null, null, null, null);
        elemCount = 0;
    }
}
