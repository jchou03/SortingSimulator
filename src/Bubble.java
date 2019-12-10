import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Bubble implements MouseListener{
	private ArrayList<Integer> data;
	private Graphics g;

	public Bubble(ArrayList<Integer> data, Graphics g) {
		this.data = data;
		this.g = g;
	}
	
	public ArrayList<Integer> getData(){
		return data;
	}
//	public void setData(ArrayList<Integer> data) {
//		this.data = data;
//	}
//	
	public ArrayList<Integer> weirdSort(ArrayList<Integer> data){
		int i = data.size()-1;
		//iterate through the 
		while(i > 0) {
			int k = 0;
			int j = 1;
			int element = data.get(k);
			int temp = data.get(j);
			
			//iterate through the data, switching the larger value to the back
			while(k < i) {
				if(temp < element) {
					System.out.println("Switched " + data.get(k).toString() + " with " + temp);
					data.set(k, temp);
					data.set(j, element);
				}
				k++;
				j++;
				if(k < data.size()) {
					element = data.get(k);
				}
				if(j < data.size()) {
					temp = data.get(j);
				}
			}
			i--;
		}
		return data;
	}
	
	private String convert(int value) {
		String number = Integer.toString(value);
		return number;
	}
//	secured is the index in which afterwards, the numbers have reached its final location
	private void writeNumber(int y, int index, int secured) {
		int x = 300;
		Font bold = new Font(new String("bold"), Font.BOLD, 20);
		g.setFont(bold);
		for(int i = 0; i < data.size(); i++) {
			if(i == index || i == index + 1) {
				String number = convert(data.get(i));
				g.setColor(Color.RED);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else if(i > secured && i < data.size()){
				String number = convert(data.get(i));
				g.setColor(Color.BLUE);
				g.drawString(number, x, y);
				g.setColor(Color.BLACK);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}else {
				String number = convert(data.get(i));
				g.drawString(number, x, y);
				x += g.getFontMetrics().stringWidth(number) + 20;
			}
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
			g.drawString(number, x, y);
			x += g.getFontMetrics().stringWidth(number) + 20;
		}
	}
	
	public void sort(){
		g.drawString("Past step", 50, 50);
		writeNumber(50, -1, data.size());
		int i = 0;
		while(i < data.size() - 1){
			System.out.println("i:" + i);
			int j = 0;
			int secured = data.size() - 1 - i;
			while(j < secured) {
				int temp = data.get(j);
				g.clearRect(0, 0, 600, 600);
				g.drawString("Past step:", 50, 50);
				if(i > 0 && j == 0) {
					writeNumber(50, secured, secured);
				}else {
					writeNumber(50,j-1,secured);
				}
				if(temp > data.get(j + 1)) {	
					data.set(j, data.get(j + 1));
					data.set(j + 1, temp);
				}
				g.drawString("Current step: ", 50, 100);
				writeNumber(100, j, secured); 
				System.out.println("j:" + j);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
				j++;
			}
			i++;
		}
		writeSorted(300,100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
