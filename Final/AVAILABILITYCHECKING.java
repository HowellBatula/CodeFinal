package Final;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class AVAILABILITYCHECKING extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<String> reservations;
    private Date selectedDate;
    private String selectedStartTime;
    private String selectedEndTime;
    private JTextArea textArea;
    protected JComponent frame;

    private String userName;
    private String contactNumber;

    public AVAILABILITYCHECKING(String name, String number) {
    	methods m = new methods();
    	this.userName = name;
        this.contactNumber = number;
        initialize();
        reservations = m.loadReservations();
    }

    private void initialize() {
        setTitle("Calendar");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // Create and configure components
        JDateChooser Date = new JDateChooser();
        Date.setBounds(186, 151, 243, 20);
        Date.setForeground(new Color(0, 0, 0));
        Date.setDateFormatString("MMMM d, y");

        List<String> startOptions = generateTimeOptions();
        DefaultComboBoxModel<String> comboBoxModelStart = new DefaultComboBoxModel<>(startOptions.toArray(new String[0]));

        List<String> endOptions = generateTimeOptions();
        DefaultComboBoxModel<String> comboBoxModelEnd = new DefaultComboBoxModel<>(endOptions.toArray(new String[0]));

        JComboBox<String> startBox = new JComboBox<>(comboBoxModelStart);
        startBox.setBounds(186, 305, 195, 22);

        JComboBox<String> endBox = new JComboBox<>(comboBoxModelEnd);
        endBox.setBounds(186, 355, 195, 22);

        // Add components to the content pane
        getContentPane().add(Date);
        getContentPane().add(startBox);
        getContentPane().add(endBox);

        
        JScrollPane reserveArea = new JScrollPane();
        reserveArea.setBounds(500, 150, 410, 250);
        getContentPane().add(reserveArea);

        textArea = new JTextArea();
        reserveArea.setViewportView(textArea);
        textArea.setEditable(false);

        // Register event listeners
        Date.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedDate = Date.getDate();
                
            }
        });

        endBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedEndTime = (String) endBox.getSelectedItem();
              
            }
        });

        startBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedStartTime = (String) startBox.getSelectedItem();
            
            }
        });

        JPanel checkPanel = new JPanel();
        checkPanel.setOpaque(false);
        checkPanel.setBounds(206, 420, 140, 40);
        getContentPane().add(checkPanel);
        checkPanel.setLayout(null);

        JLabel checkBtn = new JLabel("");
        checkBtn.setBounds(0, 0, 140, 40);
        checkPanel.add(checkBtn);
        checkBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/availbtn.png")));

        JLabel checkBig = new JLabel("");
        checkBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/availBig.png")));
        checkBig.setBounds(0, 0, 140, 40);
        checkBig.setVisible(false);
        checkPanel.add(checkBig);

        JPanel seePanel = new JPanel();
        seePanel.setOpaque(false);
        seePanel.setBounds(637, 103, 140, 45);
        getContentPane().add(seePanel);
        seePanel.setLayout(null);

        JLabel seeBig = new JLabel("");
        seeBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservationsBig.png")));
        seeBig.setHorizontalAlignment(SwingConstants.CENTER);
        seeBig.setBounds(0, 0, 140, 45);
        seeBig.setVisible(false);
        seePanel.add(seeBig);

        JLabel seeBtn = new JLabel("");
        seeBtn.setBounds(0, 0, 140, 45);
        seePanel.add(seeBtn);
        seeBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservationsbtn.png")));

        JPanel reservePanel = new JPanel();
        reservePanel.setOpaque(false);
        reservePanel.setBounds(460, 480, 100, 57);
        getContentPane().add(reservePanel);
        reservePanel.setLayout(null);
        reservePanel.setVisible(false);

        JLabel reserveBtn = new JLabel("");
        reserveBtn.setHorizontalAlignment(SwingConstants.CENTER);
        reserveBtn.setBounds(0, 11, 100, 36);
        reservePanel.add(reserveBtn);
        reserveBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservebtn.png")));

        JLabel reserveBig = new JLabel("");
        reserveBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reserveBig.png")));
        reserveBig.setBounds(0, 11, 100, 35);
        reserveBig.setVisible(false);
        reservePanel.add(reserveBig);

        // Event Listeners
        checkPanel.addMouseListener(new ButtonMouseAdapter(checkBtn, checkBig) {
            @Override
            public void mouseClicked(MouseEvent e) {
                methods m = new methods();
                if (selectedDate != null && selectedStartTime != null && selectedEndTime != null) {
                    Date currentDate = new Date();
                    if (selectedDate.after(currentDate)) {
                        String selectedStartDateTime = getFormattedDateTime(selectedDate, selectedStartTime);
                        String selectedEndDateTime = getFormattedDateTime(selectedDate, selectedEndTime);
                        if (m.isTimeRangeAvailable(selectedStartDateTime, selectedEndDateTime)) {
                            String selectedDateTime = selectedStartDateTime + " - " + selectedEndDateTime;
                            boolean alreadyReserved = isReservationAlreadyExists(selectedDateTime);
                            if (!alreadyReserved) {
                                JOptionPane.showMessageDialog(AVAILABILITYCHECKING.this, "Date and time are available.", "Available",
                                        JOptionPane.INFORMATION_MESSAGE);
                                reservePanel.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(AVAILABILITYCHECKING.this, "Date and time are already reserved.", "Reserved", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(AVAILABILITYCHECKING.this, "Date and time are not available.", "Not Available", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(AVAILABILITYCHECKING.this, "Please select a date and time range.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            private boolean isReservationAlreadyExists(String selectedDateTime) {
                try {
                    File reservationFile = new File("reservation.txt");
                    if (reservationFile.exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(reservationFile));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.equals(selectedDateTime)) {
                                reader.close();
                                return true;
                            }
                        }
                        reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        });


        seePanel.addMouseListener(new ButtonMouseAdapter(seeBtn, seeBig) {
            @Override
            public void mouseClicked(MouseEvent e) {
                methods m = new methods();
            	List<String> reservationData = m.loadReservations();
                StringBuilder builder = new StringBuilder();
                for (String reservation : reservationData) {
                    builder.append(reservation).append("\n");
                }
                textArea.setText(builder.toString());
            }
        });

        reservePanel.addMouseListener(new ButtonMouseAdapter(reserveBtn, reserveBig) {
            @Override
            public void mouseClicked(MouseEvent e) {
                String reserveName = userName;
                String contacts = contactNumber;
                RESERVATIONMANAGEMENT reservationManagement = new RESERVATIONMANAGEMENT(reserveName, contacts, selectedDate, selectedStartTime, selectedEndTime);

                // Launch the RESERVATIONMANAGEMENT window
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            reservationManagement.frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                dispose();
            }
        });

        JLabel availImg = new JLabel("");
        availImg.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/AVAILABILITYCHECKING.png")));
        availImg.setBounds(0, 0, 984, 561);
        getContentPane().add(availImg);
    }

    // Generate time options for combo boxes
    private List<String> generateTimeOptions() {
        List<String> timeOptions = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        for (int i = 0; i < 18; i++) {
            timeOptions.add(format.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 60);
        }

        return timeOptions;
    }

    // Get formatted date and time string
    private String getFormattedDateTime(Date date, String time) {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, y hh:mm a");
        String dateStr = format.format(date);
        return dateStr.substring(0, dateStr.length() - 8) + time;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AVAILABILITYCHECKING frame = new AVAILABILITYCHECKING("John Doe", "123456789");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Mouse Adapter for button clicks
    abstract class ButtonMouseAdapter extends MouseAdapter {
        private JLabel normalIcon;
        private JLabel hoverIcon;

        public ButtonMouseAdapter(JLabel normalIcon, JLabel hoverIcon) {
            this.normalIcon = normalIcon;
            this.hoverIcon = hoverIcon;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            normalIcon.setVisible(false);
            hoverIcon.setVisible(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            hoverIcon.setVisible(false);
            normalIcon.setVisible(true);
        }
    }

    // Methods class to handle reservation data
    class methods {
        public List<String> loadReservations() {
            List<String> reservations = new ArrayList<>();
            try {
                File file = new File("reservation.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty()) {
                        reservations.add(line.trim());
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return reservations;
        }

        public boolean isTimeRangeAvailable(String startDateTime, String endDateTime) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                Date startRange = dateFormat.parse(startDateTime);
                Date endRange = dateFormat.parse(endDateTime);

                File reservationFile = new File("reservation.txt");
                if (reservationFile.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(reservationFile));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" - ");
                        if (parts.length == 2) {
                            Date existingStart = dateFormat.parse(parts[0]);
                            Date existingEnd = dateFormat.parse(parts[1]);

                            if (startRange.before(existingEnd) && endRange.after(existingStart)) {
                                reader.close();
                                return false; // Overlapping reservation found
                            }
                        }
                    }
                    reader.close();
                }
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
            return true; // No overlapping reservations found
        }
    }
}
