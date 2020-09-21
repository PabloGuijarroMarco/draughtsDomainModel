package main.java.code.draughts;

import main.java.code.utils.Direction;

public class Board {
    private final int NUMBER_PLAYERS;
    private Coordinate[][] coordinates;

    Board(int numberPlayers) {
        assert numberPlayers > 0;

        this.NUMBER_PLAYERS = numberPlayers;
        this.coordinates = new Coordinate[this.NUMBER_PLAYERS][Coordinate.PIECES];
        int contWhite=0;
        int contBlack=0;
        for(int row = 0; row < Coordinate.DIMENSION; row++){
            for(int column = 0; column < Coordinate.DIMENSION; column++) {
                if(row!=3 && row!=4){
                    if(row<=2){
                        if((row%2==1 && column%2==0) || (row%2==0 && column%2==1)){
                            this.coordinates[0][contWhite] = new ConcreteCoordinate(row, column);
                            contWhite++;
                        }
                    }else{
                        if((row%2==1 && column%2==0) || (row%2==0 && column%2==1)){
                            this.coordinates[1][contBlack] = new ConcreteCoordinate(row, column);
                            contBlack++;
                        }
                    }
                }
            }
        }
    }

    void write() {
        Message.SEPARATOR.writeln();
        for (int i = 0; i<Coordinate.DIMENSION; i++) {
            Message.VERTICAL_LINE_LEFT.write();
            for (int j = 0; j<Coordinate.DIMENSION; j++) {
                this.getToken(new ConcreteCoordinate(i, j)).write();
                Message.VERTICAL_LINE_CENTERED.write();
            }
            Message.VERTICAL_LINE_RIGHT.writeln();
            Message.SEPARATOR.writeln();
        }
    }

    private Token newToken(int ordinal) {
        return Token.get(ordinal);
    }

    private Token getToken(Coordinate coordinate) {
        assert coordinate != null && !coordinate.isNull();

        for (int i = 0; i < this.NUMBER_PLAYERS; i++) {
            for (int j = 0; j < Coordinate.PIECES; j++) {
                if (this.coordinates[i][j].equals(coordinate)) {
                    return Token.get(i);
                }
            }
        }
        return Token.NULL_TOKEN;
    }

    boolean isNotEmpty(int player) {
        for (int j = 0; j < Coordinate.DIMENSION; j++) {
            if (this.coordinates[player][j].isNull()) {
                return false;
            }
        }
        return true;
    }

    void put(Coordinate coordinate, Token token) {
        assert coordinate != null && !coordinate.isNull();
        assert token != null && !token.equals(Token.NULL_TOKEN);

        Coordinate[] coordinates = this.coordinates[token.ordinal()];
        System.out.println(coordinates[0]);
        int i = 0;
        while (!coordinates[i].isNull()) {
            i++;
        }
        coordinates[i] = coordinate;
    }

    void move(Coordinate originCoordinate, Coordinate targetCoordinate) {
        assert originCoordinate != null;
        assert !originCoordinate.isNull();
        assert targetCoordinate != null;
        assert !targetCoordinate.isNull();

        Token token = this.getToken(originCoordinate);
        this.remove(originCoordinate);
        this.put(targetCoordinate, token);
    }

    private void remove(Coordinate coordinate) {
        assert coordinate != null;
        assert !coordinate.isNull();

        for (int i = 0; i < this.NUMBER_PLAYERS; i++) {
            for (int j = 0; j < Coordinate.DIMENSION; j++) {
                if (this.coordinates[i][j].equals(coordinate)) {
                    this.coordinates[i][j] = NullCoordinate.instance();
                }
            }
        }
    }

    boolean isOccupied(Coordinate coordinate, Token token) {
        assert coordinate != null;
        assert !coordinate.isNull();

        return this.getToken(coordinate) == token;
    }

    boolean isEmpty(Coordinate coordinate) {
        assert coordinate != null;
        assert !coordinate.isNull();

        return this.isOccupied(coordinate, Token.NULL_TOKEN);
    }

    boolean isOver(Token token) {
        assert token != null;
        assert !token.equals(Token.NULL_TOKEN);

        if (this.isNotEmpty(token.ordinal())) {
            return false;
        }
        return true;
    }
}
