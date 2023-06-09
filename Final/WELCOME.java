package Final;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class WELCOME extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WELCOME window = new WELCOME();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WELCOME() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel backgroundImg = new JLabel("New label");
		backgroundImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/LASTHOME.png")));
		backgroundImg.setBounds(0, 0, 984, 561);
		getContentPane().add(backgroundImg);
		
		JPanel enterPanel = new JPanel();
		enterPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enterPanel.setOpaque(false);
		enterPanel.setBounds(450, 460, 100, 50);
		frame.getContentPane().add(enterPanel);
		enterPanel.setLayout(null);
		
		JLabel enterBtn = new JLabel("");
		enterBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		enterBtn.setHorizontalAlignment(SwingConstants.CENTER);
		enterBtn.setBounds(10, 10, 80, 30);
		enterPanel.add(enterBtn);
		enterBtn.setBorder(null);
		enterBtn.setBackground(new Color(255, 255, 255));
		enterBtn.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/welcomenterbtn.png")));
		
		JLabel hoverEnter = new JLabel("");
		enterPanel.add(hoverEnter);
		hoverEnter.setHorizontalTextPosition(SwingConstants.CENTER);
		hoverEnter.setHorizontalAlignment(SwingConstants.CENTER);
		hoverEnter.setVisible(false);
		hoverEnter.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/enterBig.png")));
		
		JLabel welcomeImg = new JLabel("");
		welcomeImg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		welcomeImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/LASTHOME.png")));
		welcomeImg.setBounds(0, 0, 984, 561);
		frame.getContentPane().add(welcomeImg);
		
		JPanel landingPagePanel = new JPanel();
		hoverEnter.setBounds(0, 0, 100, 50);
		enterPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				backgroundImg.setVisible(false);
				enterPanel.setVisible(false);
				welcomeImg.setVisible(false);
				landingPagePanel.setVisible(true);
			}
			public void mouseEntered(MouseEvent e) {
				enterBtn.setVisible(false);
				hoverEnter.setVisible(true);
				
			}
			public void mouseExited(MouseEvent e) {
				enterBtn.setVisible(true);
				hoverEnter.setVisible(false);
			}
			public void mousePressed(MouseEvent e) {
				enterBtn.setVisible(true);
				hoverEnter.setVisible(false);
			}
			public void mouseReleased(MouseEvent e) {
				enterBtn.setVisible(false);
				hoverEnter.setVisible(true);
			}	
		});
		landingPagePanel.setBounds(0, 0, 984, 561);
		landingPagePanel.setVisible(false);
		frame.getContentPane().add(landingPagePanel);
		
		landingPagePanel.setLayout(null);
		
		JPanel residentPanel = new JPanel();
		residentPanel.setBounds(130, 95, 280, 340);
		landingPagePanel.add(residentPanel);
		residentPanel.setOpaque(false);
		residentPanel.setLayout(null);
		
		JLabel ResidentImg = new JLabel("");
		ResidentImg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		ResidentImg.setBounds(10, 65, 260, 260);
		residentPanel.add(ResidentImg);
		ResidentImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/residentlabel (1).png")));
		
		JLabel biggerResidentImg = new JLabel("");
		residentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				RESIDENTLOGIN.main(null);
				
			}
			public void mouseEntered(MouseEvent e) {
				ResidentImg.setVisible(false);
				biggerResidentImg.setVisible(true);
				
			}
			public void mouseExited(MouseEvent e) {
				biggerResidentImg.setVisible(false);
				ResidentImg.setVisible(true);
				
			}
		});
		biggerResidentImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/residentlabel (1) (1).png")));
		biggerResidentImg.setHorizontalAlignment(SwingConstants.CENTER);
		biggerResidentImg.setBounds(10, 65, 260, 260);
		biggerResidentImg.setVisible(false);
		residentPanel.add(biggerResidentImg);
		
		JPanel guestPanel = new JPanel();
		guestPanel.setBounds(570, 95, 280, 340);
		landingPagePanel.add(guestPanel);
		guestPanel.setLayout(null);
		guestPanel.setOpaque(false);
		
		JLabel GuestLabel = new JLabel("");
		GuestLabel.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/guestlabell (1).png")));
		GuestLabel.setBounds(10, 65, 265, 260);
		guestPanel.add(GuestLabel);
		
		JLabel biggerGuestImg = new JLabel("");
		guestPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
							}
			public void mouseEntered(MouseEvent e) {
				GuestLabel.setVisible(false);
				biggerGuestImg.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				biggerGuestImg.setVisible(false);
				GuestLabel.setVisible(true);
			}
		});
		biggerGuestImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/guestlabell (1) (1).png")));
		biggerGuestImg.setBounds(0, 60, 260, 270);
		biggerGuestImg.setVisible(false);
		guestPanel.add(biggerGuestImg);
		
		JLabel landingpageImg = new JLabel("");
		landingpageImg.setIcon(new ImageIcon(WELCOME.class.getResource("/IMAGES/LASTHOME.png")));
		landingpageImg.setBounds(0, 0, 984, 561);
		landingPagePanel.add(landingpageImg);
		
		
	}
}