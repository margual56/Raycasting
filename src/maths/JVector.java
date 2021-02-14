package maths;

import java.awt.Color;

public class JVector {
	public double x, y, z;

	public JVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public JVector(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public JVector() {
	}

	public JVector(double d) {
		this.x = d;
		this.y = d;
		this.z = d;
	}

	public JVector xyy() {
		return new JVector(x, y, y);
	}

	public JVector xxy() {
		return new JVector(x, x, y);
	}

	public JVector xyx() {
		return new JVector(x, y, x);
	}

	public JVector yxx() {
		return new JVector(y, x, x);
	}

	public JVector yxy() {
		return new JVector(y, x, y);
	}

	public JVector yyx() {
		return new JVector(y, y, x);
	}

	public JVector xz() {
		return new JVector(x, z);
	}

	public JVector xy() {
		return new JVector(x, y);
	}

	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public static double length(JVector p) {
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

	public static JVector normalize(JVector p) {
		double l = p.length();

		if (l == 0)
			return null;

		return new JVector(p.x / l, p.y / l, p.z / l);
	}

	public double norm() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public static double norm(JVector p) {
		return Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
	}

	public void add(JVector p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
	}

	public void sub(JVector p) {
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

	public static JVector add(JVector a, JVector b) {
		return new JVector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static JVector sub(JVector a, JVector b) {
		return new JVector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static JVector sub(JVector a, double d) {
		return new JVector(a.x - d, a.y - d, a.z - d);
	}

	public static JVector mult(JVector p, double d) {
		return new JVector(p.x * d, p.y * d, p.z * d);
	}

	public static JVector abs(JVector p) {
		return new JVector(Math.abs(p.x), Math.abs(p.y), Math.abs(p.z));
	}

	public static double dot(JVector a, JVector b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	public JVector copy() {
		return new JVector(this.x, this.y, this.z);
	}

	public static JVector copy(JVector p) {
		return new JVector(p.x, p.y);
	}

	public static JVector cross(JVector a, JVector b) {
		double i = a.y * b.z - b.y * a.z;
		double j = -(a.x * b.z - b.x * a.z);
		double k = a.x * b.y - b.x * a.y;

		return new JVector(i, j, k);
	}

	public static JVector max(JVector a, JVector b) {
		if (a.length() < b.length())
			return b;

		return a;
	}

	public static float angleBetween(JVector a, JVector b) {
		return (float) Math.acos(JVector.dot(a, b) / (a.length() * b.length()));
	}

	public static JVector clamp(JVector v, double min, double max) {
		JVector p = v.copy();

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

	public static JVector div(JVector p, double d) {
		return new JVector(p.x / d, p.y / d, p.z / d);
	}

	public static JVector fromAngle(double angle) {
		return new JVector(Math.cos(angle), Math.sin(angle));
	}

	public double dist(JVector other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
	}

	public static double dist(JVector a, JVector b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
	}
}
