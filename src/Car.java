import java.util.Objects;

public class Car implements Comparable<Car> {
    private final String vin;
    private final String model;
    private final String manufacturer;
    private final int year;
    private int mileage;
    private int price;
    private CarType type;

    public Car(String vin, String model, String manufacturer, int year, int mileage, int price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public int getPrice() { return price; }
    public CarType getType() { return type; }

    public void setMileage(int mileage) { this.mileage = mileage; }
    public void setPrice(int price) { this.price = price; }
    public void setType(CarType type) { this.type = type; }
}