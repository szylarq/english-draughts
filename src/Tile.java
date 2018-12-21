public class Tile {

    private String colour;
    private Piece host;
    private int positionRow;
    private int positionColumn;

    public Tile(String colour, int positionRow, int positionColumn){

        this.colour = colour;
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

    public String getColour() {
        return colour;
    }

    public Piece getHost() {
        return host;
    }

    public void setHost(Piece host) {
        this.host = host;
    }
}
