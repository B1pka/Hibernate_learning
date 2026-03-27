package console_UI;

import core.Order;
import service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static service.OrderService.printOrders;

public class FindOrderHandler {
    public static void findHandle(Scanner scanner, OrderService orderService){

        boolean searchMenu = true;

        while(searchMenu){
            try {
                System.out.println("6. Найти заказ");
                System.out.println("1. Найти заказ по сумме");
                System.out.println("2. Найти заказ по id клиента");
                System.out.println("3. Найти заказ по id и сумме");
                System.out.println("4. Выход в главное меню");

                int searchMethod = Integer.parseInt(scanner.nextLine().trim());

                switch (searchMethod){
                    case 1 -> {
                        System.out.print("Введите сумму заказа: ");
                        BigDecimal searchPrice = new BigDecimal(scanner.nextLine().trim());

                        List<Order> orderList = orderService.findByPrice(searchPrice);
                        printOrders(orderList);
                    }
                    case 2 -> {
                        System.out.print("Введите id клиента: ");
                        Long clientId = Long.parseLong(scanner.nextLine().trim());

                        List<Order> orderList = orderService.findByClientId(clientId);
                        printOrders(orderList);
                    }
                    case 3 -> {
                        System.out.print("Введите сумму заказа: ");
                        BigDecimal searchPrice = new BigDecimal(scanner.nextLine().trim());

                        System.out.print("Введите id клиента: ");
                        Long clientId = Long.parseLong(scanner.nextLine().trim());

                        List<Order> orderList = orderService.findByPriceAndClientId(clientId, searchPrice);
                        printOrders(orderList);
                    }
                    case 4 -> {
                        System.out.println("Возвращение в основное меню...");
                        searchMenu = false;
                    }
                    default -> System.out.println("Неизвестный пункт");
                }
            } catch (Exception e){
                System.out.println("Ошибка при поиске заказа");
            }
        }
    }
}
