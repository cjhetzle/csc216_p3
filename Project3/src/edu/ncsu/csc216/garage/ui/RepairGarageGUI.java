package edu.ncsu.csc216.garage.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.garage.model.dealer.Manageable;
import edu.ncsu.csc216.garage.model.dealer.ServiceManager;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * GUI for the repair garage
 * @author Marshall Skelton
 *
 */
public class RepairGarageGUI extends JFrame {
	/**serial ID */
	private static final long serialVersionUID = 1L;
	/** vehicle list table */
	private JList<String> waitingList;
	/** service bay list table */
	private JList<String> garage;
	/** list model used for Garage */
	private DefaultListModel<String> vehicleModel = new DefaultListModel<String>();
	/** list model used for */
	private DefaultListModel<String> serviceModel = new DefaultListModel<String>();
	/** service manager */
	private Manageable serviceMgr;
	/** sbl counter */
	private int sblcounter;
	/** filter for list */
	private String filter = "";

	/**
	 * GUI constructor
	 * @param filename the file for initializing the Vehicle list
	 */
	public RepairGarageGUI(String filename) {
		super("Service Garage Manager");
		try {
			if (filename == null) {				
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filename = fc.getSelectedFile().getAbsolutePath();
				}			
			}
		} 
		catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Cannot initialize repair chart from " + filename, 
					"Error", JOptionPane.ERROR_MESSAGE);
			endExecution();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Seating chart file not found. ", "Error", JOptionPane.ERROR_MESSAGE);
			endExecution();
		}
		
		//construct service manager
		Scanner importFile = null;
		try {
			importFile = new Scanner(new File(filename));
			serviceMgr = new ServiceManager(importFile);
			filter = "";
			listUpdate();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "Seating chart file not found. ", "Error", JOptionPane.ERROR_MESSAGE);
			endExecution();
		}
		
		//get the container
		Container c = getContentPane();
		c.setLayout(new GridLayout());
		setSize(800, 500);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//end of container
		
		//p1 waitlist for vehicles
		JPanel p1 = new JPanel();
		//p2 garage list panel
		JPanel p2 = new JPanel();
		//panel labels
		JLabel l1 = new JLabel("Vehicles Awaiting Service");
		JLabel l2 = new JLabel("Service Bays");
		p1.add(l1);
		p2.add(l2);
		
		JLabel disfil = new JLabel("Display filter:");
		JTextField df = new JTextField();
		df.setPreferredSize(new Dimension(100, 30));
		df.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent event1) {
				/** unused method */
			}
			@Override
			public void insertUpdate(DocumentEvent event2) {
				filter = df.getText();
                listUpdate();
			}
			@Override
			public void removeUpdate(DocumentEvent event3) {
				filter = df.getText();
                listUpdate();
				
			}
			
		});
		
		//add vehicle button
		JButton av = new JButton("Add New Vehicle");
		av.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(av)) {
					UserDialog pane = new UserDialog();
					pane.setVisible(true);
					Vehicle v = pane.getNewVehicle();
					if (v != null) {
						serviceMgr.putOnWaitingList(v);
						vehicleModel.addElement(v.toString());
					}
				}
			}
		});
		
		//edit vehicle button
		JButton ev = new JButton("Edit Selected Vehicle");
		ev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//edit vehicle
				if (e.getSource().equals(ev)) {
					int index = waitingList.getSelectedIndex();
					if (index > -1) {
						filter = df.getText();
						Vehicle v = (Vehicle) serviceMgr.getWaitingItem(filter, index);
						UserDialog pane = new UserDialog(v);
						pane.setVisible(true);
						v = pane.getNewVehicle();
						if (v != null) {
							serviceMgr.remove(filter, index);
							vehicleModel.remove(index);
							serviceMgr.putOnWaitingList(v);
							listUpdate();
						}
					}
				}
			}
		});
		
		JButton rv = new JButton("Remove Selected Vehicle");
		rv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove selected vehicle
				if (e.getSource().equals(rv)) {
					int index = waitingList.getSelectedIndex();
					filter = df.getText();
					serviceMgr.remove(filter, index);
					vehicleModel.remove(index);
				}
			}
		});
		
		JButton asb = new JButton("Add Service Bay");
		asb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add service bay to list
				if (e.getSource().equals(asb)) {
					serviceMgr.addNewBay();
					Scanner input = new Scanner(serviceMgr.printServiceBays());
					serviceModel.removeAllElements();
					while (input.hasNextLine()) {
						serviceModel.addElement(input.nextLine());
					}
					input.close();
				}
			}
		});
		
		JButton fsb = new JButton("Fill Service Bays");
		fsb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serviceMgr.fillServiceBays();
				serviceModel.removeAllElements();
				Scanner input = new Scanner(serviceMgr.printServiceBays());
				while (input.hasNextLine()) {
					serviceModel.addElement(input.nextLine());
				}
				input.close();
				filter = df.getText();
				listUpdate();
			}
		});
		JButton fv = new JButton("Finish Repair of Selected Vehicle");
		fv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = garage.getSelectedIndex();
				System.out.print(index);
				serviceMgr.releaseFromService(index);
				serviceModel.removeAllElements();
				Scanner input = new Scanner(serviceMgr.printServiceBays());
				while (input.hasNextLine()) {
					serviceModel.addElement(input.nextLine());
				}
				input.close();
			}
		});
		
		p2.add(asb);
		p2.add(fsb);
		p2.add(fv);
		p1.add(av);
		p1.add(ev);
		p1.add(rv);
		p1.add(disfil);
		p1.add(df);
		
		//list for vehicle list
		waitingList = new JList<String>(vehicleModel);
		JScrollPane listScrollPane = new JScrollPane(waitingList);
		waitingList.setLayoutOrientation(JList.VERTICAL);
		p1.add(listScrollPane, BorderLayout.CENTER);
		
		//list for service bay list
		garage = new JList<String>(serviceModel);
		JScrollPane listScrollPane2 = new JScrollPane(garage);
		listScrollPane2.setPreferredSize(new Dimension(300, 300));
		garage.setLayoutOrientation(JList.VERTICAL);
		Scanner input = new Scanner(serviceMgr.printServiceBays());
		sblcounter = 8;
		for (int i = 0; i < sblcounter; i++) {
			serviceModel.addElement(input.nextLine());
		}
		input.close();
		p2.add(listScrollPane2, BorderLayout.CENTER);
		
		JButton quit = new JButton("Quit");
		p2.add(quit, BorderLayout.SOUTH);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
		c.add(p1);
		c.add(p2);
		setVisible(true);
	}
	/**
	 * main method for setting up the GUI
	 * @param args string 
	 */
	public static void main(String[] args) {
		if (args.length > 0)
			new RepairGarageGUI(args[0]);
		else
			new RepairGarageGUI(null);
		
	}
	/**
	 * display filter helper method displays the updated vehicle list that meets the filter
	 */
	private void listUpdate() {
		
		for (int i = 0; i < vehicleModel.size(); i++) {
			vehicleModel.remove(i);
			i--;
		}
		int i = 0;
		Vehicle v = null;
		while ((v = (Vehicle) serviceMgr.getWaitingItem(filter, i)) != null) {
			vehicleModel.addElement(v.toString());
			i++;
		}
	}
	
	/**
	 * Private Method - Ends execution of the program.
	 */
	private void endExecution() {
		System.exit(0);
	}
}
