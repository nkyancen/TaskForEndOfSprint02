import java.util.Scanner;

public class GameMenu {
    private static final Scanner scanner = new Scanner(System.in);

    private static boolean isGameMenuOn;

    void startTheGame() {
        isGameMenuOn = true;

        while (isGameMenuOn) {
            printGameMenu();
        }
    }

    private void printGameMenu() {
        System.out.println("Выберите тип игры:");
        System.out.println("1 - Малая"); // (диапазон чисел от 0 до 9, 3 попытки)
        System.out.println("2 - Средняя"); // (диапазон чисел от 0 до 99, 7 попыток)
        System.out.println("3 - Большая"); // (диапазон чисел от 0 до 999, 10 попыток)
        System.out.println("4 - Пользовательская"); // (диапазон чисел и количество попыток задается вручную)
        System.out.println("0 - Выход.");

        chooseGameType(checkUserInput());
    }

    private String checkUserInput() {
        boolean isRightInput = false;
        String answer = scanner.next();

        while (!isRightInput) {
            switch (answer) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "0": {
                    isRightInput = true;
                    break;
                }
                default: {
                    System.out.println("Неправильный ввод. Повторите выбор.");
                    answer = scanner.next();
                }
            }
        }
        return answer;
    }

    private void chooseGameType(String answer) {
        switch (answer) {
            case "1": {
                Game game = new Game(3, 0, 9);
                game.startTheGame();
                break;
            }
            case "2": {
                Game game = new Game(7, 0, 99);
                game.startTheGame();
                break;
            }
            case "3": {
                Game game = new Game(10, 0, 1000);
                game.startTheGame();
                break;
            }
            case "4": {
                Game game = getUserGame();
                game.startTheGame();
                break;
            }
            default: {
                endOfGames();
                break;
            }
        }
    }

    private Game getUserGame() {
        System.out.println("Введите количество попыток:");
        int attempts = getUserInput();
        System.out.println("Введите минимальное число диапазона:");
        int minOfDiapason = getUserInput();
        System.out.println("Введите максимальное число диапазона:");
        int maxOfDiapason = getUserInput();
        return new Game(attempts, minOfDiapason, maxOfDiapason);
    }

    private int getUserInput() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Это не целое число. Введите значение повторно");
        }
        return scanner.nextInt();
    }

    private void endOfGames() {
        isGameMenuOn = false;
        System.out.println("Игры кончились!! До скорых встреч!!!");
        scanner.close();
    }
}
