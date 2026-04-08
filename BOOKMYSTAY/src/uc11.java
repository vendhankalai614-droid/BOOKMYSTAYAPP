import java.util.*;

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 1);
    }

    public boolean allocateRoom(String type) {
        if (rooms.getOrDefault(type, 0) > 0) {
            rooms.put(type, rooms.get(type) - 1);
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println(rooms);
    }
}

class BookingRequestQueue {
    private Queue<String> queue = new LinkedList<>();

    public void addRequest(String request) {
        queue.add(request);
    }

    public String getRequest() {
        return queue.poll();
    }
}

class AllocationService {
    public void allocateRoom(String reservation, RoomInventory inventory) {
        if (inventory.allocateRoom("Single")) {
            System.out.println(Thread.currentThread().getName() + " booked " + reservation);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed " + reservation);
        }
    }
}

class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private AllocationService allocationService;

    public ConcurrentBookingProcessor(BookingRequestQueue queue, RoomInventory inventory, AllocationService allocationService) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    public void run() {
        String reservation;
        while ((reservation = queue.getRequest()) != null) {
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        AllocationService allocationService = new AllocationService();

        bookingQueue.addRequest("R1");
        bookingQueue.addRequest("R2");

        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        inventory.display();
    }
}
