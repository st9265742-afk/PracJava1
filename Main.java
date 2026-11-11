import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(
            1, "Ноутбук", 19999.99,
            "Високопродуктивний ноутбук для роботи та ігор",
            electronics
        );

        Product product2 = new Product(
            2, "Смартфон", 12999.50,
            "Смартфон з великим екраном та високою автономністю",
            smartphones
        );

        Product product3 = new Product(
            3, "Навушники", 2499.00,
            "Бездротові навушники з шумозаглушенням",
            accessories
        );

        List<Product> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();

        while (true) {

            System.out.println("\n===== ІНТЕРНЕТ-МАГАЗИН =====");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Оформити замовлення");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    for (Product product : products) {
                        System.out.println(product);
                    }

                    break;

                case 2:

                    System.out.println("Введіть ID товару:");
                    int addId = scanner.nextInt();

                    boolean found = false;

                    for (Product product : products) {

                        if (product.getId() == addId) {
                            cart.addProduct(product);
                            System.out.println("Товар додано до кошика!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Товар не знайдено.");
                    }

                    break;

                case 3:

                    if (cart.isEmpty()) {
                        System.out.println("Кошик порожній.");
                    } else {
                        System.out.println(cart);
                    }

                    break;

                case 4:

                    System.out.println("Введіть ID товару для видалення:");
                    int removeId = scanner.nextInt();

                    if (cart.removeProductById(removeId)) {
                        System.out.println("Товар видалено з кошика!");
                    } else {
                        System.out.println("Товар не знайдено у кошику.");
                    }

                    break;

                case 5:

                    if (cart.isEmpty()) {
                        System.out.println("Неможливо оформити порожнє замовлення.");
                        break;
                    }

                    Order order = new Order(cart);

                    orderHistory.addOrder(order);

                    System.out.println("Замовлення оформлено!");
                    System.out.println(order);

                    cart.clear();

                    break;

                case 6:

                    orderHistory.showHistory();

                    break;

                case 7:

                    System.out.println("\nВиберіть тип пошуку:");
                    System.out.println("1 - За назвою товару");
                    System.out.println("2 - За категорією");

                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Введіть пошуковий запит:");
                    String query = scanner.nextLine();

                    boolean searchFound = false;

                    for (Product product : products) {

                        if (searchChoice == 1 &&
                            product.getName().toLowerCase()
                            .contains(query.toLowerCase())) {

                            System.out.println(product);
                            searchFound = true;

                        } else if (searchChoice == 2 &&
                            product.getCategory().getName().toLowerCase()
                            .contains(query.toLowerCase())) {

                            System.out.println(product);
                            searchFound = true;
                        }
                    }

                    if (searchChoice != 1 && searchChoice != 2) {
                        System.out.println("Невідомий тип пошуку.");
                    } else if (!searchFound) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }

                    break;

                case 0:

                    System.out.println("Дякуємо, що використовували наш магазин!");

                    scanner.close();
                    return;

                default:

                    System.out.println("Невідома опція. Спробуйте ще раз.");

                    break;
            }
        }
    }
}