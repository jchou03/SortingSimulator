import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// a type of sorting that takes in a data set and arranges it so the lowest value is at the front
public class Selection {
	private ArrayList<Integer> data;
	private Graphics g;
	
	public Selection(ArrayList<Integer> data, Graphics g) {
		this.data = data;
		this.g = g;
	}
	
	private String convert(int value) {
		String number = Integer.toString(value);
		return number;
	}
	
//	draw an arrow under the current index the algorithem is at
	private void drawArrow(int x, int y) {
		g.drawLine(x, y, x-3, y+6);
		g.drawLine(x, y, x+3, y+6);
	}
	
	public void writeSorted(int x, int y) {
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setColor(Color.BLACK);
		g.setFont(bold);
		g.clearRect(0, 0, 600, 600);
		g.drawString("Final sorted list:", x, y);
		for(int i = 0; i < data.size(); i++) {
			String number = convert(data.get(i));
			g.drawString(number, x + 200, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
	}
	
	private void writeNumber(int y, int minIndex, int secured, int currentIndex) {
		g.clearRect(0, 0, 600, 600);
		int x = 300;
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setFont(bold);
		for(int i = 0; i < data.size(); i++) {
			String number = convert(data.get(i));
			if(i == currentIndex) {
				drawArrow(x+(g.getFontMetrics().stringWidth(number)/2), y+5);
			}
			if(i == minIndex) {
				g.setColor(Color.RED);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else if(i < secured && i < data.size()){
				g.setColor(Color.BLUE);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else {
				g.drawString(number, x, y);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}
			
		}
	}
	
	public void sort() {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < data.size(); i++) {
			int minIndex = i;
			int secured = 0 + i;
			min = Integer.MAX_VALUE;
			for(int j = i; j < data.size(); j++) {
				if(data.get(j) < min) {
					min = data.get(j);
					minIndex = j;
				}
				writeNumber(100, minIndex, secured, j);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			if(minIndex != 0) {
				data.remove(minIndex);
				data.add(i, min);
			}
		}
		writeSorted(50,100);
	}
	
	public ArrayList<Integer> getData(){
		return data;
	}
	public void setData(ArrayList<Integer> data) {
		this.data = data;
	}

}

// next lesson fix issue where all numbers become the min numbers and lose some data points in the data set