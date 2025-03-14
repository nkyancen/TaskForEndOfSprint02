import java.util.Random;
import java.util.Scanner;

public class Main {
    static int ATTEMPTS_LIMIT = 3;
    static int RANDOM_LIMIT = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();

        int randomNumber;
        int attemptsCount;
        String nextGame;
        boolean isNotGuessNumber;
        boolean hasNextGame = true;
        boolean isNotRightInput;

        while (hasNextGame) {
            isNotGuessNumber = true;
            attemptsCount = ATTEMPTS_LIMIT;

            randomNumber = rnd.nextInt(RANDOM_LIMIT + 1);
            System.out.printf("Я загадал число от 0 до %d. Попробуй отгадать!! \n\n", RANDOM_LIMIT);

            while (isNotGuessNumber) {
                System.out.printf("Осталось попыток: %d\nВведите целое число от 0 до %d\n", attemptsCount, RANDOM_LIMIT);

                if (scanner.hasNextInt()) {
                    int userNumber = scanner.nextInt();
                    if (userNumber >= 0 && userNumber <= RANDOM_LIMIT) {
                        if (userNumber == randomNumber) {
                            System.out.println("Поздравляю, число угадано!!!");
                            isNotGuessNumber = false;
                        } else if (userNumber < randomNumber) {
                            System.out.println("Ты ввел число меньше загаданного");
                            attemptsCount--;
                        } else {
                            System.out.println("Ты ввел число больше загаданного");
                            attemptsCount--;
                        }
                        if (attemptsCount == 0) {
                            System.out.println("Ты проиграл! Закончились попытки.");
                            break;
                        }
                    } else {
                        System.out.println("Число вне интервала. Введите значение повторно");
                    }
                } else {
                    scanner.next();
                    System.out.println("Произошла ошибка ввода. Введите значение повторно");
                }
            }

            System.out.println("Повторить игру еще раз? 1 - да; 0 - нет");
            isNotRightInput = true;
            while (isNotRightInput) {
                nextGame = scanner.next();
                switch (nextGame) {
                    case "0": {
                        hasNextGame = false;
                        isNotRightInput = false;
                        break;
                    }
                    case "1": {
                        isNotRightInput = false;
                        break;
                    }
                    default: {
                        System.out.println("Неправильный ввод. Повторите выбор.");
                        break;
                    }
                }
            }
        }

        scanner.close();
    }
}
