import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void showHistory() {

        if (orders.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
            return;
        }

        System.out.println("\nІсторія замовлень:");

        for (int i = 0; i < orders.size(); i++) {
            System.out.println("\nЗамовлення №" + (i + 1));
            System.out.println(orders.get(i));
            System.out.println("----------------------");
        }
    }
}
