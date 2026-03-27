package console_UI;

import service.ClientService;

import java.util.Scanner;

public class EditClientHandler {
    public static void editHandl(Scanner scanner, ClientService clientService){

        System.out.print("Ведите id для редактирования: ");
        Long clientId = Long.parseLong(scanner.nextLine().trim());

        boolean editMenu = true;

        while(editMenu){
            try {
                System.out.println("3. Редактировать профиль");
                System.out.println("Выберите что вы хотите изменить:");
                System.out.println("1. Имя");
                System.out.println("2. Email");
                System.out.println("3. Телефон");
                System.out.println("4. Адрес");
                System.out.println("5. Выход в основное меню");
                System.out.print("Выберите параметр для изменения: ");

                int change = Integer.parseInt(scanner.nextLine());

                switch (change) {
                    case 1 -> {
                        System.out.println("Введите новое имя: ");
                        String newName = scanner.nextLine();
                        clientService.editName(clientId, newName);
                        System.out.println("Имя изменено: " + newName);
                    }
                    case 2 -> {
                        System.out.println("Введите новый email: ");
                        String newEmail = scanner.nextLine();
                        clientService.editEmail(clientId, newEmail);
                        System.out.println("Email изменен: " + newEmail);
                    }
                    case 3 -> {
                        System.out.println("Введите новый телефон: ");
                        String newPhone = scanner.nextLine();
                        clientService.editPhone(clientId, newPhone);
                        System.out.println("Телефон изменен: " + newPhone);
                    }
                    case 4 -> {
                        System.out.println("Введите новый адрес: ");
                        String newAddress = scanner.nextLine();
                        clientService.editAddress(clientId, newAddress);
                        System.out.println("Адрес изменен: " + newAddress);
                    }
                    case 5 -> {
                        System.out.println("Возврат в основное меню...");
                        editMenu = false;
                    }
                    default -> System.out.println("Укажите корректный пункт меню");
                }
            } catch (Exception e) {
                System.out.println("Ошибка при редактировании профиля: " + e.getMessage());
            }
        }
    }
}
