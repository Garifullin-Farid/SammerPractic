import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== №1 Массивы ===");
        Random rand = new Random();
        int[] years = new int[50];
        for (int i = 0; i < years.length; i++) {
            years[i] = 2000 + rand.nextInt(26);
        }

        System.out.print("Машины после 2015: ");
        int sum = 0;
        for (int year : years) {
            sum += year;
            if (year > 2015) {
                System.out.print(year + " ");
            }
        }
        System.out.println("\nСредний возраст: " + (2026 - sum / years.length));

        System.out.println("\n=== №2 Коллекции ===");
        List<String> models = new ArrayList<>();
        models.add("Toyota Camry");
        models.add("BMW X5");
        models.add("Tesla Model S");
        models.add("Toyota Camry");
        models.add("Honda Civic");
        models.add("BMW X5");
        models.add("Tesla Model 3");

        Set<String> uniqueModels = new HashSet<>(models);
        List<String> sortedModels = new ArrayList<>(uniqueModels);
        sortedModels.sort(Comparator.reverseOrder());
        System.out.println("Отсортировано: " + sortedModels);

        Set<String> resultSet = new HashSet<>();
        for (String model : sortedModels) {
            resultSet.add(model.contains("Tesla") ? "ELECTRO_CAR" : model);
        }
        System.out.println("С заменой Tesla: " + resultSet);

        System.out.println("\n=== №3 Equals/hashCode ===");
        Set<Car> cars = new HashSet<>();
        cars.add(new Car("VIN001", "Camry", "Toyota", 2020, 30000, 25000));
        cars.add(new Car("VIN002", "X5", "BMW", 2022, 15000, 45000));
        cars.add(new Car("VIN001", "Corolla", "Toyota", 2021, 20000, 22000));
        cars.add(new Car("VIN003", "Model S", "Tesla", 2023, 5000, 80000));

        System.out.println("Уникальных машин: " + cars.size());
        for (Car car : cars) {
            System.out.println(car);
        }

        List<Car> sortedCars = new ArrayList<>(cars);
        Collections.sort(sortedCars);
        System.out.println("\nСортировка по году (от новых):");
        for (Car car : sortedCars) {
            System.out.println(car.getYear() + " - " + car.getModel());
        }

        System.out.println("\n=== №4 Stream API ===");
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("VIN001", "Camry", "Toyota", 2020, 30000, 25000));
        carList.add(new Car("VIN002", "X5", "BMW", 2022, 15000, 45000));
        carList.add(new Car("VIN003", "Model S", "Tesla", 2023, 5000, 80000));
        carList.add(new Car("VIN004", "Civic", "Honda", 2021, 45000, 20000));
        carList.add(new Car("VIN005", "3 Series", "BMW", 2020, 60000, 30000));
        carList.add(new Car("VIN006", "RAV4", "Toyota", 2022, 25000, 32000));

        System.out.println("Топ-3 самых дорогих с пробегом < 50000:");
        carList.stream()
                .filter(c -> c.getMileage() < 50000)
                .sorted((c1, c2) -> Integer.compare(c2.getPrice(), c1.getPrice()))
                .limit(3)
                .forEach(c -> System.out.println(c.getModel() + " - " + c.getPrice()));

        double avgMileage = carList.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.println("Средний пробег: " + avgMileage);

        Map<String, List<Car>> byManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("Группировка по производителю:");
        byManufacturer.forEach((manufacturer, list) ->
                System.out.println(manufacturer + ": " + list.size() + " машин"));
    }
}