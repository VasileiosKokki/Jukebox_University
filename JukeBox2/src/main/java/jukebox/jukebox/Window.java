package jukebox.jukebox;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

// δημιουργεί το γραφικό περιβάλλον χρήστη
public class Window extends JFrame {

    // καταστάσεις του GUI
    private final int NO_FILES_OPENED = -1;
    private final int NEW_FILE_OPENED = -2;
    private final int SONG_IS_PAUSED = -3;
    private final int SONG_IS_STOPPED = -4;

    private static boolean FirstTime = true; // true αν δεν έχει παιχτεί κανένα τραγούδι ακόμα
    private int flag; // σημαία για τις καταστάσεις του GUI καθώς και το τρέχων τραγούδι που παίζει
    private Song song; // αποθηκεύει το τραγούδι ή playlist
    private String path; // ο φακέλος ή αρχείο που επιλέγει ο χρήστης
    private String[] names; // λίστα με τα paths των τραγουδιών του playlist

    // κουμπιά, ετικέτες και components που μαζί αποτελούν το GUI
    private javax.swing.JButton jButton1; // play
    private javax.swing.JButton jButton2; // next
    private javax.swing.JButton jButton3; // pause
    private javax.swing.JButton jButton4; // stop
    private javax.swing.JButton jButton5; // open file
    private javax.swing.JButton jButton6; // τα κουμπία από 6 εώς 25 είναι η λίστα από τραγούδια στο αριστερό μέρος του παραθύρου
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4; // το τρέχων τραγούδι που παίζει με πράσινα γράμματα κάτω αριστερά
    private javax.swing.JPanel jPanel1; // τα τέσσερα panels που χρωματίζουν διαφορετικές περιοχές του GUI
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1; // δημιουργεί scrollbar στο jTextArea1 αν το κείμενο βγεί εκτός διαστάσεων
    private javax.swing.JSeparator jSeparator1; // η άσπρη γραμμή κάτω από το New Playlist
    private javax.swing.JSeparator jSeparator2; // η άσπρη γραμμή κάτω από το Status Console
    public static javax.swing.JTextArea jTextArea1; // η ενσωματωμένη κονσόλα του GUI
    private javax.swing.JToggleButton jToggleButton1; // repeat
    private javax.swing.JToggleButton jToggleButton2; // random

    // αρχικοποιεί τις βασικές μεταβλητές και τα components του GUI
    public Window() {
        initComponents(); // κάνει κάθε κουμπί και μεταβλητή του GUI ορατή και επίσης τους προσθέτει λειτουργικότητα
        names = new String[20]; // το gui δεν μπορεί να επεξεργαστεί πάνω από 20 τραγούδια
        clearNames(); // αρχικοποίησε κάθε στοιχείο του πίνακα names σε κενό String
        flag = NO_FILES_OPENED; // η αρχική κατάσταση της σημαίας
        path = ""; // το path χρησιμοποιείται από τις συναρτήσεις των jButtons για τον έλεγχο εισόδου από τον χρήστη

    }

    // τοποθετεί τα κουμπία και όλα τα components του GUI στις σωστές θέσεις,
    // τους δίνει χρώματα, φτιάχνει τις διαστάσεις του παραθύρου και ενεργοποιεί τα κουμπία
    // σε περίπτωση που πατηθούν
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // αρχικοποίηση μεταβλητών
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // ενεργοποιεί το κουμπί εξόδου πάνω δεξιά
        setTitle("JukeBox"); // τίτλος
        setBackground(new java.awt.Color(179, 179, 179));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false); // παράθυρο σταθερών διαστάσεων

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musical-note.png"))); // οι εικόνες περιλαμβάνονται στον φάκελο resources
        jLabel1.setPreferredSize(new java.awt.Dimension(512, 512));
        jLabel1.setRequestFocusEnabled(false);

        jToggleButton1.setBackground(new java.awt.Color(33, 33, 33));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repeat.png")));
        jToggleButton1.setBorder(null);

        jToggleButton2.setBackground(new java.awt.Color(33, 33, 33));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/random.png")));
        jToggleButton2.setBorder(null);

        jButton1.setBackground(new java.awt.Color(33, 33, 33));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play-button.png")));
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() { //
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(33, 33, 33));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/next.png")));
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) { // ενεργοποιεί το κουμπί (όταν πατηθεί θα εκτελεστεί ο κώδικας στην συνάρτηση jButton2ActionPerformed
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(33, 33, 33));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pause.png")));
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(33, 33, 33));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stop.png")));
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(33, 33, 33));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel4.setForeground(new java.awt.Color(29, 185, 84));
        jLabel4.setText("");
        jLabel4.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)
                        .addComponent(jToggleButton1)
                        .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jToggleButton1)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton2)
                                                .addComponent(jButton1)
                                                .addComponent(jButton3)
                                                .addComponent(jButton4)
                                                .addComponent(jToggleButton2))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(83, 83, 83));

        jLabel2.setBackground(new java.awt.Color(83, 83, 83));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 16));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("New Playlist");

        jButton5.setBackground(new java.awt.Color(83, 83, 83));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-explorer.png")));
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(83, 83, 83));
        jButton6.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText(" ");
        jButton6.setBorder(null);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(83, 83, 83));
        jButton7.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText(" ");
        jButton7.setBorder(null);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(83, 83, 83));
        jButton8.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText(" ");
        jButton8.setBorder(null);
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(83, 83, 83));
        jButton9.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText(" ");
        jButton9.setBorder(null);
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(83, 83, 83));
        jButton10.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText(" ");
        jButton10.setBorder(null);
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(83, 83, 83));
        jButton11.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText(" ");
        jButton11.setBorder(null);
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(83, 83, 83));
        jButton12.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText(" ");
        jButton12.setBorder(null);
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(83, 83, 83));
        jButton13.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText(" ");
        jButton13.setBorder(null);
        jButton13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(83, 83, 83));
        jButton14.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText(" ");
        jButton14.setBorder(null);
        jButton14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(83, 83, 83));
        jButton15.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText(" ");
        jButton15.setBorder(null);
        jButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(83, 83, 83));
        jButton16.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText(" ");
        jButton16.setBorder(null);
        jButton16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(83, 83, 83));
        jButton17.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText(" ");
        jButton17.setBorder(null);
        jButton17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(83, 83, 83));
        jButton18.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText(" ");
        jButton18.setBorder(null);
        jButton18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(83, 83, 83));
        jButton19.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText(" ");
        jButton19.setBorder(null);
        jButton19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(83, 83, 83));
        jButton20.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText(" ");
        jButton20.setBorder(null);
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(83, 83, 83));
        jButton21.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText(" ");
        jButton21.setBorder(null);
        jButton21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(83, 83, 83));
        jButton22.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText(" ");
        jButton22.setBorder(null);
        jButton22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(83, 83, 83));
        jButton23.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText(" ");
        jButton23.setBorder(null);
        jButton23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(83, 83, 83));
        jButton24.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText(" ");
        jButton24.setBorder(null);
        jButton24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(83, 83, 83));
        jButton25.setFont(new java.awt.Font("Verdana", 0, 14));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText(" ");
        jButton25.setBorder(null);
        jButton25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5)
                                                .addGap(0, 4, Short.MAX_VALUE))
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton5)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton25)
                                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(18, 18, 18));

        jLabel3.setBackground(new java.awt.Color(18, 18, 18));
        jLabel3.setFont(new java.awt.Font("Ubuntu Mono", 1, 21));
        jLabel3.setForeground(new java.awt.Color(29, 185, 84));
        jLabel3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel3.setText("Status Console");
        jLabel3.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(18, 18, 18));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu Mono", 0, 14));
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("");
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setEditable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.CENTER))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(550, 659); // οι διαστάσεις του παραθύρου
        setLocationRelativeTo(null); // το παράθυρο ανοίγει στο κέντρο της οθόνης
    }

    // ο κώδικας αυτής της μεθόδου εκτελείται όταν πατηθεί το play
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (path.equals("") || names[0].equals("")) { // αν δεν έχει ανοιχτεί κανένος φάκελος ή τραγούδι μην κάνεις τίποτα
            return;
        }

        // αν το τραγούδι δεν είναι σταματημένο
        if (!"IDLE".equals(song.getStatus())) {
            checkArguments Arg = new checkArguments();
            if (flag == SONG_IS_PAUSED) { // αν το τραγούδι βρίσκεται σε παύση
                song.resume();  // συνεχίζεται η αναπαραγωγή του τραγουδιού από εκεί που είχε μείνει
                jTextArea1.setText("Player Status : Resumed ");
            }
            else if (flag == SONG_IS_STOPPED && (Arg.checkListType(path) || Arg.checkDirectory(path))) { // αν το τραγούδι πρέπει να σταματήσει και βρισκόμαστε σε λίστα
                song.stop(); // σταμάτα το τραγούδι
                flag = song.getCurrentSongIndex(); // η σημαία ενημερώνεται με το τρέχων τραγούδι

                // και στις τρεις περιπτώσεις συνεχίζεται η αναπαραγωγή του τραγουδιού από την αρχή
                if (jToggleButton1.isSelected() && !jToggleButton2.isSelected()) { // έχει επιλεχθεί μόνο το repeat
                    song.loopGUI();
                }
                else if (jToggleButton2.isSelected() && !jToggleButton1.isSelected()) { // έχει επιλεχθεί μόνο το random
                    song.randomGUI();
                }
                else { // έχουν επιλεχθεί και τα δύο ή κανένα τους
                    song.orderGUI();
                }
            }
            else if (flag == SONG_IS_STOPPED && (Arg.checkFileType(path))) { // αν το τραγούδι πρέπει να σταματήσει και δεν βρισκόμαστε σε λίστα
                song.stop(); // σταμάτα το τραγούδι
                flag = 0; // η σημαία δείχνει στο πρώτο και μοναδικό τραγούδι
                song.playGUI(names[flag]); // ξεκίνα την αναπαραγωγή
            }
        }
    }

    // κώδικας που εκτελείται όταν πατηθεί το κουμπί next
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) { // αν δεν έχει ανοιχτεί νέος φάκελος στο GUI
            if (!"IDLE".equals(song.getStatus())) { // και το τραγούδι δεν είναι σταματημένο
                checkArguments Arg = new checkArguments();
                if (Arg.checkListType(path) || Arg.checkDirectory(path)) { // αν βρισκόμαστε σε λίστα
                    int num = (song.getCurrentSongIndex() + 1) % song.getPlayList().size(); // δείκτης του επόμενου τραγουδιού
                    song.stop(); // σταματάει εντελώς την αναπαραγωγή του τραγουδιού για να γίνει η μετάβαση στο επόμενο
                    flag = num; // η σημαία δείχνει στο επόμενο τραγούδι
                }
                else if (Arg.checkFileType(path)) { // αν υπάρχει μόνο ένα τραγούδι
                    song.stop(); // σταμάτησε την αναπαραγωγή του
                }
                jTextArea1.setText("Player Status : Skipping To Next Song");
            }
        }
    }

    // αν πατηθεί το κουμπί για παύση της αναπαραγωγής
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) { // αν δεν έχει ανοιχτεί νέος φάκελος στο GUI
            if (!"IDLE".equals(song.getStatus())) { // και το τραγούδι δεν είναι σταματημένο
                if (flag != SONG_IS_STOPPED) { // αν το τραγούδι δεν είναι ήδη σταματημένο
                    song.pause(); // σταμάτησε προσωρινά την αναπαραγωγή του
                    flag = SONG_IS_PAUSED; // ενημέρωσε την σημαία
                    jTextArea1.setText("Player Status : Paused");
                }
            }
        }
    }

    // αν πατηθεί το κουμπί για να τελειώσει η αναπαραγωγή
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (flag != NO_FILES_OPENED && flag != NEW_FILE_OPENED) { // αν δεν έχει ανοιχτεί νέος φάκελος στο GUI
            if (!"IDLE".equals(song.getStatus())) { // και το τραγούδι δεν είναι σταματημένο
                song.pause(); // σταματάει έμμεσα την αναπαραγωγή του τραγουδιού (χρησιμοποιούμε pause επειδή είναι ο μόνος τρόπος για να σταματήσουν οι λίστες την αναπαραγωγή)
                flag = SONG_IS_STOPPED; // θέτωντας flag = SONG_IS_STOPPED διαφοροποιούμε την παύση από το σταμάτημα της αναπαραγωγής (stop)
                jTextArea1.setText("Player Status : Stopped"); // ΠΡΟΣΟΧΗ: το terminal θα γράφει "INFO: Status changed to PAUSED"
            }
        }
    }

    // κώδικας που εκτελείται όταν πατηθεί το κουμπί για άνοιγμα νεόυ φακέλου
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser(); // δημιουργεί το παράθυρο για την επιλογή φακέλου, τραγουδιού ή playlist
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // προσθέτει την επιλογή φακέλων πέρα από αρχεία
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { // αν επιλέχθηκε έγκυρος φάκελος ή αρχείο
            clearButtons(); // θέσε το όνομα κάθε jButton σε κενό
            clearNames(); // θέσε κάθε όνομα του πίνακα names σε κενό
            File file;
            String songName;
            path = fileChooser.getSelectedFile().getPath(); // περιέχει το path του αρχείου ή φακέλου που επιλέχθηκε
            song = new Song(path); // δημιουργεί το playlist ή τραγούδι με βάση το επιλεγμένο αρχείο
            flag = NEW_FILE_OPENED; // ενημερώνει τη σημαία
            if (this.song != null) { // αν παίζει ήδη τραγούδι από το προηγούμενο αρχείο ή φάκελο
                song.pause(); // σταμάτησέ το προσωρινά (αν ο χρήστης επιλέξει νέο τραγούδι από την λίστα ή πατήσει play, τότε θα καλεστεί η stop από τις αντίστοιχες μεθόδους)
            }
            jTextArea1.setText("Player Status : New Playlist");
            checkArguments Arg = new checkArguments();
            if (Arg.checkListType(path) || Arg.checkDirectory(path)) { // αν βρισκόμαστε σε λίστα ή σε φάκελο
                if (song.getPlayList().size() < 21) { // και τα τραγούδια δεν είναι περισσότερα από 20
                    for (int i = 0; i < song.getPlayList().size(); i++) { // για κάθε τραγούδι στην λίστα
                        names[i] = song.getPlayList().get(i); // αποθήκευσε το path του στον πίνακα names
                        switch (i) { // τα κουμπία που αναπαριστούν τα τραγούδια της λίστα ξεκινούν από το jButton6 εώς το jButton25
                            case 0: // το jButton6 αντιπροσωπεύει το πρώτο τραγούδι (το στοιχείο names[0] στον πίνακα names)
                                file = new File(song.getPlayList().get(i)); // δημιούργησε το αρχείο από το αντίστοιχο τραγούδι του playlist
                                songName = file.getName().substring(0, file.getName().lastIndexOf(".")); // περιέχει το όνομα του τραγουδιού χώρις την κατάληξη .mp3
                                if (songName.length() > 15) { // αν το όνομα του τραγουδιού είναι πάνω από 15 χαρακτήρες
                                    songName = songName.substring(0, 12); // αφαίρεσε τους τρεις τελευταίους
                                    songName = String.format("%s...", songName); // και πρόσθεσε στην θέση τους τρεις τελείες
                                }
                                jButton6.setText(songName); // ενημέρωσε το όνομα του κουμπιού
                                break;
                            case 1: // το jButton7 αντιπροσωπεύει το δεύτερο τραγούδι (το στοιχείο names[1] στον πίνακα names) και ούτω καθεξής
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton7.setText(songName);
                                break;
                            case 2:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton8.setText(songName);
                                break;
                            case 3:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton9.setText(songName);
                                break;
                            case 4:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton10.setText(songName);
                                break;
                            case 5:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton11.setText(songName);
                                break;
                            case 6:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton12.setText(songName);
                                break;
                            case 7:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton13.setText(songName);
                                break;
                            case 8:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton14.setText(songName);
                                break;
                            case 9:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton15.setText(songName);
                                break;
                            case 10:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton16.setText(songName);
                                break;
                            case 11:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton17.setText(songName);
                                break;
                            case 12:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton18.setText(songName);
                                break;
                            case 13:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton19.setText(songName);
                                break;
                            case 14:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton20.setText(songName);
                                break;
                            case 15:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton21.setText(songName);
                                break;
                            case 16:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton22.setText(songName);
                                break;
                            case 17:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton23.setText(songName);
                                break;
                            case 18:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton24.setText(songName);
                                break;
                            case 19:
                                file = new File(song.getPlayList().get(i));
                                songName = file.getName().substring(0, file.getName().lastIndexOf("."));
                                if (songName.length() > 15) {
                                    songName = songName.substring(0, 12);
                                    songName = String.format("%s...", songName);
                                }
                                jButton25.setText(songName);
                                break;

                        }
                    }
                }
                else if (song.getPlayList().size() >= 21) { // το GUI μπορεί και υποστηρίζει μόνο μέχρι 20 τραγούδια
                    jTextArea1.setText("Player Status : Playlist Has Too Many Songs");
                }
            }
            else if (Arg.checkFileType(path)) { // αν υπάρχει μόνο ένα τραγούδι
                names[0] = path; // αποθήκευσέ το στο πρώτο στοιχείο του πίνακα names
                songName = fileChooser.getSelectedFile().getName();
                songName = songName.substring(0, songName.lastIndexOf("."));
                if (songName.length() > 15) {
                    songName = songName.substring(0, 12);
                    songName = String.format("%s...", songName);
                }
                jButton6.setText(songName);
            }
        }
    }

    // το πρώτο κουμπί από την λίστα τραγουδιών έχει ιδιαίτερη σημασία γιατί μπορεί να είναι το μοναδικό
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton6.getText())) {       

            checkArguments Arg = new checkArguments();

            if (flag == SONG_IS_PAUSED) { // αν το τραγούδι βρίσκεται σε παύση
                song.stop(); // σταμάτησέ το
                
            }
            // αν παίζει άλλο τραγούδι από αυτό που αντιπροσωπεύει το συγκεκριμένο κουμπί
            else if ((flag != 0 && flag != NO_FILES_OPENED) || flag == NEW_FILE_OPENED) {
                song.stop(); // σταμάτησέ το
            }

            if (FirstTime == true) {  // αν είναι το πρώτο τραγούδι που θα αναπαραχθεί σε όλο το πρόγραμμα
                song.loggers();  // κάλεσε την loggers() για να εμφανιστούν στο τερματικό χρήσιμες πληροφορίες σχετικά με την κατάσταση της αναπαραγωγής
                FirstTime = false;  // από δω και στο εξής η μεταβλητή θα μείνει για πάντα false
            }

            // αν υπάρχει μόνο ένα τραγούδι (σημείωση: σε αυτή την περίπτωση το κουμπί random αγνοείται)
            if (Arg.checkFileType(path)) {
                if (flag == 0) { // αν επιλεχθεί το ίδιο τραγούδι επανειλημμένα σταμάτησέ το
                    song.stop();
                }
                if (jToggleButton1.isSelected()) { // αν έχει επιλεχθεί το repeat παίξε το τραγούδι κατ' επανάληψη
                    song.loopGUI();
                }
                else if (!jToggleButton1.isSelected()) { // αν δεν έχει επιλεχθεί το repeat απλά παίξε το τραγούδι μία φορά
                    song.playGUI(names[0]);
                }
                flag = 0; // ενημέρωσε την σημαία
            }
            else if ((Arg.checkListType(path) || Arg.checkDirectory(path))) { // αν βρισκόμαστε σε λίστα
                if (flag == 0) { // αν επιλεχθεί το ίδιο τραγούδι επανειλημμένα σταμάτησέ το
                    song.stop();
                }
                song.setCurrentSongIndex(0); // ενημέρωσε την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή
                flag = 0; // ενημέρωσε την σημαία
                // αν έχει επιλεχθεί μόνο το κουμπί repeat
                if (jToggleButton1.isSelected() && !jToggleButton2.isSelected()) {
                    song.loopGUI();
                }
                else if (jToggleButton2.isSelected() && !jToggleButton1.isSelected()) { // αν έχει επιλεχθεί μόνο το κουμπί random
                    song.randomGUI();
                }
                else { // αν έχουν επιλεχθεί και τα δύο ή κανένα τους, θέσε την στρατηγική σε order
                    song.orderGUI(); // αυτό σημαίνει πως η default στρατηγική που επιλέγεται όταν ο χρήστης διαλέξει ένα τραγούδι είναι order
                }
            }
            disable(jButton6, 500);  // κάντο unclickable για μικρό χρονικό διάστημα ώστε να μην είναι spammable
            disable(jButton7, 500);
            disable(jButton8, 500);
            disable(jButton9, 500);
            disable(jButton10, 500);
            disable(jButton11, 500);
            disable(jButton12, 500);
            disable(jButton13, 500);
            disable(jButton14, 500);
            disable(jButton15, 500);
            disable(jButton16, 500);
            disable(jButton17, 500);
            disable(jButton18, 500);
            disable(jButton19, 500);
            disable(jButton20, 500);
            disable(jButton21, 500);
            disable(jButton22, 500);
            disable(jButton23, 500);
            disable(jButton24, 500);
            disable(jButton25, 500);
            
        }
    }

    // αν επιλεχθεί το δεύτερο τραγούδι από την λίστα
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton7.getText())) { // αν στο κουμπί έχει αναθεθεί κάποιο τραγούδι
            addFunctionality(1); // πρόσθεσέ του την αντίστοιχη λειτουργικότητα
            
        }
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton8.getText())) {
            addFunctionality(2);
            
        }
    }

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton9.getText())) {
            addFunctionality(3);
            
        }
    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton10.getText())) {
            addFunctionality(4);
            
        }
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton11.getText())) {
            addFunctionality(5);
            
        }
    }

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton12.getText())) {
            addFunctionality(6);
            
        }
    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton13.getText())) {
            addFunctionality(7);
            
        }
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton14.getText())) {
            addFunctionality(8);
            
        }
    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton15.getText())) {
            addFunctionality(9);
            
        }
    }

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton16.getText())) {
            addFunctionality(10);
            
            
        }
    }

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton17.getText())) {
            addFunctionality(11);
            
        }
    }

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton18.getText())) {
            addFunctionality(12);
            
        }
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton19.getText())) {
            addFunctionality(13);
            
        }
    }

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton20.getText())) {
            addFunctionality(14);
            
        }
    }

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton21.getText())) {
            addFunctionality(15);
            
        }
    }

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton22.getText())) {
            addFunctionality(16);
            
        }
    }

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton23.getText())) {
            addFunctionality(17);
            
        }
    }

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton24.getText())) {
            addFunctionality(18);
            
        }
    }

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!" ".equals(jButton25.getText())) {
            addFunctionality(19);
            
        }
    }

    // προσθέτει την λειτουργικότητα κάθε κουμπιού
    private void addFunctionality(int i) {

        if (flag == SONG_IS_PAUSED) { // αν το τραγούδι βρίσκεται σε παύση
            song.stop(); // σταμάτησέ το
            
        }
        // αν παίζει άλλο τραγούδι από αυτό που αντιπροσωπεύει το συγκεκριμένο κουμπί
        else if ((flag != i && flag != NO_FILES_OPENED) || flag == NEW_FILE_OPENED) {
            song.stop(); // σταμάτησέ το
        }

        if (FirstTime == true) {  // αν είναι το πρώτο τραγούδι που θα αναπαραχθεί σε όλο το πρόγραμμα
            song.loggers();  // κάλεσε την loggers() για να εμφανιστούν στο τερματικό χρήσιμες πληροφορίες σχετικά με την κατάσταση της αναπαραγωγής
            FirstTime = false;  // από δω και στο εξής η μεταβλητή θα μείνει για πάντα false
        }

        if (flag == i) { // αν επιλεχθεί το ίδιο τραγούδι επανειλημμένα σταμάτησέ το
            song.stop();
        }

        song.setCurrentSongIndex(i); // ενημέρωσε την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή
        flag = i; // ενημέρωσε την σημαία

        // αν έχει επιλεχθεί μόνο το κουμπί repeat
        if (jToggleButton1.isSelected() && !jToggleButton2.isSelected()) {
            song.loopGUI();
        }
        else if (jToggleButton2.isSelected() && !jToggleButton1.isSelected()) { // αν έχει επιλεχθεί μόνο το κουμπί random
            song.randomGUI();
        }
        else { // αν έχουν επιλεχθεί και τα δύο ή κανένα τους, θέσε την στρατηγική σε order
            song.orderGUI(); // αυτό σημαίνει πως η default στρατηγική που επιλέγεται όταν ο χρήστης διαλέξει ένα τραγούδι είναι order
        }
        disable(jButton6, 500);  // κάντο unclickable για μικρό χρονικό διάστημα ώστε να μην είναι spammable
            disable(jButton7, 500);
            disable(jButton8, 500);
            disable(jButton9, 500);
            disable(jButton10, 500);
            disable(jButton11, 500);
            disable(jButton12, 500);
            disable(jButton13, 500);
            disable(jButton14, 500);
            disable(jButton15, 500);
            disable(jButton16, 500);
            disable(jButton17, 500);
            disable(jButton18, 500);
            disable(jButton19, 500);
            disable(jButton20, 500);
            disable(jButton21, 500);
            disable(jButton22, 500);
            disable(jButton23, 500);
            disable(jButton24, 500);
            disable(jButton25, 500);
    }

    // θέτει το όνομα κάθε κουμπιού σε κενό για να του στερήσει την λειτουργικότητά του
    private void clearButtons() {
        jButton6.setText(" ");
        jButton7.setText(" ");
        jButton8.setText(" ");
        jButton9.setText(" ");
        jButton10.setText(" ");
        jButton11.setText(" ");
        jButton12.setText(" ");
        jButton13.setText(" ");
        jButton14.setText(" ");
        jButton15.setText(" ");
        jButton16.setText(" ");
        jButton17.setText(" ");
        jButton18.setText(" ");
        jButton19.setText(" ");
        jButton20.setText(" ");
        jButton21.setText(" ");
        jButton22.setText(" ");
        jButton23.setText(" ");
        jButton24.setText(" ");
        jButton25.setText(" ");
    }

    // καθαρίζει τον πίνακα names
    private void clearNames() {
        for (int i = 0; i < names.length; i++) {
            names[i] = "";
        }
    }
    
    
    // απενεργοποιεί το κουμπί για μικρό χρονικό διάστημα ώστε να μην μπορεί να πατηθεί πολύ γρήγορα
    static void disable(final AbstractButton b, final long ms) {
    b.setEnabled(false);
    new SwingWorker() {
        @Override protected Object doInBackground() throws Exception {
            Thread.sleep(ms);
            return null;
        }
        @Override protected void done() {
            b.setEnabled(true);
        }
    }.execute();
}
}
