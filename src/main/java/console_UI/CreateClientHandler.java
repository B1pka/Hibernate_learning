package console_UI;

import core.Client;
import service.ClientService;

import java.util.Scanner;

public class CreateClientHandler {
    public static void clientHandle(Scanner scanner, ClientService clientService){

        System.out.println("1. Добавить клиента");
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine().trim();

        System.out.print("Введите ваш email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Введите ваш номер телефона: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Введите ваш адрес: ");
        String address = scanner.nextLine().trim();

        Client client = clientService.createClient(name, email, phone, address);
        System.out.println("Вы успешно зарегестрировались: Id:" + client.getId());
    }
}
