package ru.ruselprom.lib.fet.round;

public class RadiusAndEdgeIndices {
	private double radius;
	private int[] edgeIndices;
	public RadiusAndEdgeIndices(double radius, int[] edgeIndices) {
		this.radius = radius;
		this.edgeIndices = edgeIndices;
	}
	public double getRadius() {
		return radius;
	}
	public int[] getEdgeIndices() {
		return edgeIndices;
	}
}
