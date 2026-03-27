package console_UI;

import service.ClientService;

import java.util.Scanner;

public class DeleteClientHandler {

    public static void deleteHandle(Scanner scanner, ClientService clientService){
        try{
            System.out.println("2. Удалить клиента");
            System.out.print("Введите id клиента для удалени: ");

            Long clientId = Long.parseLong(scanner.nextLine().trim());

            clientService.deleteClient(clientId);

            System.out.println("Клиент с id:" + clientId + " успешно удален");
        } catch (NumberFormatException e){
            System.out.println("Введите корректный id клиента");
        }
    }
}
