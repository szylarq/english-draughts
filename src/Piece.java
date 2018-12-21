public class Piece {

    private String colour; //black or white
    private String type; //either king or standard piece;
    private char charType;

        public Piece(String colour, String type/*, int positionRow, int positionColumn*/){

            this.colour = colour;
            this.type = type;
            this.getCharPiece();
        }

    public String getColour(){
        return colour;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public char getCharType(){
        return charType;
    }

    private void getCharPiece(){

        if(this.colour.equals("black")){
            charType = 'b';
        }
        else {
            charType = 'w';
        }
    }
}
