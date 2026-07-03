import java.util.List;

public class LibraryUtils {

    // Generic method voi Bounded Type Parameter: T phai co the so sanh duoc
    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("Mang khong duoc rong!");
        T min = array[0];
        for (T item : array) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }

    // Unbounded Wildcard: chap nhan List cua bat ky kieu nao, chi de doc/in
    public static void printAll(List<?> list) {
        for (Object item : list) {
            System.out.println("  - " + item);
        }
    }

    // PECS: Producer Extends, Consumer Super
    // src la "producer" (chi doc ra) -> dung ? extends T
    // dest la "consumer" (chi ghi vao) -> dung ? super T
    public static <T> void copyList(List<? super T> dest, List<? extends T> src) {
        for (T item : src) {
            dest.add(item);
        }
    }
}
