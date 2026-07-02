import java.util.*;

public class DealershipApp {
    private static final CarDealership DEALERSHIP = new CarDealership();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== АВТОЦЕНТР ===");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти по производителю");
            System.out.println("3. Средняя цена по типу");
            System.out.println("4. Сортировка по году");
            System.out.println("5. Статистика по типам");
            System.out.println("6. Самая старая/новая машина");
            System.out.println("7. Показать все машины");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            int choice = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (choice) {
                case 1 -> addCar();
                case 2 -> findCar();
                case 3 -> averagePrice();
                case 4 -> sortByYear();
                case 5 -> statistics();
                case 6 -> oldestNewest();
                case 7 -> showAll();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void addCar() {
        System.out.print("VIN: ");
        String vin = SCANNER.nextLine();
        System.out.print("Модель: ");
        String model = SCANNER.nextLine();
        System.out.print("Производитель: ");
        String manufacturer = SCANNER.nextLine();
        System.out.print("Год: ");
        int year = SCANNER.nextInt();
        System.out.print("Пробег: ");
        int mileage = SCANNER.nextInt();
        System.out.print("Цена: ");
        int price = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.print("Тип (SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE): ");
        String typeStr = SCANNER.nextLine().toUpperCase();
        CarType type = CarType.valueOf(typeStr);

        Car car = new Car(vin, model, manufacturer, year, mileage, price);
        car.setType(type);

        if (DEALERSHIP.addCar(car)) {
            System.out.println("Машина добавлена!");
        } else {
            System.out.println("Машина с таким VIN уже существует!");
        }
    }

    private static void findCar() {
        System.out.print("Введите производителя: ");
        String manufacturer = SCANNER.nextLine();
        List<Car> found = DEALERSHIP.findByManufacturer(manufacturer);
        if (found.isEmpty()) {
            System.out.println("Машины не найдены");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static void averagePrice() {
        System.out.print("Введите тип: ");
        String typeStr = SCANNER.nextLine().toUpperCase();
        CarType type = CarType.valueOf(typeStr);
        double avg = DEALERSHIP.averagePriceByType(type);
        System.out.println("Средняя цена: " + avg);
    }

    private static void sortByYear() {
        List<Car> sorted = DEALERSHIP.sortByYearDesc();
        sorted.forEach(c -> System.out.println(c.getYear() + " - " + c.getModel()));
    }

    private static void statistics() {
        Map<CarType, Long> stats = DEALERSHIP.countByType();
        stats.forEach((type, count) -> System.out.println(type + ": " + count));
    }

    private static void oldestNewest() {
        Car oldest = DEALERSHIP.oldestCar();
        Car newest = DEALERSHIP.newestCar();
        System.out.println("Самая старая: " + (oldest != null ? oldest.getModel() : "нет"));
        System.out.println("Самая новая: " + (newest != null ? newest.getModel() : "нет"));
    }

    private static void showAll() {
        List<Car> all = DEALERSHIP.sortByYearDesc();
        all.forEach(System.out::println);
    }
}