import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Merge {
	private ArrayList<Integer> data;
	private Graphics g;
	 
	public Merge(ArrayList<Integer> data, Graphics g) {
		this.data = data;
		this.g = g;
	}
	
	public ArrayList<Integer> getData(){
		return data;
	}
	public void setData(ArrayList<Integer> data) {
		this.data = data;
	}
	
	private String convert(int value) {
		String number = Integer.toString(value);
		return number;
	}
	
	private void drawArrow(int x, int y) {
		g.drawLine(x, y, x-3, y+6);
		g.drawLine(x, y, x+3, y+6);
	}
	
//	secured is the index in which afterwards, the numbers have reached its final location
	private void writeNumber(int x, int y, int start, int end) {
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setFont(bold);
		for(int i = start; i <= end; i++) {
			String number = convert(data.get(i));
			g.drawString(number, x, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
	}
	
	// function to find the start x position of a set of numbers given the middle position
	private int findWidth(int start, int end, int middle) {
		int width = 0;
		for(int i = start; i <= end; i++) {
			String number = convert(data.get(i));
			width += g.getFontMetrics().stringWidth(number);
			if(i != end) {
				 width += 20;
			}
//			System.out.println("width: " + width);
		}
		return width;
	}
	
	// level is to keep track of what level is currently being displayed
	public ArrayList<Integer> sort(int start, int end, int level, int leftEdge, int rightEdge) {
		// math stuff to calculate the middle x position of the next row 
		int newMiddle = (rightEdge + leftEdge)/2;
		int width = findWidth(start, end, newMiddle);
		
		int startX = newMiddle - width/2;
		int startY =  100 + (level * 50);
		System.out.println("Start: " + start + " End: " + end);
		
		writeNumber(startX, startY, start, end);	
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		if(end - start <= 0) {
			return data;
		}
		
		sort(start,((end+start)/2), level + 1, leftEdge, newMiddle); //left split
		sort((end+start)/2 + 1, end, level + 1, newMiddle, rightEdge); //right split
		int left = start;
		int right = end;
		
		while(left <= right) {
			if(data.get(left) < data.get(right)) {
				right--;
			}else{
				if(data.get(left) >= data.get(right)) {
					int temp = data.get(left);
					data.set(left,data.get(right));
					data.set(right, temp);
//					System.out.println("Switched " + data.get(left) + " with " + data.get(right));
//					keep incrementing right in order to screen for other values that are less than left before incrementing left
					if(!(right <= left)) {
						System.out.println("switched values " + data.get(left) + " with " + data.get(right));
						right--;
						continue;
					}
					right = end;
					left++;
				}

			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// clear out the section of the frame that shows the current list after recursion and is going back up
		if(level == 0) {
			System.out.println("start:" + start);
			width = findWidth(start, end, newMiddle);
			System.out.println("final width: " + width);
		}
		g.clearRect(startX, startY-50, width, 50);
		// draw updated version of list when going back up
		writeNumber(startX, startY, start, end);
		return data;
	}
	
	public void writeSorted(int x, int y) {
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setColor(Color.BLACK);
		g.setFont(bold);
		g.clearRect(0, 0, 600, 600);
		g.drawString("Final sorted list:", 50, 100);
		for(int i = 0; i < data.size(); i++) {
			String number = convert(data.get(i));
			g.drawString(number, x + 200, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
	}
}

// fixed issue where the top layer wans't being cleared properly