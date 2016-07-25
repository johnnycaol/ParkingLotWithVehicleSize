public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize size;
    private int row;
    private int number;
    private Level level;

    public ParkingSpot(Level level, int row, int number, VehicleSize size) {
        this.level = level;
        this.row = row;
        this.number = number;
        this.size = size;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        // check if the spot is big enough and is available
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    public boolean park(Vehicle vehicle) {
        if (!canFitVehicle(vehicle))
            return false;

        this.vehicle = vehicle;
        vehicle.parkInSpot(this);
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void removeVehicle() {
        level.spotFreed();
        vehicle = null;
    }
}