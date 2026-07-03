import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("########## BAI 1: GenericStack<T> ##########");
        testBai1();

        System.out.println();
        System.out.println("########## BAI 2: Pair<K, V> ##########");
        testBai2();

        System.out.println();
        System.out.println("########## BAI 3: LibraryUtils (Bounded + Wildcard + PECS) ##########");
        testBai3();
    }

    // ---------- Bai 1 ----------
    static void testBai1() {
        // Stack<String> luu lich su hanh dong
        GenericStack<String> historyStack = new GenericStack<>();
        historyStack.push("Muon sach B001");
        historyStack.push("Tra sach B002");
        historyStack.push("Cap nhat doc gia R001");

        System.out.println("Dinh ngan xep (peek): " + historyStack.peek());

        System.out.println("Pop lan 1: " + historyStack.pop());
        System.out.println("Pop lan 2: " + historyStack.pop());
        System.out.println("Con lai trong stack: " + historyStack.size() + " phan tu");

        try {
            GenericStack<String> emptyStack = new GenericStack<>();
            emptyStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Pop stack rong -> nem EmptyStackException nhu mong doi");
        }

        // Stack<Integer> luu ma loi
        GenericStack<Integer> errorStack = new GenericStack<>();
        errorStack.push(100);
        errorStack.push(200);
        errorStack.push(404);

        System.out.println("Danh sach ma loi (tu dinh xuong day):");
        while (!errorStack.isEmpty()) {
            System.out.println("  - " + errorStack.pop());
        }
    }

    // ---------- Bai 2 ----------
    static void testBai2() {
        Pair<String, Integer> book1 = new Pair<>("B001", 15);
        Pair<String, Integer> book2 = new Pair<>("B002", 8);

        System.out.println("Pair 1: " + book1);
        System.out.println("Pair 2: " + book2);

        boolean book1CaoHon = Pair.comparePairs(book1, book2);
        Pair<String, Integer> caoHon = book1CaoHon ? book1 : book2;
        System.out.println("Pair co so luot muon cao hon: " + caoHon);

        Pair<String, String> readerCard = new Pair<>("Nguyen Van A", "TC001");
        System.out.println("Truoc khi swap: " + readerCard);

        Pair<String, String> swapped = Pair.swap(readerCard);
        System.out.println("Sau khi swap:   " + swapped);
    }

    // ---------- Bai 3 ----------
    static void testBai3() {
        // findMin voi Book[] (Book implements Comparable<Book> theo Title)
        Book[] books = {
                new Book("B003", "The Pragmatic Programmer", "David Thomas"),
                new Book("B001", "Clean Code", "Robert Martin"),
                new Book("B002", "Design Patterns", "GoF")
        };
        Book minBook = LibraryUtils.findMin(books);
        System.out.println("Sach dung dau bang chu cai (Title): " + minBook);

        // findMin voi Reader[] (Reader implements Comparable<Reader> theo Name)
        Reader[] readers = {
                new Reader("R002", "Tran Thi B"),
                new Reader("R001", "Nguyen Van A"),
                new Reader("R003", "Le Van C")
        };
        Reader minReader = LibraryUtils.findMin(readers);
        System.out.println("Doc gia dung dau bang chu cai (Name): " + minReader);

        // printAll voi Unbounded Wildcard
        System.out.println("In toan bo danh sach Book:");
        List<Book> bookList = new ArrayList<>(List.of(books));
        LibraryUtils.printAll(bookList);

        System.out.println("In toan bo danh sach Reader:");
        List<Reader> readerList = new ArrayList<>(List.of(readers));
        LibraryUtils.printAll(readerList);

        // copyList the hien nguyen tac PECS
        // src: List<? extends T> la "producer" -- chi doc ra
        // dest: List<? super T> la "consumer" -- chi ghi vao
        List<Object> generalStorage = new ArrayList<>();
        LibraryUtils.copyList(generalStorage, bookList);   // copy Book vao List<Object>
        LibraryUtils.copyList(generalStorage, readerList); // copy Reader vao List<Object>

        System.out.println("Kho luu tru chung sau khi copyList (" + generalStorage.size() + " phan tu):");
        LibraryUtils.printAll(generalStorage);
    }
}
