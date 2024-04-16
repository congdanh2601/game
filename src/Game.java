import java.util.Scanner;

public class Game {
    Board b = new Board();
    Player p1;
    Player p2;
    int count = 0;

    Scanner scanner = new Scanner(System.in);

    void takePlayerInfo() {
        String type1;
        String name1 = "";

        System.out.print("Choose X/O: ");
        do {
            type1 = scanner.nextLine();
            if (type1.equals("X") || type1.equals("O")) {
                System.out.print("Enter your name: ");
                name1 = scanner.nextLine();
                break;
            }
            System.out.print("You must type X or O: ");
        } while (true);

        p1 = new Player(type1, name1);

        System.out.print("Enter player 2's name: ");
        String name2 = scanner.nextLine();
        String type2 = type1.equals("X") ? "O" : "X";
        p2 = new Player(type2, name2);
    }

    void play() {
        b.printBoard();
        Player currentPlayer = p1;

        while (count < 9) {
            System.out
                    .print(currentPlayer.name + "\'s turn (" + currentPlayer.type + "), choose your position (1-9): ");
            int position = Integer.parseInt(scanner.nextLine());

            // Check if choosen position is unavailable
            if (!b.board[position - 1].equals(" ")) {
                System.out.println("That position\'s already filled.");
                continue;
            }

            b.board[position - 1] = currentPlayer.type;
            b.printBoard();

            if (checkWin() >= 0) {
                System.out.println(currentPlayer.name + " won.");
                break;
            }

            if (currentPlayer == p1) {
                currentPlayer = p2;
            } else {
                currentPlayer = p1;
            }

            count++;
            if (count == 9) {
                System.out.println("Game draw.");
            }
        }
    }

    int checkWin() {
        if (!b.board[0].equals(" ") && ((b.board[0].equals(b.board[1]) && b.board[0].equals(b.board[2])) ||
                (b.board[0].equals(b.board[3]) && b.board[0].equals(b.board[6])) ||
                (b.board[0].equals(b.board[4]) && b.board[0].equals(b.board[8])))) {
            System.out.println(0);
            return 0;
        }

        if (!b.board[1].equals(" ") && (b.board[1].equals(b.board[4]) && b.board[1].equals(b.board[7]))) {
            return 1;
        }

        if (((b.board[2].equals(b.board[5]) && b.board[2].equals(b.board[8])) ||
                (b.board[2].equals(b.board[4]) && b.board[2].equals(b.board[6]))) && !b.board[2].equals(" ")) {
            return 2;
        }

        if ((b.board[3].equals(b.board[4]) && b.board[3].equals(b.board[5])) && !b.board[3].equals(" ")) {
            return 3;
        }

        if ((b.board[6].equals(b.board[7]) && b.board[6].equals(b.board[8])) && !b.board[6].equals(" ")) {
            return 6;
        }
        return -1;
    }

}
