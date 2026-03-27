package console_UI;

import service.CouponService;

import java.util.Scanner;

public class AddCouponHandler {
    public static void couponHandle(Scanner scanner, CouponService couponService){
        try{
            System.out.println("5. Применить купон");
            System.out.print("Введите id клиента: ");
            Long clientId = Long.parseLong(scanner.nextLine().trim());

            System.out.println("Введите id купона");
            Long couponId = Long.parseLong(scanner.nextLine().trim());

            couponService.addCouponToClient(clientId, couponId);

            System.out.println("Купон " + couponId + " успешно приминен");
        } catch (Exception e){
            System.out.println("Ошибка при приминение купона: " + e.getMessage());
        }
    }
}
