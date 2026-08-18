import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = (warehouseId == null || warehouseId.isBlank()) ? "Unknown" : warehouseId;
        this.quantities = quantities == null
                ? new int[0]
                : Arrays.copyOf(quantities, quantities.length);
    }

    int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    int totalQuantity() {
        int total = 0;
        for (int quantity : quantities) {
            total += quantity;
        }
        return total;
    }

    int outOfStockCount() {
        int count = 0;
        for (int quantity : quantities) {
            if (quantity == 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return warehouseId + " " + Arrays.toString(quantities);
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        InventorySnapshot snapshot = new InventorySnapshot("WH01", new int[] {5, 0, 3, 0});

        System.out.println("總數量：" + snapshot.totalQuantity());
        System.out.println("缺貨品項：" + snapshot.outOfStockCount());

        InventorySnapshot nullSnapshot = new InventorySnapshot("WH02", null);
        System.out.println("null 陣列總數量：" + nullSnapshot.totalQuantity());
    }
}
