import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quick {
//	class to hold both the list of data as well as the pivot index
	private class Info{
		public ArrayList<Integer> list;
		public int pivot;
		public Info(int pivot, ArrayList<Integer> data) {
			this.list = data;
			this.pivot = pivot;
		}
	}
	private Graphics g;
	
//	don't need data or constructor due to sort just taking in what is needed and not referencing data but you need it for importing graphics
	public Quick(Graphics g) {
		this.g = g;
	}
	private Font bold = new Font(new String("bold"), Font.BOLD, 20);
//	
//	public ArrayList<Integer> getData(){
//		return data;
//	}
//	public void setData(ArrayList<Integer> data) {
//		this.data = data;
//	}
	
//	functions for drawing the list of numbers onto the JFrame
	private String convert(int value) {
		String number = Integer.toString(value);
		return number;
	}
//	secured is the index in which afterwards, the numbers have reached its final location
	private void writeNumber(int x, int y, List<Integer> list, int pivotIndex) {
		g.setFont(bold);
		g.setColor(Color.BLACK);
		for(int i = 0; i < list.size(); i++) {
			if(i == pivotIndex) {
				String number = convert(list.get(i));
				g.setColor(Color.RED);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else {
				String number = convert(list.get(i));
				g.drawString(number, x, y);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}
		}
	}
	
	private void writeColor(int x, int y, List<Integer> left, int pivot, List<Integer> right) {
		g.setFont(bold);
		g.setColor(Color.CYAN);
		for(int i = 0; i < left.size(); i++) {
			String number = convert(left.get(i));
			g.drawString(number, x, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
		g.setColor(Color.RED);
		String pivotString = convert(pivot);
		g.drawString(pivotString, x, y);
		g.setColor(Color.MAGENTA);
		x += g.getFontMetrics().stringWidth(pivotString) + 20;
		for(int i = 0; i < right.size(); i++) {
			String number = convert(right.get(i));
			g.drawString(number, x, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
	}
	
//	public void writeSorted(int x, int y) {
//		Font bold = new Font(new String("bold"), Font.BOLD, 20);
//		g.setColor(Color.BLACK);
//		g.setFont(bold);
//		g.clearRect(0, 0, 600, 600);
//		g.drawString("Final sorted list:", 50, 100);
//		for(int i = 0; i < data.size(); i++) {
//			String number = convert(data.get(i));
//			g.drawString(number, x, y);
//			x += g.getFontMetrics().stringWidth(number) + 20;
//		}
//	}
	
	// function to find the start x position of a set of numbers given the middle position
	private int findWidth(List<Integer> list, int middle) {
		int width = 0;
		for(int i = 0; i < list.size(); i++) {
			String number = convert(list.get(i));
			width += g.getFontMetrics().stringWidth(number);
			if(i != list.size()) {
				 width += 20;
			}
//			System.out.println("width: " + width);
		}
		return width;
	}
	
//	sorts the list based on a pivot number, with numbers of lower value going before the pivot, and larger numbers going after the pivot, then doing the same with the sublists created by this wort
	public List<Integer> sort(List<Integer> list, int level, int leftEdge, int rightEdge){
//		values for setting up the position where it is drawn
		int newMiddle = (rightEdge + leftEdge)/2;
		int width = findWidth(list, newMiddle);
		System.out.println("newMiddle: " + newMiddle);
		int startX = newMiddle - width/2;
		int startY =  100 + (level * 75);
		
		writeNumber(startX, startY, list, list.size() - 1);
		
		if(list.size() <= 1) {
			return list;
		}
		
		Info info = partition(list.size()-1, list, level, leftEdge, rightEdge);
		List<Integer> left = sort(info.list.subList(0, info.pivot), level + 1, leftEdge, newMiddle);
		
		List<Integer> right = sort(info.list.subList(info.pivot+1, info.list.size()), level + 1, newMiddle, rightEdge);
		List<Integer> result = new ArrayList<Integer>();

//		combine the left list, the pivot, and the right list into one arraylist to pass back up
		result.addAll(left);
//		int currentX = writeColor(startX, startY, result, Color.MAGENTA);
		result.add(info.list.get(info.pivot));
		String number = convert(info.list.get(info.pivot));
		width += g.getFontMetrics().stringWidth(number);
//		g.drawString(number, currentX, startY);
//		writeColor(currentX, startY, info.list.get(info.pivot), Color.RED);
		result.addAll(right);
		int newPivot = info.list.get(info.pivot) /*- (right.size()-1)*/;
		
//		stuff for drawing the sorted version with color
		g.clearRect(startX, startY - 50, width + 30, 51);
		writeColor(startX, startY, left, newPivot, right);
		
		return result;
	}
	
	public Info partition(int pivot, List<Integer> list, int level, int leftEdge, int rightEdge){
		int newMiddle = (rightEdge + leftEdge)/2;
		int width = findWidth(list, newMiddle);
		
		int startX = newMiddle - width/2;
		int startY =  125 + (level * 75);
		
//		new list where the partition sorted list will go
		ArrayList<Integer> temp = new ArrayList<Integer>();
//		or do temp.add(data.get(pivot)) after and have the constructor empty
		
		int pivotIndex = 0;
		temp.add(list.get(pivot)); //gives a stack overflow error for some reason
		for(int i = 0; i < list.size()-1; i++) {
//			throw lesser values than the pivot to the front of temp
			if(list.get(i) < list.get(pivot)) {
//				add the new value that is less than the pivot at its location then moving pivotIndex up to match the new location
				temp.add(pivotIndex, list.get(i));
				pivotIndex++;
			}
//			throw larger values than the pivot 
			else{
				temp.add(list.get(i));
			}
			g.clearRect(startX, startY - 20, width, 20);
			writeNumber(startX, startY, temp, pivotIndex);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			
		}
		return new Info(pivotIndex, temp);
	}
	// work on implemeting color for representing the different sublists that are being sorted
}