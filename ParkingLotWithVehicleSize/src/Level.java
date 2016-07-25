public class Level {
    private int floor;
    private ParkingSpot[] parkingSpots; // number of spots in each level,
                                        // including large, compact and
                                        // motorcycle size
    private int availableSpots = 0; // number of free spots
    private static final int SPOT_PER_ROW = 10;

    public Level(int floor, int numberSpots) {
        this.floor = floor;
        availableSpots = numberSpots;
        parkingSpots = new ParkingSpot[numberSpots];

        int largeSpots = numberSpots / 4;
        int motorcycleSpots = numberSpots / 4;
        int compactSpots = numberSpots - largeSpots - motorcycleSpots;

        // Create the parking spots
        for (int i = 0; i < numberSpots; i++) {
            VehicleSize size = VehicleSize.Motorcycle;
            if (i < largeSpots) {
                size = VehicleSize.Large;
            } else if (i < largeSpots + compactSpots) {
                size = VehicleSize.Compact;
            }
            int row = i / SPOT_PER_ROW;
            parkingSpots[i] = new ParkingSpot(this, row, i, size);
        }
    }

    public int availableSpots() {
        return availableSpots;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        // Find a place to park this vehicle, return false if failed
        if (availableSpots() < vehicle.getSpotsNeeded())
            return false;
        
        // spotNumber is the start index of the place needed to park the car
        int spotNumber = findAvailableSpots(vehicle);
        if (spotNumber < 0)
            return false;
        return parkStartingAtSpot(spotNumber, vehicle);
    }

    private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
        // Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded
        vehicle.clearSpots();
        boolean success = true;
        for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
            success &= parkingSpots[i].park(vehicle);
        }
        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    private int findAvailableSpots(Vehicle vehicle) {
        int spotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int spotsFound = 0;

        for (int i = 0; i < parkingSpots.length; i++) {
            ParkingSpot parkingSpot = parkingSpots[i];
            
            // Reset row number
            if (lastRow != parkingSpot.getRow()) {
                spotsFound = 0;
                lastRow = parkingSpot.getRow();
            }
            
            // Check if the spot can fit the vehicle
            if (parkingSpot.canFitVehicle(vehicle)) {
                spotsFound++;
            } else {
                spotsFound = 0;
            }
            
            // Get the start index of the place
            if (spotsFound == spotsNeeded) {
                return i - (spotsNeeded - 1);
            }
        }
        return -1;
    }

    public void spotFreed() {
        availableSpots++;
    }
}