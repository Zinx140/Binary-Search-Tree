import java.util.ArrayList;

public class BST {
    ArrayList<Integer> lists;

    public BST(int root) {
        lists = new ArrayList<>();
        lists.add(root);
    }

    // Array direpresentasikan secara heap dimana:
    // index 0 -> merupakan root
    // left child nya adalah 2 * i + 1
    // right child adlh -> 2 * i + 2
    // Parent -> (i - 1) / 2

    // Jika leaf maka aku isi dengan -1

    public void insert(int new_number) {
        insert_recursive(0, new_number);
    }

    public void insert_recursive(int index, int new_number) {

        while (lists.size() <= index) {
            lists.add(-1);
        }

        if (lists.get(index) == -1) {
            lists.set(index, new_number);
            return;
        }
        if (new_number <= lists.get(index)) {
            insert_recursive(index * 2 + 1, new_number);
        }
        if (new_number > lists.get(index)) {
            insert_recursive(index * 2 + 2, new_number);
        }

    }

    public void find(int target) {
        find_recursive(target, 0);
    }

    public void find_recursive(int target, int index) {
        if (lists.get(index) == -1) {
            System.out.println("Number not found!");
            return;
        }

        if (lists.get(index) == target) {
            System.out.println("Number found");
            return;
        }

        if (target <= lists.get(index)) {
            find_recursive(target, index * 2 + 1);
        }

        if (target > lists.get(index)) {
            find_recursive(target, index * 2 + 2);
        }
    }

    public int findPredecessor(int index) {
        int left = index * 2 + 1;
        if (left >= lists.size() || lists.get(left) == -1) {
            return -1;
        }

        int current = left;
        while (true) {
            int right = current * 2 + 2;
            if (right < lists.size() && lists.get(right) != -1) {
                current = right;
            } else {
                break;
            }
        }
        return current;
    }

    public void remove(int target) {
        remove_recursive(target, 0);
    }

    public void remove_recursive(int target, int index) {
        if (index >= lists.size() || lists.get(index) == -1) {
            System.out.println("Number not found");
            return;
        }

        int current = lists.get(index);

        if (target < current) {
            remove_recursive(target, index * 2 + 1);
            return;
        }

        if (target > current) {
            remove_recursive(target, index * 2 + 2);
            return;
        }

        if (target == current) {

            int left = index * 2 + 1;
            int right = index * 2 + 2;

            boolean hasLeft = left < lists.size() && lists.get(left) != -1;
            boolean hasRight = right < lists.size() && lists.get(right) != -1;

            if (!hasLeft && !hasRight) {
                lists.set(index, -1);
                return;
            }

            if (hasLeft && !hasRight) {
                lists.set(index, lists.get(left));
                remove_recursive(lists.get(left), left);
                return;
            }

            if (!hasLeft && hasRight) {
                lists.set(index, lists.get(right));
                remove_recursive(lists.get(right), right);
                return;
            }

            int predIndex = findPredecessor(index);
            if (predIndex == -1) {
                System.out.println("Predecessor not found!");
                return;
            }

            int predValue = lists.get(predIndex);
            lists.set(index, predValue);
            remove_recursive(predValue, predIndex);
        }
    }

    public void print() {
        for (Integer integer : lists) {
            if (integer != -1) {
                System.out.print(integer + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }

}
