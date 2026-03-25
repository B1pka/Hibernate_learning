package services;

import core.Client;
import core.Hibernatecfg;
import core.Order;
import core.Profile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        ApplicationContext context = new AnnotationConfigApplicationContext(Hibernatecfg.class);
        ClientService clientService = context.getBean(ClientService.class);
        CouponService couponService = context.getBean(CouponService.class);
        OrderService orderService = context.getBean(OrderService.class);
        ProfileService profileService = context.getBean(ProfileService.class);

        while (true) {
            try {
                System.out.println("--Меню приложения--");
                System.out.println("Выберите действие: ");
                System.out.println("1. Добавить клиента");
                System.out.println("2. Удалить клиента");
                System.out.println("3. Редактировать профиль");
                System.out.println("4. Добавить заказ");
                System.out.println("5. Изменить купон");
                System.out.println("6. Найти заказ");
                System.out.println("7. Выход");
                System.out.println();
                System.out.print("Введите номер команды: ");

                String line = scanner.nextLine();

                int choice;
                try {
                    choice = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректный номер пункта меню");
                    System.out.println();
                    continue;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.println("1. Добавить клиента");
                        System.out.println("Введите имя клиента");
                        String name = scanner.nextLine();
                        if (name.isBlank()) {
                            throw new IllegalArgumentException("Поле 'имя' обязательно");
                        }

                        System.out.println("Введите email клиента");
                        String email = scanner.nextLine();
                        if (email.isBlank()) {
                            throw new IllegalArgumentException("Поле 'email' обязательно");
                        }

                        Client client = new Client(LocalDateTime.now(), name, email);
                        clientService.saveClient(client);

                        System.out.println("Введите номер телефона...");
                        String phone = scanner.nextLine();

                        System.out.println("Введите адрес...");
                        String address = scanner.nextLine();

                        Profile profile = new Profile(phone, address);
                        profileService.saveProfile(profile, client);

                        System.out.println("Вы успешно авторизовались! Ваш Id: " + client.getId());
                    }

                    case 2 -> {
                        System.out.println("2. Удалить клиента");
                        System.out.print("Введите id клиента для удаления: ");
                        String id = scanner.nextLine();

                        if (id.isBlank()) {
                            throw new IllegalArgumentException("Введите корректный id");
                        }

                        clientService.deleteClient(Long.parseLong(id));
                        System.out.println("Клиент успешно удален");
                    }

                    case 3 -> {
                        System.out.print("Введите id пользователя для редактирования: ");
                        String editId = scanner.nextLine();

                        if (editId.isBlank()) {
                            throw new IllegalArgumentException("Id не должен быть пустым");
                        }

                        boolean stayEditMenu = true;

                        while (stayEditMenu) {
                            try {
                                System.out.println("3. Редактировать профиль");
                                System.out.println("Выберите что вы хотите изменить:");
                                System.out.println("1. Имя");
                                System.out.println("2. Email");
                                System.out.println("3. Телефон");
                                System.out.println("4. Адрес");
                                System.out.println("5. Выход в основное меню");
                                System.out.print("Выберите параметр для изменения: ");

                                String subLine = scanner.nextLine();
                                int change = Integer.parseInt(subLine.trim());

                                switch (change) {
                                    case 1 -> {
                                        System.out.println("Введите новое имя: ");
                                        String newName = scanner.nextLine();
                                        clientService.editName(Long.parseLong(editId), newName);
                                        System.out.println("Имя изменено: " + newName);
                                    }
                                    case 2 -> {
                                        System.out.println("Введите новый email: ");
                                        String newEmail = scanner.nextLine();
                                        clientService.editEmail(Long.parseLong(editId), newEmail);
                                        System.out.println("Email изменен: " + newEmail);
                                    }
                                    case 3 -> {
                                        System.out.println("Введите новый телефон: ");
                                        String newPhone = scanner.nextLine();
                                        profileService.editPhone(Long.parseLong(editId), newPhone);
                                        System.out.println("Телефон изменен: " + newPhone);
                                    }
                                    case 4 -> {
                                        System.out.println("Введите новый адрес: ");
                                        String newAddress = scanner.nextLine();
                                        profileService.editAddress(Long.parseLong(editId), newAddress);
                                        System.out.println("Адрес изменен: " + newAddress);
                                    }
                                    case 5 -> {
                                        System.out.println("Возврат в основное меню...");
                                        stayEditMenu = false;
                                    }
                                    default -> System.out.println("Укажите корректный пункт меню");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Введите число от 1 до 5");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Ошибка при редактировании профиля: " + e.getMessage());
                            }
                        }
                    }

                    case 4 -> {
                        System.out.println("4. Добавить заказ");
                        System.out.println("Введите id пользователя для заказа: ");
                        String orderId = scanner.nextLine();

                        System.out.println("Стоимость заказа: ");
                        BigDecimal amount = new BigDecimal(scanner.nextLine().trim());

                        boolean orderStatus = true;
                        System.out.println("Статус вашего заказа: " + orderStatus);

                        Client client = clientService.getClientById(Long.parseLong(orderId));
                        Order order = new Order(LocalDateTime.now(), amount, orderStatus);
                        orderService.saveOrder(order, client);

                        System.out.println("Заказ успешно создан");
                    }

                    case 5 -> {
                        System.out.println("5. Изменить купон");
                        System.out.println("Введите id пользователя: ");
                        Long clientId = Long.parseLong(scanner.nextLine());

                        System.out.println("Введите id купона для применения: ");
                        Long couponId = Long.parseLong(scanner.nextLine());

                        clientService.addCoupon(clientId, couponId);
                        System.out.println("Купон успешно применен к клиенту!");
                    }

                    case 6 -> {
                        System.out.println("6. Найти заказ");
                        System.out.println("Введите сумму заказа для поиска: ");

                        BigDecimal searchPrice = new BigDecimal(scanner.nextLine().trim());
                        List<Order> orders = orderService.findOrderByPrice(searchPrice);

                        if (orders.isEmpty()) {
                            System.out.println("Заказов с такой стоимостью нет");
                        } else {
                            System.out.println("Найдены заказы:");
                            orders.forEach(o ->
                                    System.out.println(
                                            "Id: " + o.getId() +
                                                    "; Date: " + o.getOrderDate() +
                                                    "; Status: " + o.getStatus()
                                    )
                            );
                        }
                    }

                    case 7 -> {
                        System.out.println("П О К А");
                        return;
                    }

                    default -> System.out.println("Неизвестный пункт меню");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число в правильном формате");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }

            System.out.println();
        }
    }
}