import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static ArrayList<Integer> data = new ArrayList<Integer>();
	public static JFrame frame = new JFrame("Sorting Stuff");
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.print("How many numbers do you want to sort?");
		int n = sc.nextInt();
		System.out.println();
		
		int i = 0;
		while(i < n) {
			try {
				System.out.print("Input your number: ");
				int input = sc.nextInt();
				data.add(input);
				System.out.println();
				i++;
			}catch(Exception e) {
				continue;
			}
		}
		
		
		frame.setSize(600,600);

//		setting up buttons for selecting the sorting algorithm 
		JButton bSelect = new JButton("Selection");
		bSelect.setBounds(100, 100, 200, 50);
		JButton bBubble = new JButton("Bubble");
		bBubble.setBounds(100, 200, 200, 50);
		JButton bInsert = new JButton("Insertion");
		bInsert.setBounds(100, 300, 200, 50);
		JButton bMerge = new JButton("Merge");
		bMerge.setBounds(100, 400, 200, 50);
		JButton bQuick = new JButton("Quick");
		bQuick.setBounds(100, 500, 200, 50);
//		JPanel panel = new JPanel();
		

		frame.add(bSelect);
		frame.add(bBubble);
		frame.add(bInsert);
		frame.add(bMerge);
		frame.add(bQuick);

		frame.setLayout(null);
		frame.setVisible(true);

		
		bSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bBubble.setVisible(false);
				bInsert.setVisible(false);
				bMerge.setVisible(false);
				bQuick.setVisible(false);
				chooseSort("select");
			}
		});
		bBubble.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bSelect.setVisible(false);
				bInsert.setVisible(false);
				bMerge.setVisible(false);
				bQuick.setVisible(false);
				chooseSort("bubble");
			}
		});
		bInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bSelect.setVisible(false);
				bBubble.setVisible(false);
				bMerge.setVisible(false);
				bQuick.setVisible(false);
				chooseSort("insertion");
			}
		});
		bMerge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bSelect.setVisible(false);
				bBubble.setVisible(false);
				bInsert.setVisible(false);
				bQuick.setVisible(false);
				chooseSort("merge");
			}
		});
		bQuick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bSelect.setVisible(false);
				bBubble.setVisible(false);
				bInsert.setVisible(false);
				bMerge.setVisible(false);
				chooseSort("quick");
			}
		});
		
		

	}

	public static void chooseSort(String typeOfSort) {
		frame.getGraphics().clearRect(0, 0, 600, 600);
		switch(typeOfSort) {
		case "select":
			Selection selectSort = new Selection(data, frame.getGraphics());
			System.out.println(data.toString());
			selectSort.sort();
			System.out.println("made it out of sort");
			//		List<Integer> banana = quickSort.sort(data);
			System.out.println("sorted list: " + data.toString());
			break;
		case "bubble":
			Bubble bubbleSort = new Bubble(data, frame.getGraphics());
			System.out.println(data.toString());
			bubbleSort.sort();
//			List<Integer> sortedList = mergeSort.sort(0, data.size()-1, 0, 0, 600);
			System.out.println("made it out of sort");
			//		List<Integer> banana = quickSort.sort(data);
			System.out.println("sorted list: " + data.toString());
			break;
		case "insertion":
			Insertion insertionSort = new Insertion(data, frame.getGraphics());
			System.out.println(data.toString());
			insertionSort.sort2();
			System.out.println("made it out of sort");
			System.out.println("sorted list: " + data.toString());
			break;
		case "merge":
			Merge mergeSort = new Merge(data, frame.getGraphics());
			System.out.println(data.toString());
			List<Integer> sortedList = mergeSort.sort(0, data.size()-1, 0, 0, 600);
			System.out.println("made it out of sort");
			System.out.println("sorted list: " + sortedList.toString());
			break;
		case "quick":
			Quick quickSort = new Quick(frame.getGraphics());
			System.out.println(data.toString());
			List<Integer> sortedList2 = quickSort.sort(data, 0, 0, 600);
			System.out.println("made it out of sort");
			System.out.println("sorted list: " + sortedList2.toString());
			break;	
		}
	}
	
//	next lesson visualize other sorts(done with bubble and selection)
}