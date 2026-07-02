import java.util.*;
import java.util.stream.Collectors;

public class CarDealership {
    private final List<Car> cars;

    public CarDealership() {
        this.cars = new ArrayList<>();
    }

    public boolean addCar(Car car) {
        boolean exists = cars.stream()
                .anyMatch(c -> c.getVin().equals(car.getVin()));
        if (!exists) {
            cars.add(car);
            return true;
        }
        return false;
    }

    public List<Car> findByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToInt(Car::getPrice)
                .average()
                .orElse(0);
    }

    public List<Car> sortByYearDesc() {
        return cars.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getYear(), c1.getYear()))
                .collect(Collectors.toList());
    }

    public Map<CarType, Long> countByType() {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    public Car oldestCar() {
        return cars.stream()
                .min(Comparator.comparingInt(Car::getYear))
                .orElse(null);
    }

    public Car newestCar() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getYear))
                .orElse(null);
    }

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }
}