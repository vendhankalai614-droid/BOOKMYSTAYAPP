import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    public boolean isRoomAvailable(String roomType) {
        return roomType.equalsIgnoreCase("Single") || roomType.equalsIgnoreCase("Double");
    }
}

class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isRoomAvailable(roomType)) {
            throw new InvalidBookingException("Invalid or unavailable room type");
        }
    }
}

class BookingRequestQueue {
    private List<String> bookings = new ArrayList<>();

    public void addRequest(String request) {
        bookings.add(request);
    }

    public List<String> getAllRequests() {
        return bookings;
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        System.out.println("Booking Validation:");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double): ");
            String roomType = scanner.nextLine();

            validator.validate(name, roomType, inventory);

            bookingQueue.addRequest(name + " - " + roomType);
            System.out.println("Booking successful");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
