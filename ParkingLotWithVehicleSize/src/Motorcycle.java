public class Motorcycle extends Vehicle {
    public Motorcycle() {
        spotsNeeded = 1;
        size = VehicleSize.Motorcycle;
    }

    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        // checks if the spot is a compact, motorcycle or a large
        return parkingSpot.getSize() == VehicleSize.Large || parkingSpot.getSize() == VehicleSize.Compact
                || parkingSpot.getSize() == VehicleSize.Motorcycle;
    }
}