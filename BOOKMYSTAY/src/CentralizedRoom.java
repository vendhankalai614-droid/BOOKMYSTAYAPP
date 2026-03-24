import java.util.HashMap;
import java.util.Map;

// Inventory Management Class
class RoomInventory {

    // Centralized storage for room availability
    private Map<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Method to get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update availability (increase/decrease)
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Error: Cannot reduce below zero for " + roomType);
        } else {
            inventory.put(roomType, updated);
        }
    }

    // Method to display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type    : " + entry.getKey());
            System.out.println("Availability : " + entry.getValue());
            System.out.println("-------------------------------------");
        }
    }
}

// Main Application Class
public class CentralizedRoom {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 3.1 ");
        System.out.println("=====================================");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate updates
        System.out.println("\nUpdating Inventory...\n");

        inventory.updateAvailability("Single Room", -1); // Booking
        inventory.updateAvailability("Double Room", -2); // Booking
        inventory.updateAvailability("Suite Room", 1);   // Cancellation

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication execution completed.");
    }
}
