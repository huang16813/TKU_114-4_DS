class Equipment {
    private String id;
    private String name;
    private int availableCount;

    Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.isBlank()) ? "Unknown" : id;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
        this.availableCount = Math.max(0, availableCount);
    }

    boolean borrowOne() {
        if (availableCount <= 0) {
            return false;
        }
        availableCount--;
        return true;
    }

    void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return id + " " + name + " available=" + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment projector = new Equipment("E101", "Projector", 2);
        Equipment laptop = new Equipment("E102", "Laptop", 1);

        System.out.println("借用 Projector：" + projector.borrowOne());
        System.out.println("借用 Projector：" + projector.borrowOne());
        System.out.println("借用 Projector（庫存不足）：" + projector.borrowOne());
        System.out.println("借用 Laptop：" + laptop.borrowOne());
        laptop.returnItems(3);

        System.out.println(projector);
        System.out.println(laptop);
    }
}
