/*
Assumptions:
    • The parking lot has multiple levels. Each level has multiple rows of spots.
    • The parking lot can park motorcycles, cars, and buses.
    • The parking lot has motorcycle spots, compact spots, and large spots.
    • A motorcycle can park in any spot.
    • A car can park in either a single compact spot or a single large spot.
    • A bus can park in five large spots that are consecutive and within the same row. It cannot park in small spots.
*/
public class ParkingLot {
    private Level[] levels;
    private final int NUM_LEVELS = 5;

    public ParkingLot() {
        levels = new Level[NUM_LEVELS];
        for (int i = 0; i < NUM_LEVELS; i++) {
            levels[i] = new Level(i, 30);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].parkVehicle(vehicle))
                return true;
        }
        return false;
    }
}