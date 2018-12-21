public class Chessboard {

    private static final int DIMENSION_LENGTH = 8;
    private Tile[][] board;

    public Chessboard(){

        board = new Tile[DIMENSION_LENGTH][DIMENSION_LENGTH];
    }

    public static int getDimensionLength() {
        return DIMENSION_LENGTH;
    }

    public void fillTheBoard(){

        for(int i = 0; i < DIMENSION_LENGTH; i++){
            for(int j = 0; j < DIMENSION_LENGTH; j++){

                if((i + j) % 2 == 0){
                    board[i][j] = new Tile("white", i, j);
                }
                else {
                    board[i][j] = new Tile("black", i, j);

                    if (i < 3) {
                        board[i][j].setHost(new Piece("black", "standard"/*, i, j*/));
                    } else if (i > 4) {
                        board[i][j].setHost(new Piece("white", "standard"/*, i, j*/));
                    }
                }
            }
        }
    }

    public Tile getTileByCoordinates(int row, int column){

        return board[row][column];
    }

    @Override
    public String toString() {

        String result = "     ";

        for(int i = 0; i < DIMENSION_LENGTH; i++){

            result+="|     ";
        }

        result+="\n     ";

        for(int i = 0; i < DIMENSION_LENGTH; i++){

            result+="|  "+ (i + 1) + "  ";
        }

        for(int i = 0; i < DIMENSION_LENGTH; i++){

            result+="\n-----";

            for(int j = 0; j < DIMENSION_LENGTH; j++) {

                result += "|-----";
            }

            result+="\n  " + (i + 1) + "  ";

            for(int j = 0; j < DIMENSION_LENGTH; j++) {

                result += "|  " + (board[i][j].getHost() == null ? " " : board[i][j].getHost().getCharType()) + "  ";
            }
        }
        result += "\n     ";

        for(int i = 0; i < DIMENSION_LENGTH; i++){

            result+="|     ";
        }

        return result;
    }
}
