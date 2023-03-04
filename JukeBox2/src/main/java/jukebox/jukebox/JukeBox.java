package jukebox.jukebox;

public class JukeBox {

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) { // αν ο χρήστης δεν πέρασε ορίσματα απλά τρέξε το gui
            Window window = new Window(); // δημιουργεί το παράθυρο
            try { // κάνει τα κουμπιά πιο καλαίσθητα
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() { // τρέχει την εφαρμογή
                    window.setVisible(true); // κάνει το παράθυρο ορατό
                }
            });
        }
        else {
            checkArguments JukeBox = new checkArguments(2);  // Δημιουργεί νέo αντικείμενο JukeBox που είναι στιγμιότυπο της κλάσης checkArguments και θέτει NumbOfArg = 2 που αποτελεί το ἀνω ὀριο στην checkArgNumb)
            if (!JukeBox.checkArgNumb(args.length)) {  // Αν (είναι true ότι) ἐχει λιγότερο ἀπο 1 argument ή περισσότερα απο 2 (NumbOfArg) arguments
                System.out.println(">>> Argument requirement not met");  // Κάνει print στο τερματικό την φράση ">>> Argument requirement not met"
                System.exit(1);  // Κάνει exit με αποτυχία
            }

            Song song = new Song(args[0]);  // Δημιουργεί νέο αντικείμενο song που είναι στιγμιότυπο της κλάσης Song και παίρνει σαν όρισμα το 1ο argument που δίνει ο χρήστης (ανοίγει ο player)

            if (args.length == 1) {  // Αν το πλήθος των arguments ισούται με 1
                if (JukeBox.checkFileType(args[0])) {  // Αν (είναι true ότι) το 1ο argument που δίνει ο χρήστης πρόκειται για mp3 αρχείο
                    song.play(args[0]);  // Καλεί την μέθοδο play της κλάσης Song με παράμετρο το 1ο argument που δίνει ο χρήστης (παίζει το τραγούδι)
                }
                else if (JukeBox.checkListType(args[0])) {  // Αλλιώς αν (είναι true ότι) το 1ο argument που δίνει ο χρήστης πρόκειται για m3u λίστα
                    song.order();  // Καλεί την μέθοδο order της κλάσης Song χωρίς παραμέτρους (παίζει τα τραγούδια της λίστας με τη σειρά που γράφονται)
                }
                else {  // Αλλιώς (αν δεν είναι τίποτα απ' τα δύο)
                    System.out.println(">>> File not supported");  // Κάνει print στο τερματικό την φράση ">>> File not supported"
                }
            }
            else {  // Αλλιώς (πλήθος των arguments διάφορο του 1, δηλαδή έχουμε 2 arguments)
                if (JukeBox.checkFileType(args[0])) {  // Αν (είναι true ότι) το 1ο argument που δίνει ο χρήστης πρόκειται για mp3 αρχείο
                    if (JukeBox.checkStrat(args[1]).equals("loop")) {  // Αν (είναι true ότι) το 2ο argument που δίνει ο χρήστης ισούται με την στρατηγική "loop"
                        song.loop();  // Καλεί την μέθοδο loop της κλάσης Song χωρίς παραμέτρους (παίζει το τραγούδι κατ' επανάληψη)
                    }
                    else {  // Αλλιώς (για οποιαδήποτε άλλη στρατηγική)
                        JukeBox.other(args[1], args[0]);  // Καλεί την μέθοδο other της κλάσης checkArguments και δίνει ως παραμέτρους το 2ο argument του χρήστη ως StratInput και το 1ο argument ως Input
                    }
                }
                else if (JukeBox.checkListType(args[0])) {  // Αλλιώς αν (είναι true ότι) το 1ο argument που δίνει ο χρήστης πρόκειται για m3u λίστα
                    if (JukeBox.checkStrat(args[1]).equals("random")) {  // Αν (είναι true ότι) το 2ο argument που δίνει ο χρήστης ισούται με την στρατηγική "random"
                        song.random();  // Καλεί την μέθοδο random της κλάσης Song χωρίς παραμέτρους (παίζει τα τραγούδια της λίστας με τυχαία σειρά)
                    }
                    else if (JukeBox.checkStrat(args[1]).equals("order")) {  // Αλλιώς αν (είναι true ότι) το 2ο argument που δίνει ο χρήστης ισούται με την στρατηγική "order"
                        song.order();  // Καλεί την μέθοδο order της κλάσης Song χωρίς παραμέτρους (παίζει τα τραγούδια της λίστας με τη σειρά που γράφονται)
                    }
                    else if (JukeBox.checkStrat(args[1]).equals("loop")) {  // Αλλιώς αν (είναι true ότι) το 2ο argument που δίνει ο χρήστης ισούται με την στρατηγική "loop"
                        song.loop();  // Καλεί την μέθοδο loop της κλάσης Song χωρίς παραμέτρους (παίζει τη λίστα κατ' επανάληψη)
                    }
                    else {  // Αλλιώς (για οποιαδήποτε άλλη στρατηγική)
                        JukeBox.other(args[1], args[0]);  // Καλεί την μέθοδο other της κλάσης checkArguments και δίνει ως παραμέτρους το 2ο argument του χρήστη ως StratInput και το 1ο argument ως Input
                    }
                }
                else {  // Αλλιώς (1ο argument ούτε mp3 αρχείο ούτε m3u λίστα)
                    System.out.println(">>> File not supported");  // Κάνει print στο τερματικό την φράση ">>> File not supported"
                }
            }
            song.closePlayer();  // Καλεί την μέθοδο closePlayer της κλάσης Song χωρίς παραμέτρους (κλείνει ο player)
        }
    }
}
