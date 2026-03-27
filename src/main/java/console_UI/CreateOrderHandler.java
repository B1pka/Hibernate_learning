package console_UI;

import core.Order;
import service.OrderService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateOrderHandler {

    public static void orderHandle(Scanner scanner, OrderService orderService){

        try{
            System.out.println("4. Добавить заказ");
            System.out.print("Введите id пользователя для заказа: ");
            Long clientId = Long.parseLong(scanner.nextLine().trim());

            System.out.println("Стоимость заказа: ");
            BigDecimal amount = new BigDecimal(scanner.nextLine().trim());

            Order order = orderService.saveOrder(clientId, amount);

            System.out.println("Заказ успешно создан");

            System.out.println("Статус вашего заказа: " + order.getStatus());

        } catch (Exception e) {
            System.out.println("Ошибка при создании заказа: " + e.getMessage());
        }
    }
}
