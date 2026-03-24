public class bookmystayapp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 2.1 ");
        System.out.println("=====================================");

        // Creating room objects (Polymorphism)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        // Display details
        System.out.println("\n--- Room Details & Availability ---\n");

        singleRoom.displayRoomDetails();
        System.out.println("Availability    : " + singleRoomAvailability);
        System.out.println("-------------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Availability    : " + doubleRoomAvailability);
        System.out.println("-------------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("Availability    : " + suiteRoomAvailability);
        System.out.println("-------------------------------------");

        System.out.println("\nApplication execution completed.");
    }
}
