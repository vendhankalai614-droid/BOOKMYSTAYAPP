import java.util.*;

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {
        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service " + service.getName() +
                " to reservation " + reservationId);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<Service> services = servicesByReservation.get(reservationId);
        if (services == null) return 0.0;

        double total = 0.0;
        for (Service s : services) {
            total += s.getCost();
        }
        return total;
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES-101";

        Service spa = new Service("Spa", 50.0);
        Service breakfast = new Service("Breakfast", 20.0);
        Service airportPickup = new Service("Airport Pickup", 30.0);

        manager.addService(reservationId, spa);
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Total add-on cost for reservation "
                + reservationId + ": $" + totalCost);
    }
}
