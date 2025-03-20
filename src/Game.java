import java.util.Random;
import java.util.Scanner;

class Game {
    private static int attemptsLimit;
    private static int randomMax;
    private static int randomMin;

    Game(int attempts, int minNumber, int maxNumber) {
        attemptsLimit = attempts;
        randomMin = minNumber;
        randomMax = maxNumber;
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean hasTheGame;
    private int attemptCount;

    void startTheGame() {
        System.out.println("Да начнется игра!");
        System.out.printf("Правила просты: я загадываю число от %d до %d, ты отгадываешь.\n", randomMin, randomMax);
        System.out.printf("Всего у тебя есть всего %s.\n", attemptsToString(attemptsLimit));
        playTheGame();
    }

    private void playTheGame() {
        hasTheGame = true;
        attemptCount = attemptsLimit;

        int randomNumber = generateRandomNumber();
        while (hasTheGame && attemptCount > 0) {
            int userNumber = getUserNumberInNeedInterval();
            analyseNumbers(randomNumber, userNumber);
        }
        isGameFailed(randomNumber);

        ifRestart();
    }

    private void isGameFailed(int randomNumber) {
        if (attemptCount == 0 && hasTheGame) {
            System.out.printf("Упс, попытки закончились.\nЯ загадал число %d\n", randomNumber);
        }
    }

    private int generateRandomNumber() {
        Random rnd = new Random();
        System.out.printf("Я загадал число от %d до %d. Попробуйте отгадать!! \n\n", randomMin, randomMax);
        return rnd.nextInt(randomMin, randomMax + 1);
    }

    private int readUserNumber() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Это не целое число. Введите значение повторно");
        }
        return scanner.nextInt();
    }

    private int getUserNumberInNeedInterval() {
        System.out.printf("Осталось %s.\nВведите целое число от %d до %d\n", attemptsToString(attemptCount), randomMin, randomMax);
        int userInput = readUserNumber();
        while (userInput < randomMin || userInput > randomMax) {
            System.out.println("Число вне интервала. Введите значение повторно");
            userInput = readUserNumber();
        }
        return userInput;
    }

    private void analyseNumbers(int randomNumber, int userNumber) {
        attemptCount--;
        if (userNumber == randomNumber) {
            System.out.println("Поздравляю, число угадано!!!");
            hasTheGame = false;
        } else if (userNumber < randomNumber) {
            System.out.println("Вы ввели число меньше загаданного");
        } else {
            System.out.println("Вы ввели число больше загаданного");
        }
    }

    private void ifRestart() {
        System.out.println("Повторить игру еще раз? 1 - да; 0 - нет");
        checkUserAnswer(scanner.next());
    }

    private void checkUserAnswer(String answer) {
        while (!answer.equals("1") && !answer.equals("0")) {
            System.out.println("Неправильный ввод. Повторите выбор.");
            answer = scanner.next();
        }
        if (answer.equals("0")) {
            finishTheGame();
        } else {
            startTheGame();
        }
    }

    private void finishTheGame() {
        System.out.println("Игра окончена.");
        scanner.close();
    }

    private String attemptsToString(int attempts) {
        if ((attempts / 10) % 10 == 1) {
            return String.format("%d попыток", attempts);
        }

        String attemptsOut;
        switch (attempts % 10) {
            case 1: {
                attemptsOut = String.format("%d попытка", attempts);
                break;
            }
            case 2:
            case 3:
            case 4: {
                attemptsOut = String.format("%d попытки", attempts);
                break;
            }
            default: {
                attemptsOut = String.format("%d попыток", attempts);
            }
        }
        return attemptsOut;
    }
}


