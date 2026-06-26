public class Main {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Иван");
        BankAccount acc2 = new BankAccount("Мария");

        System.out.println(acc1);
        System.out.println(acc2);

        acc1.deposit(1000);
        System.out.println("Пополнение: " + acc1.getBalance());

        acc1.withdraw(300);
        System.out.println("Снятие: " + acc1.getBalance());

        acc1.transfer(acc2, 200);
        System.out.println("Баланс acc1: " + acc1.getBalance());
        System.out.println("Баланс acc2: " + acc2.getBalance());

        acc1.setBlocked(true);
        System.out.println("Попытка снять с заблокированного: " + acc1.withdraw(100));

        System.out.println("Сравнение счетов: " + acc1.equals(acc2));
    }
}