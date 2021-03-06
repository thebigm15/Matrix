package com.matrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MatrixTest {
	
	private IntegerMatrix constructorTester;
	private IntegerMatrix setAtIndexTester;
	public static IntegerMatrix a;
	public static IntegerMatrix b;
	public static IntegerMatrix c;
	public static IntegerMatrix d;
	public static IntegerMatrix diffRows;
	public static IntegerMatrix diffCols;
	public static IntegerMatrix zeroMatrix;
	public static IntegerMatrix oneMatrix;
	public static IntegerMatrix twoMatrix;
	public static IntegerMatrix aTimesB;
	public static IntegerMatrix transposedC;
	public static IntegerMatrix integerIdentity3;
	public static DoubleMatrix doubleIdentity3;
	
	@BeforeAll public static void initialize() throws InterruptedException {
		diffRows = new IntegerMatrix(4,3);
		diffCols = new IntegerMatrix(3,5);
		a = new IntegerMatrix();
		b = new IntegerMatrix();
		c = new IntegerMatrix(3,1);
		for(int i = 0; i < a.getRows(); i++) {
			for(int j = 0; j < b.getRows(); j++) {
				a.setAtIndex(i, j, i + j);
				b.setAtIndex(i, j, (i + 1) * (j + 1));
			}
		}
		for(int i = 0; i < c.getRows(); i++) {
			for(int j = 0; j < c.getCols(); j++) {
				c.setAtIndex(i, j, i + 10);
			}
		}
		int[][] tmp = {{1,3,5},{3,6,9},{5,9,13}};
		d = new IntegerMatrix(tmp);
		int[][] zeroArr = {{0,0,0},{0,0,0},{0,0,0}};
		zeroMatrix = new IntegerMatrix(zeroArr);
		int[][] oneArr = {{1,1,1},{1,1,1},{1,1,1}};
		oneMatrix = new IntegerMatrix(oneArr);
		int[][] twoArr = {{2,2,2},{2,2,2},{2,2,2}};
		twoMatrix = new IntegerMatrix(twoArr);
		int[][] aTimesBArr = {{8,16,24},{14,28,42},{20,40,60}};
		aTimesB = new IntegerMatrix(aTimesBArr);
		int[][] transposedArr = {{10,11,12}};
		transposedC = new IntegerMatrix(transposedArr);
		int[][] integerIdentityArr = {{1,0,0},{0,1,0},{0,0,1}};
		integerIdentity3 = new IntegerMatrix(integerIdentityArr);
		double[][] doubleIdentityArr = {{1.0,0,0},{0,1.0,0},{0,0,1.0}};
		doubleIdentity3 = new DoubleMatrix(doubleIdentityArr);
	}
	
	@Test public void constructorTest() {
		constructorTester = new IntegerMatrix();
		assertEquals(3, constructorTester.getRows());
		assertEquals(3, constructorTester.getCols());
	}
	
	@Test public void constructorWithIntegerArgumentsTest() {
		constructorTester = new IntegerMatrix(2,2);
		assertEquals(2, constructorTester.getRows());
		assertEquals(2, constructorTester.getCols());
	}
	
	@Test public void constructorWithArrayArgumentTest() {
		int[][] arr = {{1,2,3},{4,5,6}};
		constructorTester = new IntegerMatrix(arr);
		assertEquals(constructorTester.getRows(), 2);
		assertEquals(constructorTester.getCols(), 3);
		assert(constructorTester.getAtIndex(0, 0) == 1);
	}
	
	@Test public void constructorWithMatrixArgumentTest() {
		constructorTester = new IntegerMatrix(c);
		assertEquals(3, constructorTester.getRows());
		assertEquals(1, constructorTester.getCols());
		assert(10 == constructorTester.getAtIndex(0, 0));
	}
	
	@Test public void getAtIndexTest() {
		assert(2 == a.getAtIndex(0, 2));
	}
	
	@Test public void getAtIndexExceptionTest() {
		Throwable e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> a.getAtIndex(100, 0));
		assertEquals(("Index Out of Bounds"
				+ "\nCheck that your values for row and col are correct"
				+ " and try again."), e.getMessage());
		e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> a.getAtIndex(0, 100));
		assertEquals(("Index Out of Bounds"
				+ "\nCheck that your values for row and col are correct"
				+ " and try again."), e.getMessage());
	}
	
	@Test public void setAtIndexTest() {
		setAtIndexTester = new IntegerMatrix();
		setAtIndexTester.setAtIndex(0, 0, 1);
		assert(setAtIndexTester.getAtIndex(0, 0) == 1);
	}
	
	@Test public void setAtIndexExceptionTest() {
		Throwable e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> a.setAtIndex(100, 0, 0));
		assertEquals(("Index Out of Bounds"
				+ "\nCheck that your values for row and col are correct"
				+ " and try again."), e.getMessage());
		e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> a.setAtIndex(0, 100, 0));
		assertEquals(("Index Out of Bounds"
				+ "\nCheck that your values for row and col are correct"
				+ " and try again."), e.getMessage());
	}
	
	@Test public void addTest() {
		assertEquals(true, d.equals(a.add(b)));
	}
	
	@Test public void addExceptionTest() {
		Throwable e = assertThrows(IllegalArgumentException.class, () -> a.add(diffCols));
		assertEquals(("\nCannot add matrices with dimesnions of "
					+ a.getRows() + "x" + a.getCols() + " and " + diffCols.getRows() + "x" + diffCols.getCols()
					+ ".\nTry different matrices where all dimensions match."), e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> c.add(diffRows));
		assertEquals(("\nCannot add matrices with dimesnions of "
					+ c.getRows() + "x" + c.getCols() + " and " + diffRows.getRows() + "x" + diffRows.getCols()
					+ ".\nTry different matrices where all dimensions match."), e.getMessage());
	}
	
	@Test public void subtractTest() {
		assertEquals(true, d.subtract(d).equals(zeroMatrix));
	}
	
	@Test public void subtractExceptionTest() {
		Throwable e = assertThrows(IllegalArgumentException.class, () -> a.subtract(diffCols));
		assertEquals(("\nCannot subtract matrices with dimesnions of "
					+ a.getRows() + "x" + a.getCols() + " and " + diffCols.getRows() + "x" + diffCols.getCols()
					+ ".\nTry different matrices where all dimensions match."), e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> c.subtract(diffRows));
		assertEquals(("\nCannot subtract matrices with dimesnions of "
					+ c.getRows() + "x" + c.getCols() + " and " + diffRows.getRows() + "x" + diffRows.getCols()
					+ ".\nTry different matrices where all dimensions match."), e.getMessage());
	}
	
	@Test public void scalarMultiplicationTest() {
		assertEquals(true, oneMatrix.multiply(2).equals(twoMatrix));
	}
	
	@Test public void multiplyTest() {
		assertEquals(true, aTimesB.equals(a.multiply(b)));
	}
	
	@Test public void multiplyExceptionTest() {
		Throwable e = assertThrows(IllegalArgumentException.class, () -> diffCols.multiply(diffRows));
		assertEquals(("\nCannot multiply matrices with dimesnions of "
				+ diffCols.getRows() + "x" + diffCols.getCols() + " and " + diffRows.getRows() + "x" + diffRows.getCols()
				+ ".\nTry different matrices where the columns of the first \nmatch the rows of the second."), e.getMessage());
	}
	
	@Test public void transposeTest() {
		assertEquals(true, transposedC.equals(c.transpose()));
	}
	
	@Test public void compareToTest() {
		assertEquals(Integer.MIN_VALUE, diffRows.compareTo(a));
		assertEquals(Integer.MAX_VALUE, diffCols.compareTo(b));
		assertEquals(0, a.compareTo(a));
		assertEquals(9, oneMatrix.compareTo(zeroMatrix));
	}
	
	@Test public void equalsTest() {
		assertEquals(false, a.equals(diffRows));
		assertEquals(false, a.equals(diffCols));
		assertEquals(false, a.equals(b));
		assertEquals(false, a.equals(new IntegerMatrix(5,5)));
		assertEquals(true, a.equals(a));
	}
	
	@Test public void toStringTest() {
		assertEquals("[0 0 0]\n[0 0 0]\n[0 0 0]", zeroMatrix.toString());
	}
	
	@Test public void integerArgumentIdentityMatrixTest() {
		assertEquals(true, integerIdentity3.equals(MatrixOperations.identityMatrix(integerIdentity3.getRows())));
	}
	
	@Test public void integerMatrixArgumentIdentityMatrixTest() {
		assertEquals(true, integerIdentity3.equals(MatrixOperations.identityMatrix(integerIdentity3)));
	}
	
	@Test public void doubleMatrixArgumentIdentityMatrixTest() {
		assertEquals(true, doubleIdentity3.equals(MatrixOperations.identityMatrix(doubleIdentity3)));
	}
}
