import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        System.out.println("Hello world, do you want to play some English Draughts? I'm sure you do, so please " +
                "introduce yourself:");

        Scanner scanner = new Scanner(System.in);

        String playerOne = scanner.nextLine();
        System.out.println("\nThank you, and your partner:");

        String playerTwo = scanner.nextLine();

        Game game = new Game();

        System.out.println("Perfect, thank you! Now we can start the game; I'm sure you know the rules so let's " +
                "just start!\nI'll throw a coin now to to make the fate decide who starts. So black pieces belong " +
                "to...\n\n\n\n" + Utils.coinToss(playerOne, playerTwo) + "!!!\n\nSTART\n\nProvide a move " +
                "following the pattern -> (6,1) (5,2)");

        game.printTheChessboard();

        String input = null;
        int errorType = 0;

        while (game.arePiecesLeft() /* && */) {

            while (input == null) {

                switch (errorType) {

                    case 1:
                        System.out.println("Wrong input format!");
                        break;

                    case 2:
                        System.out.println("Wrong move coordinates!");
                        break;
                }

                System.out.println("Make a move:");

                input = scanner.nextLine();

                if (!Utils.isInputCorrect(input)) {

                    input = null;

                    errorType = 1;
                }
                else {
                    System.out.println(game.getRoundNumber());
                    System.out.println(game.isAnyMoveAvailable());
                    System.out.println(game.isAnyCaptureAvailable());


                    int[] coordinates = Utils.decrementDigits(Utils.extractDigitsFromInput(input));

                    if (coordinates.length == 4) {

                        if (!game.isMoveCorrect(coordinates[0], coordinates[1], coordinates[2], coordinates[3])) {

                            input = null;

                            errorType = 2;
                        }
                        else {

                            game.move(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

                            game.printTheChessboard();
                        }
                    }
                    else if (coordinates.length == 6) {

                        if (!game.isCaptureCorrect(coordinates[0], coordinates[1], coordinates[2], coordinates[3],
                                coordinates[4], coordinates[5])) {

                            input = null;

                            errorType = 2;
                        }
                        else {

                            game.capture(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4],
                                    coordinates[5]);

                            game.printTheChessboard();
                        }
                    }
                }

            }
            input = null;

            errorType = 0;

            game.nextRound();

            System.out.println("ok");
        }
    }
}
