package Tree;

public class bstMain {
    public static void main(String[] args){
        BST<Integer> bstInt = new BST<>(12);
        System.out.println("Insert 4: "+bstInt.insert(4));
        System.out.println("Insert 7: "+bstInt.insert(7));
        System.out.println("Tree: "+bstInt);
        System.out.println("Max in tree: "+bstInt.max());
        System.out.println("Min in tree: "+bstInt.min());
        System.out.println("Insert 7: "+bstInt.insert(7));
        System.out.println("Insert 9: "+bstInt.insert(9));
        System.out.println("Insert 8: "+bstInt.insert(8));
        System.out.println("Tree: "+bstInt+"\tSize: "+bstInt.size());
        System.out.println("Remove 9: "+bstInt.remove(9));
        System.out.println("Tree: "+bstInt+"\tSize: "+bstInt.size());
        bstInt.clear();
        System.out.println("Tree: "+bstInt+"\tSize: "+bstInt.size());
        BST<String> bstStr = new BST<>("eeee");
        bstStr.insert("zz");
        bstStr.insert("a");
        bstStr.insert("cc");
        bstStr.insert("dd");
        bstStr.insert("d");
        bstStr.insert("kk");
        bstStr.insert("rrrr");
        bstStr.insert("e");
        System.out.println("Tree2: "+bstStr+"\tSize: "+bstStr.size());
        System.out.println("Remove 'rrrr': "+bstStr.remove("rrrr"));
        System.out.println("Tree2: "+bstStr+"\tSize: "+bstStr.size());
    }
}
