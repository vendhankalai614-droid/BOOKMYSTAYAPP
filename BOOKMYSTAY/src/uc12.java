import java.io.*;
import java.util.*;

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public void setRoom(String type, int count) {
        rooms.put(type, count);
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void display() {
        System.out.println(rooms);
    }
}

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getRooms().entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                inventory.setRoom(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading file");
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        inventory.setRoom("Single", 2);
        inventory.setRoom("Double", 3);

        service.saveInventory(inventory, "inventory.txt");

        RoomInventory newInventory = new RoomInventory();
        service.loadInventory(newInventory, "inventory.txt");

        newInventory.display();
    }
}
