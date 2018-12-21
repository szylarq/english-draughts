public class Game {

    private Chessboard chessboard;
    private int roundNumber;
    private final static int INITIAL_NUMBER_OF_PIECES = 12;
    private int numberOfBlackPieces;
    private int numberOfWhitePieces;

    public Game(){

        chessboard = new Chessboard();
        roundNumber = 0;
        chessboard.fillTheBoard();
        numberOfBlackPieces = INITIAL_NUMBER_OF_PIECES;
        numberOfWhitePieces = INITIAL_NUMBER_OF_PIECES;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void nextRound(){

        setRoundNumber(roundNumber + 1);
    }

    public boolean isMoveCorrect(int rowStart, int columnStart, int rowEnd, int columnEnd) {

        if (rowStart >= Chessboard.getDimensionLength() || rowStart < 0) {
            return false;
        }
        else if (rowEnd >= Chessboard.getDimensionLength() || rowEnd < 0) {
            return false;
        }
        else if (columnStart >= Chessboard.getDimensionLength() || columnStart < 0) {
            return false;
        }
        else if (columnEnd >= Chessboard.getDimensionLength() || columnEnd < 0) {
            return false;
        }
        else if (columnEnd == columnStart) {
            return false;
        }
        else if (Math.abs(rowEnd - rowStart) + Math.abs(columnEnd - columnStart) != 2) {
            return false;
        }

        Piece startPiece = chessboard.getTileByCoordinates(rowStart, columnStart).getHost();
        Piece endPiece = chessboard.getTileByCoordinates(rowEnd, columnEnd).getHost();

        if(roundNumber % 2 == 0 && startPiece.getColour().equals("white")){
            return false;
        }
        else if(roundNumber % 2 == 1 && startPiece.getColour().equals("black")){
            return false;
        }
        else if (startPiece == null) {
            return false;
        }
        else if (endPiece != null) {
            return false;
        }
        else if (rowEnd <= rowStart && startPiece.getColour().equals("black")) {
            return false;
        }
        else if (rowEnd >= rowStart && startPiece.getColour().equals("white")) {
            return false;
        }

        return true;
    }

    public boolean isCaptureCorrect(int rowStart, int columnStart, int rowCaptured, int columnCaptured, int rowEnd,
                                    int columnEnd) {

        if (rowStart > Chessboard.getDimensionLength() || rowStart < 0) {
            return false;
        }
        else if (rowCaptured >= Chessboard.getDimensionLength() || rowCaptured < 0) {
            return false;
        }
        else if (rowEnd >= Chessboard.getDimensionLength() || rowEnd < 0) {
            return false;
        }
        else if (columnStart >= Chessboard.getDimensionLength() || columnStart < 0) {
            return false;
        }
        else if (columnCaptured >= Chessboard.getDimensionLength() || columnCaptured < 0) {
            return false;
        }
        else if (columnEnd >= Chessboard.getDimensionLength() || columnEnd < 0) {
            return false;
        }
        else if (columnEnd == columnStart) {
            return false;
        }
        else if (columnEnd == columnCaptured) {
            return false;
        }
        else if (columnCaptured == columnStart) {
            return false;
        }
        else if (rowEnd == rowStart) {
            return false;
        }
        else if (rowEnd == rowCaptured) {
            return false;
        }
        else if (rowCaptured == rowStart) {
            return false;
        }
        else if (Math.abs(rowCaptured - rowStart) + Math.abs(columnCaptured - columnStart) != 2 ||
                Math.abs(rowEnd - rowCaptured) + Math.abs(columnEnd - columnCaptured)!= 2) {
            return false;
        }

        Piece startPiece = chessboard.getTileByCoordinates(rowStart, columnStart).getHost();
        Piece capturedPiece = chessboard.getTileByCoordinates(rowCaptured, columnCaptured).getHost();
        Piece endPiece = chessboard.getTileByCoordinates(rowEnd, columnEnd).getHost();

        if(roundNumber % 2 == 0 && startPiece.getColour().equals("white")){
            return false;
        }
        else if(roundNumber % 2 == 1 && startPiece.getColour().equals("black")){
            return false;
        }
        else if (startPiece == null) {
            return false;
        }
        else if (capturedPiece == null) {
            return false;
        }
        else if (capturedPiece.getColour().equals(startPiece.getColour())) {
            return false;
        }
        else if (endPiece != null) {
            return false;
        }
        else if (rowEnd <= rowStart && startPiece.getColour().equals("black")) {
            return false;
        }
        else if (rowEnd >= rowStart && startPiece.getColour().equals("white")) {
            return false;
        }

        return true;
    }

    public void printTheChessboard(){

        System.out.println(chessboard);
    }

    public boolean arePiecesLeft(){

        return numberOfBlackPieces != 0 && numberOfWhitePieces != 0;
    }

    public void move(int rowStart, int columnStart, int rowEnd, int columnEnd) {

        Tile startTile = chessboard.getTileByCoordinates(rowStart, columnStart);

        chessboard.getTileByCoordinates(rowEnd, columnEnd).setHost(startTile.getHost());

        startTile.setHost(null);
    }

    public void capture(int rowStart, int columnStart, int rowCaptured, int columnCaptured, int rowEnd,
                        int columnEnd){

        Tile startTile = chessboard.getTileByCoordinates(rowStart, columnStart);

        chessboard.getTileByCoordinates(rowEnd, columnEnd).setHost(startTile.getHost());

        startTile.setHost(null);

        Tile capturedTile = chessboard.getTileByCoordinates(rowCaptured, columnCaptured);

        if(capturedTile.getColour().equals("white")){

            numberOfWhitePieces--;
        }
        else{

            numberOfBlackPieces--;
        }

        capturedTile.setHost(null);
    }

    public boolean isAnyMoveAvailable(){

        int dimensionLength = Chessboard.getDimensionLength();

        Tile tile = null;

        for(int i = 0; i < dimensionLength; i++){
            for(int j = 0; j < dimensionLength; j++){

                if((i + j) % 2 == 1){

                    tile = chessboard.getTileByCoordinates(i, j);

                    if(tile.getHost() != null && tile.getHost().getColour().equals("black") && roundNumber % 2 == 0){

                        if(tile.getHost().getType().equals("standard")){

                            if(isMoveCorrect(i, j, i + 1, j + 1)
                                    || isMoveCorrect(i, j, i + 1, j - 1)){
                                return true;
                            }
                        }
                        else{

                            //king
                        }
                    }

                    else if(tile.getHost() != null && tile.getHost().getColour().equals("white") && roundNumber % 2 == 1){

                        if(tile.getHost().getType().equals("standard")){

                            if(isMoveCorrect(i, j, i - 1, j + 1)
                                    || isMoveCorrect(i, j, i - 1, j - 1)){
                                return true;
                            }
                        }
                        else {

                            //king
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isAnyCaptureAvailable(){

        int dimensionLength = Chessboard.getDimensionLength();

        Tile tile = null;

        for(int i = 0; i < dimensionLength; i++){
            for(int j = 0; j < dimensionLength; j++){

                if((i + j) % 2 == 1){

                    tile = chessboard.getTileByCoordinates(i, j);

                    if(tile.getHost() != null && tile.getHost().getColour().equals("black") && roundNumber % 2 == 0){

                        if(tile.getHost().getType().equals("standard")){

                            if(isCaptureCorrect(i, j, i + 1, j + 1, i + 2, j + 2)
                                    || isCaptureCorrect(i, j, i + 1, j - 1, i + 2, j - 2)){
                                return true;
                            }
                        }
                        else{

                            //king
                        }
                    }

                    else if(tile.getHost() != null && tile.getHost().getColour().equals("white") && roundNumber % 2 == 1){

                        if(tile.getHost().getType().equals("standard")){

                            if(isCaptureCorrect(i, j, i - 1, j + 1, i - 2, j - 2)
                                    || isCaptureCorrect(i, j, i - 1, j - 1, i - 2, j - 2)){
                                return true;
                            }
                        }
                        else {

                            //king
                        }
                    }
                }
            }
        }

        return false;
    }
}
