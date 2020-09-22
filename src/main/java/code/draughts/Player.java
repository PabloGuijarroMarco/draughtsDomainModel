package main.java.code.draughts;

abstract class Player {

	protected Token token;
	protected Board board;

	Player(Token token, Board board) {
		assert token != null && !token.isNull();
		assert board != null;
		
		this.token = token;
		this.board = board;
	}

	void play() {
			this.move();
	}

	Error checkPutCoordinateError(Coordinate coordinate) {
		Error error = Error.NULL_ERROR;
		if (!this.board.isEmpty(coordinate)) {
			error = Error.NOT_EMPTY;
		}
		return error;
	}

	private void move() {
		Error error;
		Coordinate originCoordinate;
		do {
			originCoordinate = this.getCoordinateOriginToRemove();
			error = this.checkMoveOriginCoordinateError(originCoordinate);
		} while (error != Error.NULL_ERROR);
		Coordinate targetCoordinate;
		do {
			targetCoordinate = this.getCoordinateTargetToMove();
			error = this.checkMoveTargetCoordinateError(originCoordinate, targetCoordinate);
		} while (error != Error.NULL_ERROR);
		this.board.move(originCoordinate, targetCoordinate);
	}

	abstract Coordinate getCoordinateOriginToRemove();

	abstract Coordinate getCoordinateTargetToMove();

	Error checkMoveOriginCoordinateError(Coordinate originCoordinate) {
		assert originCoordinate != null;
		
		Error error = Error.NULL_ERROR;
		if (!this.board.isOccupied(originCoordinate, this.token)) {
			error = Error.NOT_OWNER;
		}
		return error;
	}

	Error checkMoveTargetCoordinateError(Coordinate originCoordinate, Coordinate targetCoordinate) {
		assert originCoordinate != null;
		
		Error error = Error.NULL_ERROR;
		if (originCoordinate.equals(targetCoordinate)) {
			error = Error.SAME_COORDINATES;
		} 
		if (!this.board.isEmpty(targetCoordinate)) {
			error = Error.NOT_EMPTY;
		}
		if (this.board.isInDiagonal(originCoordinate, targetCoordinate)!=0){
			error = Error.MOVE_NOT_DIAGONAL;
		}
		return error;
	}

	void writeWinner() {
		this.token.write();
		Message.PLAYER_WIN.writeln();
	}
	
	Token getToken() {
		return this.token;
	}

}
