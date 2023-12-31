import java.util.ArrayList;
import java.util.List;

class Move {
	private List<Box> boxes;

    public Move(int capacity) {
        this.boxes = new ArrayList<>(capacity);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void print() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            printContents(box.getContents());
        }
    }

	public int find(String objectName) {
		for (Box box : boxes) {
			if (boxContainsObject(box, objectName)) {
				return box.getBoxNumber();
			}
		}
		return -1;
	}
	
	private boolean boxContainsObject(Box box, String objectName) {
		for (Object item : box.getContents()) {
			if (item instanceof SingleObject && ((SingleObject) item).getName().equals(objectName)) {
				return true;
			} else if (item instanceof Box && boxContainsObject((Box) item, objectName)) {
				return true;
			}
		}
		return false;
	}

	private void printContents(List<Object> contents) {
		for (Object item : contents) {
			if (item instanceof SingleObject) {
			System.out.print(((SingleObject) item).getName() + "\n");
			} 
			else if (item instanceof Box) {
				List<Object> subContents = ((Box) item).getContents();
				for (Object subItem : subContents) {
					if (subItem instanceof SingleObject) {
						System.out.print(((SingleObject) subItem).getName() + "\n");
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// We create a move that will hold 2 main boxes
		Move move = new Move(2);

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();

		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
	}
}