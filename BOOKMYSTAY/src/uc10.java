import java.util.*;

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 2);
    }

    public boolean allocateRoom(String type) {
        if (rooms.containsKey(type) && rooms.get(type) > 0) {
            rooms.put(type, rooms.get(type) - 1);
            return true;
        }
        return false;
    }

    public void releaseRoom(String type) {
        rooms.put(type, rooms.getOrDefault(type, 0) + 1);
    }

    public void display() {
        System.out.println(rooms);
    }
}

class CancellationService {
    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (reservationRoomTypeMap.containsKey(reservationId)) {
            String type = reservationRoomTypeMap.remove(reservationId);
            inventory.releaseRoom(type);
            releasedRoomIds.push(reservationId);
            System.out.println("Cancelled: " + reservationId);
        } else {
            System.out.println("Invalid reservation ID");
        }
    }

    public void showRollbackHistory() {
        System.out.println("Rollback History:");
        while (!releasedRoomIds.isEmpty()) {
            System.out.println(releasedRoomIds.pop());
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        if (inventory.allocateRoom("Single")) {
            service.registerBooking("R1", "Single");
        }

        if (inventory.allocateRoom("Double")) {
            service.registerBooking("R2", "Double");
        }

        inventory.display();

        service.cancelBooking("R1", inventory);
        service.cancelBooking("R2", inventory);

        inventory.display();

        service.showRollbackHistory();
    }
}
