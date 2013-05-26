package com.tass.lifegame.logic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameField {
	private GameField gameField;
	private Set<Integer> set;
	
	private int sideSize = 10;
	
	private static int ELEMENTS = 10;
	
	public TestGameField() {
		set = new TreeSet<Integer>();
		
		for (int i = 0; i < ELEMENTS; i++)
			set.add(i);
	}
	
	@Test
	public void getFieldAfterConstruction() {
		gameField = new GameField(set, sideSize);
		assertTrue(set.equals(gameField.getField()));
	}
	
	@Test
	public void getFieldAfterSetting() {
		gameField = new GameField(null, sideSize);
		
		gameField.setField(set, sideSize);
		assertTrue(set.equals(gameField.getField()));
	}
	
	@Test
	public void getCell() {
		gameField = new GameField(set, sideSize);
		
		for (int i = 0; i < ELEMENTS; i++)
			assertTrue(gameField.getCell(9));
	}
	
	@Test
	public void setCell() {
		gameField = new GameField(null, sideSize);
		
		Random rnd = new Random();
		int value = rnd.nextInt((int) Math.pow(sideSize, 2));
		
		gameField.setCell(value);
		gameField.updateField();
		assertTrue(gameField.getCell(value));
	}
		
	@Test
	public void getValuesToCheck() {
		gameField = new GameField(set, sideSize);
		
		Set<Integer> tmp = new TreeSet<Integer>();
		for (Integer i : set) {
			tmp.add(new Integer(i));
			
			int quotient = i / sideSize;
			int remainder = i % sideSize;
			
			if (quotient != 0 && remainder != 0) {
				tmp.add(new Integer(i - sideSize - 1));
			}
			if (quotient != 0) {
				tmp.add(new Integer(i - sideSize));
			}
			if (quotient != 0 && remainder != (sideSize - 1)) {
				tmp.add(new Integer(i - sideSize + 1));
			}
			if (remainder != 0) {
				tmp.add(new Integer(i - 1));
			}
			if (remainder != (sideSize - 1)) {
				tmp.add(new Integer(i + 1));
			}
			if (quotient != (sideSize - 1) && remainder != 0) {
				tmp.add(new Integer(i + sideSize - 1));
			}
			if (quotient != (sideSize - 1)) {
				tmp.add(new Integer(i + sideSize));
			}
			if (quotient != (sideSize - 1) && remainder != (sideSize - 1)) {
				tmp.add(new Integer(i + sideSize + 1));
			}
		}
		
		int[] tmpValues = new int[tmp.size()];
		Iterator<Integer> iterator = tmp.iterator();
			
		for (int i = 0; i < tmp.size(); i++)
			tmpValues[i] = iterator.next();
		
		int[] values = gameField.getValuesToCheck();
		
		Arrays.sort(tmpValues);
		Arrays.sort(values);

		assertTrue(Arrays.equals(values, tmpValues));
	}
}
