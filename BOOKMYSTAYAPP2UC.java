import java.util.HashMap;

/**
 * Hotel Booking System
 * UC1 - Entry Point
 * UC2 - Room Display
 * UC3 - Inventory Management
 */
public class HotelApp {

    // UC2: Abstract Room
    static abstract class Room {
        String type;
        int price;
        abstract void display();
    }

    static class SingleRoom extends Room {
        SingleRoom() { type = "Single Room"; price = 1000; }
        void display() { System.out.println(type + " - Price: " + price); }
    }

    static class DoubleRoom extends Room {
        DoubleRoom() { type = "Double Room"; price = 2000; }
        void display() { System.out.println(type + " - Price: " + price); }
    }

    static class SuiteRoom extends Room {
        SuiteRoom() { type = "Suite Room"; price = 5000; }
        void display() { System.out.println(type + " - Price: " + price); }
    }

    // UC3: Inventory using HashMap
    static class RoomInventory {
        HashMap<String, Integer> map = new HashMap<>();

        void addRoom(String type, int count) {
            map.put(type, count);
        }

        void showInventory() {
            for (String key : map.keySet()) {
                System.out.println(key + " Rooms: " + map.get(key));
            }
        }
    }

    // UC1: Main Method
    public static void main(String[] args) {

        // UC1 - Welcome Message
        System.out.println("Welcome to Hotel Booking System v1.0");

        // UC2 - Room Creation & Display
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        r1.display();
        r2.display();
        r3.display();

        // UC3 - Inventory Management
        RoomInventory inv = new RoomInventory();
        inv.addRoom("Single", 5);
        inv.addRoom("Double", 3);
        inv.addRoom("Suite", 2);

        System.out.println("\nRoom Availability:");
        inv.showInventory();
    }
}
