package _02_Pixel_Art;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JFrame window;
	JFrame f;
	JPanel j;
	JLabel l;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	private JButton Save;
	private JButton Load;
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		Save = new JButton("Click here to save your masterpiece");
		Load = new JButton("load a masterpiece");
		Load.addActionListener(this);
		Save.addActionListener(this);
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(Save);
		window.add(Load);
		gp.repaint();
		
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(Save)) {
		try {
			FileWriter fielRiter = new FileWriter("src/_02_Pixel_Art/thing.txt");
		fielRiter.write("" + gp.pixels.length);
		fielRiter.write("\n"+ ""+ gp.pixels[0].length);
			for(int i = 0; i<gp.pixels.length; i++){
			for(int j = 0; j<gp.pixels[i].length;j++) {
				fielRiter.write("\n" + "" + gp.pixels[i][j].color.getRed());
				fielRiter.write("\n" + "" + gp.pixels[i][j].color.getBlue());
				fielRiter.write("\n" + "" + gp.pixels[i][j].color.getGreen());
			}
		}
		fielRiter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch bloc
			e1.printStackTrace();
		}
		}
		if(e.getSource().equals(Load)) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/_02_Pixel_Art/thing.txt"));
			String s1 = br.readLine();
			String s2 = br.readLine();
			int i1 = Integer.parseInt(s1);
			int i2 = Integer.parseInt(s2);
			Pixel[][] p = new Pixel[i1][i2];

			for(int i= 0; i<i1; i++) {
				for(int j = 0; j< p[i].length; j++) {
					p[i][j] = new Pixel(i*10, j*10);
					p[i][j].color = new Color(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
					}
			}
			gp.repaint();
			gp.pixels = p;
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
	}
}
