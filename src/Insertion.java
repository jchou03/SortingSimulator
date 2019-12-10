import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

// goes through each element in the array and moves behind the data point that it should be behind.
public class Insertion {
	private ArrayList<Integer> data;
	private Graphics g;
	
	public Insertion(ArrayList<Integer> data, Graphics g) {
		this.data = data;
		this.g = g;
	}
	
	public ArrayList<Integer> getData(){
		return data;
	}
	public void setData(ArrayList<Integer> data) {
		this.data = data;
	}
	
	// check from the front (goes through twice)
	public void sort() {
		for(int i = 1; i < data.size(); i++) {
			int temp = data.get(i);
			for(int j = 0; j < i; j++) {
				if(data.get(i) < data.get(j)) {
					data.remove(i);
					data.add(j, temp);
					break;
				}
			}
		}
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
	private void writeNumber(int y, int index, int compare) {
		int x = 300;
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setFont(bold);
		for(int i = 0; i < data.size(); i++) {
			String number = convert(data.get(i));
			if(i == index) {
				g.setColor(Color.RED);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else if(i == compare){
				drawArrow(x+g.getFontMetrics().stringWidth(number)/2,y+5);
				g.drawString(number, x, y);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else {
				g.drawString(number, x, y);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}
		}
	}
	
	// check from behind (goes through once)
	public void sort2() {
		for(int i = 1; i < data.size(); i++) {
			int temp = data.get(i);
			for(int j = i-1; j >= -1; j--) {
				g.clearRect(0, 0, 600, 600);
				writeNumber(200, i, j);
				if(j == -1 || temp >= data.get(j)) {
					data.remove(i);
					data.add(j+1, temp);
					break;
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("j: " + j);
				
			}
			writeSorted(50,100);
		}
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
