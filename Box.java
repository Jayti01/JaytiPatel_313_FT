import java.util.ArrayList;
import java.util.List;

public class Box {
    private List<Object> contents;
    private int boxNumber;

    public Box(int boxCapacity, int boxNumber) {
        this.contents = new ArrayList<>(boxCapacity);
        this.boxNumber = boxNumber;
    }

    public void addItem(Object item) {
        contents.add(item);
    }

    public List<Object> getContents() {
        return contents;
    }

    public int getBoxNumber() {
        return boxNumber;
    }
}
