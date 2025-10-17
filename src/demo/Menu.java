package demo;

import devices.*;
import facade.HomeAutomationFacade;

import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);


        Device light = new Light();
        Device music = new MusicSystem();
        Device camera = new SecurityCamera();
        Device thermostat = new Thermostat();


        HomeAutomationFacade home = new HomeAutomationFacade(light, music, camera, thermostat);

        int choice = -1;
        System.out.println("Welcome to Smart Home Control Center!");

        while (choice != 0) {
            System.out.println("0===============================");
            System.out.println("Current Battery Level: " + home.getPowerLevel() + "%");
            System.out.println("Select a mode:");
            System.out.println("1. Start Party Mode");
            System.out.println("2. Activate Night Mode");
            System.out.println("3. Relax Mode");
            System.out.println("4. Activate Security Mode");
            System.out.println("5. Leave Home (enable remote access)");
            System.out.println("6. Return Home");
            System.out.println("7. Set Thermostat Temperature");
            System.out.println("8. Normal Mode (reset all)");
            System.out.println("0. Exit");
            System.out.println("===============================");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> home.startPartyMode();
                case 2 -> home.activateNightMode();
                case 3 -> home.relaxMode();
                case 4 -> home.activateSecurityMode();
                case 5 -> home.leaveHome();
                case 6 -> home.returnHome();
                case 7 -> {
                    System.out.print("Enter desired temperature: ");
                    try {
                        int temp = Integer.parseInt(scanner.nextLine());
                        home.setThermostatTemperature(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number!");
                    }
                }
                case 8 -> home.normalMode();
                case 0 -> System.out.println("Exiting Smart Home System... Goodbye!");
                default -> System.out.println("Unknown choice. Try again.");
            }
        }

        scanner.close();
    }
}
