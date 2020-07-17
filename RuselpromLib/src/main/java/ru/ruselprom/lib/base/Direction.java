package ru.ruselprom.lib.base;

public enum Direction {
	CLOCKWISE(1),
	COUNTERCLOCKWISE(0);
	private int value;

	private Direction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	public static int getValue(Direction direction) {
		return direction.getValue();
	}
}
