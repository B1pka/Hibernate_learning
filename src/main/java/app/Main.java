package app;

import console_UI.*;
import config.HibernateConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ClientService;
import service.CouponService;
import service.OrderService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        ClientService clientService = context.getBean(ClientService.class);
        OrderService orderService = context.getBean(OrderService.class);
        CouponService couponService = context.getBean(CouponService.class);

        Scanner scanner = new Scanner(System.in);

        while(true){
            try{
                System.out.println("--Меню приложения--");
                System.out.println("Выберите действие: ");
                System.out.println("1. Добавить клиента");
                System.out.println("2. Удалить клиента");
                System.out.println("3. Редактировать профиль");
                System.out.println("4. Добавить заказ");
                System.out.println("5. Применить купон");
                System.out.println("6. Найти заказ");
                System.out.println("7. Выход");
                System.out.println();
                System.out.print("Введите номер команды: ");

                int choise = Integer.parseInt(scanner.nextLine().trim());

                switch (choise){
                    case 1 -> CreateClientHandler.clientHandle(scanner, clientService);
                    case 2 -> DeleteClientHandler.deleteHandle(scanner, clientService);
                    case 3 -> EditClientHandler.editHandl(scanner, clientService);
                    case 4 -> CreateOrderHandler.orderHandle(scanner, orderService);
                    case 5 -> AddCouponHandler.couponHandle(scanner, couponService);
                    case 6 -> FindOrderHandler.findHandle(scanner, orderService);
                    case 7 -> {
                        System.out.println("Выход");
                        return;
                    }
                    default -> System.out.println("Неизвестный пункт меню");
                }
            } catch (NumberFormatException e){
                System.out.println("Ошибка: введите номер пункта");
            } catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
