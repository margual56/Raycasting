package maths;

import java.awt.Color;

public class Vector {
	public double x, y, z;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public Vector() {
	}

	public Vector(double d) {
		this.x = d;
		this.y = d;
		this.z = d;
	}

	public Vector xyy() {
		return new Vector(x, y, y);
	}

	public Vector xxy() {
		return new Vector(x, x, y);
	}

	public Vector xyx() {
		return new Vector(x, y, x);
	}

	public Vector yxx() {
		return new Vector(y, x, x);
	}

	public Vector yxy() {
		return new Vector(y, x, y);
	}

	public Vector yyx() {
		return new Vector(y, y, x);
	}

	public Vector xz() {
		return new Vector(x, z);
	}

	public Vector xy() {
		return new Vector(x, y);
	}

	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public static double length(Vector p) {
		return Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
	}

	public void normalize() {
		double l = this.length();

		if (l == 0)
			return;

		this.x = this.x / l;
		this.y = this.y / l;
		this.z = this.z / l;
	}

	public static Vector normalize(Vector p) {
		double l = p.length();

		if (l == 0)
			return null;

		return new Vector(p.x / l, p.y / l, p.z / l);
	}

	public double norm() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public static double norm(Vector p) {
		return Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
	}

	public void add(Vector p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
	}

	public void sub(Vector p) {
		this.x -= p.x;
		this.y -= p.y;
		this.z -= p.z;
	}

	public void sub(double d) {
		this.x -= d;
		this.y -= d;
		this.z -= d;
	}

	public void add(double d) {
		this.x += d;
		this.y += d;
		this.z += d;
	}

	public void mult(double d) {
		this.x *= d;
		this.y *= d;
		this.z *= d;
	}

	public void abs() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
	}

	public Color toColor() {
		return new Color((float) this.x, (float) this.y, (float) this.z);
	}

	public static Vector add(Vector a, Vector b) {
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector sub(Vector a, Vector b) {
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector sub(Vector a, double d) {
		return new Vector(a.x - d, a.y - d, a.z - d);
	}

	public static Vector mult(Vector p, double d) {
		return new Vector(p.x * d, p.y * d, p.z * d);
	}

	public static Vector abs(Vector p) {
		return new Vector(Math.abs(p.x), Math.abs(p.y), Math.abs(p.z));
	}

	public static double dot(Vector a, Vector b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	public Vector copy() {
		return new Vector(this.x, this.y, this.z);
	}

	public static Vector copy(Vector p) {
		return new Vector(p.x, p.y);
	}

	public static Vector cross(Vector a, Vector b) {
		double i = a.y * b.z - b.y * a.z;
		double j = -(a.x * b.z - b.x * a.z);
		double k = a.x * b.y - b.x * a.y;

		return new Vector(i, j, k);
	}

	public static Vector max(Vector a, Vector b) {
		if (a.length() < b.length())
			return b;

		return a;
	}

	public static float angleBetween(Vector a, Vector b) {
		return (float) Math.acos(Vector.dot(a, b) / (a.length() * b.length()));
	}

	public static Vector clamp(Vector v, double min, double max) {
		Vector p = v.copy();

		p.x = clamp(p.x, min, max);
		p.y = clamp(p.y, min, max);
		p.z = clamp(p.z, min, max);

		return p;
	}

	private static double clamp(double x, double min, double max) {
		if (x < min)
			return min;

		if (x > max)
			return max;

		return x;
	}

	public static Vector div(Vector p, double d) {
		return new Vector(p.x / d, p.y / d, p.z / d);
	}

	public static Vector fromAngle(double angle) {
		return new Vector(Math.cos(angle), Math.sin(angle));
	}

	public double dist(Vector other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
	}

	public static double dist(Vector a, Vector b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
	}
}
