public class App {
    public static void main(String[] args) throws Exception {
        BST bst = new BST(10);
        bst.insert(1);
        bst.insert(12);
        bst.insert(13);
        bst.insert(3);
        bst.insert(11);
        bst.print();
        bst.find(1);
        bst.remove(0);
        bst.remove(1);
        bst.print();
    }
}
