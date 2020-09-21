package main.java.code.draughts;

class MachinePlayer extends Player {

	MachinePlayer(Token token, Board board) {		
		super(token, board);
	}

	private Coordinate getCoordinate(Message message){
		assert message != null;
		
		Coordinate coordinate = new ConcreteCoordinate();
		coordinate.random();
		return coordinate;
	}

	@Override
	Coordinate getCoordinateOriginToRemove() {
		return this.getCoordinate(Message.COORDINATE_TO_REMOVE);
	}

	@Override
	Coordinate getCoordinateTargetToMove() {
		return this.getCoordinate(Message.COORDINATE_TO_MOVE);
	}
	
}
