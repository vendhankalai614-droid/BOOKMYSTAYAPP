class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("=== Booking Report ===");

        for (String booking : history.getBookings()) {
            System.out.println(booking);
        }

        System.out.println("Total Bookings: " + history.getBookings().size());
    }
}

class BookingHistory {
    private java.util.List<String> bookings = new java.util.ArrayList<>();

    public void addBooking(String booking) {
        bookings.add(booking);
    }

    public java.util.List<String> getBookings() {
        return bookings;
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addBooking("Booking 1: Room A");
        history.addBooking("Booking 2: Room B");
        history.addBooking("Booking 3: Room C");

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}
