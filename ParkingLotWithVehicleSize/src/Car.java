
public class Car extends Vehicle {
    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        // checks if the spot is a compact or a large
        return parkingSpot.getSize() == VehicleSize.Large || parkingSpot.getSize() == VehicleSize.Compact;
    }
}
