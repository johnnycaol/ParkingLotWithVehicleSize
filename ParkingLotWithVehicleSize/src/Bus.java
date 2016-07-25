public class Bus extends Vehicle {
    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.Large;
    }

    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        // checks if the spot is a Large, does NOT check num of spots
        return parkingSpot.getSize() == VehicleSize.Large;
    }
}
