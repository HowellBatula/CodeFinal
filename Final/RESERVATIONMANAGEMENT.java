package Final;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class RESERVATIONMANAGEMENT extends JFrame{

	JFrame frame;
	 private String userName;
	 private String contactNumber;
	 private Date date;
	 private String startTime;
	 private String endTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	  public RESERVATIONMANAGEMENT(String userName, String contactNumber,Date selectedDate, String startTime, String endTime) {
	        this.userName = userName;
	        this.contactNumber = contactNumber;
	        this.date=selectedDate;
	        this.startTime = startTime;
	        this.endTime = endTime;

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
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel reserveBtn = new JLabel("");
		reserveBtn.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String selectedStartDateTime = getFormattedDateStartTime(date, startTime);
		        String selectedEndDateTime = getFormattedDateEndTime(date, endTime);
		        String selectedDateTime = selectedStartDateTime + " - " + selectedEndDateTime;

		        String purpose = JOptionPane.showInputDialog(RESERVATIONMANAGEMENT.this, "Please enter the purpose:", "Reservation Purpose", JOptionPane.QUESTION_MESSAGE);
		        if (purpose != null && !purpose.isEmpty()) {
		            try {
		                FileWriter reservationsWriter = new FileWriter("reservation.txt", true);
		                FileWriter additionalReservationsWriter = new FileWriter("additional_reservations.txt", true);

		                reservationsWriter.write(selectedDateTime + "\n");
		                additionalReservationsWriter.write(userName + "," + contactNumber + "," + purpose + "," + selectedDateTime + "\n");

		                reservationsWriter.close();
		                additionalReservationsWriter.close();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		            JOptionPane.showMessageDialog(RESERVATIONMANAGEMENT.this, "Thank You!", "Date Reserved", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(RESERVATIONMANAGEMENT.this, "Please enter a valid purpose!", "Reservation Purpose", JOptionPane.WARNING_MESSAGE);
		        }
		    }

			private String getFormattedDateStartTime(Date date, String startTime) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = dateFormat.format(date);
		        return formattedDate + " " +startTime;			
			}
			private String getFormattedDateEndTime(Date date, String endTime) {
			    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    String formattedDate = dateFormat.format(date);
			    return formattedDate + " " + endTime;
			}
		});
		reserveBtn.setBounds(470, 490, 80, 36);
		reserveBtn.setIcon(new ImageIcon(RESERVATIONMANAGEMENT.class.getResource("/IMAGES/reservebtn.png")));
		frame.getContentPane().add(reserveBtn);
		
		JLabel lblNewLabel = new JLabel(userName);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setBounds(389, 133, 221, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel contactShow = new JLabel(contactNumber);
		contactShow.setHorizontalAlignment(SwingConstants.CENTER);
		contactShow.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		contactShow.setBounds(389, 200, 221, 26);
		frame.getContentPane().add(contactShow);
		
		JLabel startTimeShow = new JLabel(startTime);
		startTimeShow.setHorizontalAlignment(SwingConstants.CENTER);
		startTimeShow.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		startTimeShow.setBounds(142, 317, 221, 26);
		frame.getContentPane().add(startTimeShow);
		
		JLabel endTimeShow = new JLabel(endTime);
		endTimeShow.setHorizontalAlignment(SwingConstants.CENTER);
		endTimeShow.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		endTimeShow.setBounds(638, 322, 221, 26);
		frame.getContentPane().add(endTimeShow);
		
		JLabel dateShow = new JLabel();
	    dateShow.setHorizontalAlignment(SwingConstants.CENTER);
	    dateShow.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
	    dateShow.setBounds(389, 279, 221, 26);
	    frame.getContentPane().add(dateShow);

	    // Convert the selectedDate to a string representation
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = dateFormat.format(date);
	    dateShow.setText(dateString);
		
		
		JLabel managementImg = new JLabel("");
		managementImg.setBounds(0, 0, 984, 561);
		managementImg.setIcon(new ImageIcon(RESERVATIONMANAGEMENT.class.getResource("/IMAGES/RESERVATIONMANAGEMENT.png")));
		frame.getContentPane().add(managementImg);
	}
}