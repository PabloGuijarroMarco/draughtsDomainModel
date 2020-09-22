package main.java.code.draughts;

import java.util.Random;

import main.java.code.utils.ClosedInterval;
import main.java.code.utils.Direction;

class ConcreteCoordinate extends main.java.code.utils.Coordinate implements Coordinate {

	static final ClosedInterval LIMITS = 
		new ClosedInterval(0, Coordinate.DIMENSION);

	ConcreteCoordinate() {
		super();
	}

	ConcreteCoordinate(int row, int column) {
		super(row, column);
		assert ConcreteCoordinate.LIMITS.isIncluded(row);
		assert ConcreteCoordinate.LIMITS.isIncluded(column);
	}
	
	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public Direction getDirection(Coordinate coordinate) {
		assert coordinate != null && !coordinate.isNull();
		
		Direction direction = super.getDirection((main.java.code.utils.Coordinate)coordinate);
		if (direction != Direction.NULL_DIRECTION) {
			return direction;
		}
		if (this.inInverseDiagonal() && coordinate.inInverseDiagonal()) {
			return Direction.INVERSE_DIAGONAL;
		}
		return Direction.NULL_DIRECTION;
	}

	public int getRow(){
		return this.row;
	}

	public int getColumn(){
		return this.column;
	}

	public boolean inInverseDiagonal() {
		return this.row + this.column == Coordinate.DIMENSION - 1;
	}

	@Override
	public void read(String message) {
		assert message != null;
		
		boolean error;
		do {
			super.read(message);
			error = !LIMITS.isIncluded(this.row) || !LIMITS.isIncluded(this.column);
			if (error) {
				Error.WRONG_COORDINATES.writeln();
			}
		} while (error);
	}

	public void random() {
		Random random = new Random(System.currentTimeMillis());
		this.row = random.nextInt(Coordinate.DIMENSION);
		this.column = random.nextInt(Coordinate.DIMENSION);
	}

}
