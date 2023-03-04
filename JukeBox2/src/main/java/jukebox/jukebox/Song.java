package jukebox.jukebox;

import java.io.*;
import java.util.*;
import gr.hua.dit.oop2.musicplayer.*;
import java.security.SecureRandom;
import java.util.logging.*;

public class Song implements Strategies {  // Η public κλάση Song που αναπαριστά ένα τραγούδι ή μια λίστα τραγουδιών αναγνωρίζει και υλοποιεί τις μεθόδους του interface Strategies

    private static final Logger logger = Logger.getLogger(Song.class.getName());

    public static int Threadcount = 0;
    private int currentSongIndex;
    private final checkArguments Arg;  // Η private final "μεταβλητή" τύπου checkArguments με όνομα Arg δηλώνεται μέσα στην κλάση ώστε να είναι ορατή σε όλες τις μεθόδους της κλάσης
    private final Parser parser;  //  Ομοίως για την private final "μεταβλητή" τύπου Parser με όνομα parser
    private final Player player;  //  Ομοίως για την private final "μεταβλητή" τύπου Player με όνομα player
    private final String name;  //  Ομοίως για την private final "μεταβλητή" τύπου String με όνομα name
    private final String absolutePath;  // Ομοίως για την private final "μεταβλητή" τύπου String με όνομα absolutePath
    private final String path;  // Ομοίως για την private final "μεταβλητή" τύπου String με όνομα path
    private final String parentPath;  // Ομοίως για την private final "μεταβλητή" τύπου String με όνομα parentPath
    private final String fileInput;  // Ομοίως για την private final "μεταβλητή" τύπου String με όνομα fileInput
    private ArrayList<String> playList = null;  // Η private Arraylist με όνομα playList που περιέχει μεταβλητές τύπου String αρχικοποιείται με null
    private InputStream stream;

    public int getCurrentThreadCount() {
        return Threadcount;  // Ο getter public int getCurrentThreadCount() επιστρέφει την μεταβλητή Threadcount (τον αριθμό των threads που τρέχουν μια διεργασία)
    }

    public void setCurrentSongIndex(int value) {
        currentSongIndex = value;  // Ο setter public void setCurrentSongIndex θέτει την μεταβλητή currentSongIndex ίση με το int value 
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;  // Ο getter public int getCurrentSongIndex επιστρέφει την μεταβλητή την μεταβλητή currentSongIndex (την θέση του τραγουδιού στην playlist)
    }

    public String getName() {
        return name;  // Ο getter public String getName() επιστρέφει την μεταβλητή name (το όνομα του αρχείου)
    }

    public String getAbsolutePath() {
        return absolutePath;  // Ο getter public String getAbsolutePath() επιστρέφει την μεταβλητή absolutePath (το απόλυτο μονοπάτι του αρχείου)
    }

    public String getPath() {
        return path;  // Ο getter public String getPath() επιστρέφει την μεταβλητή path (το μονοπάτι του αρχείου)
    }

    public String getParentPath() {
        return parentPath;   // Ο getter public String getParentPath() επιστρέφει την μεταβλητή parentPath (το μονοπάτι του καταλόγου του πατέρα του αρχείου)
    }

    public ArrayList<String> getPlayList() {
        return playList;  // Ο getter public ArrayList<String> getPlayList() επιστρέφει την λίστα με τα τραγούδια
    }

    public Song(String fileInput) {

        currentSongIndex = 0;
        this.fileInput = fileInput;  // αντιγράφει την τιμή της παραμέτρου fileInput στο πεδίο (this)fileinput (στην περίπτωσή μας περνάει το 1ο argument που του δίνει ο χρήστης στην μεταβλητή fileinput)
        Arg = new checkArguments();  // Δημιουργεί νέo αντικείμενο Arg που είναι στιγμιότυπο της κλάσης checkArguments (επειδή δεν θα χρησιμοποιήσουμε τη μέθοδο checkArgNumb δεν έχει σημασία τι όρισμα θα περάσουμε στη συνάρτηση δημιουργίας)
        parser = new Parser(fileInput, Arg);  // Δημιουργεί νέo αντικείμενο parser που είναι στιγμιότυπο της κλάσης Parser και θέτει filename = fileInput
        player = PlayerFactory.getPlayer();  // Παίρνει από το PlayerFactory έναν player
        File f = new File(fileInput);  // Δημιουργεί νέo αντικείμενο f που είναι στιγμιότυπο της κλάσης File με παράμετρο fileInput
        name = f.getName();  // Η μεταβλητή name παίρνει ως τιμή το όνομα του αρχείου που δημιουργήθηκε
        absolutePath = f.getAbsolutePath();  // Η μεταβλητή absolutePath παίρνει ως τιμή το απόλυτο μονοπάτι του αρχείου που δημιουργήθηκε
        path = f.getPath();  // Η μεταβλητή path παίρνει ως τιμή το μονοπάτι του αρχείου που δημιουργήθηκε
        parentPath = f.getParent();  // Η μεταβλητή parentPath παίρνει ως τιμή το μονοπάτι του τελευταίου καταλόγου που βρίσκεται το αρχείο που δημιουργήθηκε
        if (Arg.checkListType(fileInput) || Arg.checkDirectory(fileInput)) {  // Αν (είναι true ότι) το fileInput πρόκειται για m3u λίστα
            playList = parser.getPlayList();  // Η λίστα playlist παίρνει ως τιμή την τιμή που επιστρέφεται από την μέθοδο getPlaylist() της κλάσης Parser (δηλαδή κάνει την parse στο playList)
        }
    }

    @Override
    public void play(String Name) {  // παίζει το τραγούδι
        try {  // Με την try μπορούμε να ορίσουμε ένα κομμάτι κώδικα που θέλουμε να ελεγχθεί για τυχόν σφάλματα κατά την εκτέλεσή του
            File file = new File(Name);  // Δημιουργεί νέo αντικείμενο file που είναι στιγμιότυπο της κλάσης File με παράμετρο Name
            if (file.exists()) { // Αν υπάρχει το αρχείο
                System.out.println(">>> PLAYING: " + file.getName());  // Κάνει print στο τερματικό την φράση ">>> PLAYING: " και την τιμή που επιστρέφει η μέθοδος getName() της κλάσης File (το όνομα του τραγουδιού που παίζει)
            }
            player.play((InputStream) new FileInputStream(file.getAbsoluteFile()));  // Δημιουργεί νέο stream και το τρέχει με την μέθοδο play (ανοίγει ο player)
        }
        catch (FileNotFoundException e) {  // Με την catch ορίζουμε ένα κομμάτι κώδικα που εκτελείται αν εντοπιστεί σφάλμα FileNotFoundException κατά την εκτέλεση του κώδικα που βρίσκεται στο try
            System.err.println(">>> Something's wrong with the file: " + e.getMessage());  // Κάνει print στο τερματικό με ένδειξη σφάλματος την φράση ">>> Something's wrong with the file: " και την τιμή που επιστρέφεται από τη μέθοδο getMessage() δηλαδή το όνομα του exception που στην περίπτωσή μας είναι FileNotFoundException
        }
        catch (PlayerException e) {  // Με την catch ορίζουμε ένα κομμάτι κώδικα που εκτελείται αν εντοπιστεί σφάλμα PlayerException κατά την εκτέλεση του κώδικα που βρίσκεται στο try
            System.err.println(">>> Something's wrong with the player: " + e.getMessage());  // Κάνει print στο τερματικό με ένδειξη σφάλματος την φράση ">>> Something's wrong with the player: " και την τιμή που επιστρέφεται από τη μέθοδο getMessage() δηλαδή το όνομα του exception που στην περίπτωσή μας είναι PlayerException
        }
    }

   

    @Override
    public void loop() {  // παίζει τη λίστα ή το τραγούδι κατ' επανάληψη
        if (Arg.checkFileType(fileInput)) {  // Αν (είναι true ότι) το fileinput πρόκειται για mp3 αρχείο
            while (true) {  // Όσο η συνθήκη είναι αληθές (στην περίπτωσή μας είναι πάντα αληθές και πρόκειται για ατέρμων βρόχο)
                play(absolutePath);  // Καλεί την μέθοδο play με παράμετρο absolutePath (παίζει το τραγούδι)
            }
        }
        else if (Arg.checkListType(fileInput) || Arg.checkDirectory(fileInput)) {  // Αλλιώς αν (είναι true ότι) το fileInput πρόκειται για m3u λίστα ή για φάκελο
            while (true) {  // Όσο η συνθήκη είναι αληθές (στην περίπτωσή μας είναι πάντα αληθές και πρόκειται για ατέρμων βρόχο)
                order();  // Καλεί την μέθοδο order (παίζει τη λίστα με σειρά)
            }
        }
    }

    @Override
    public void order() {  // παίζει όλα τα τραγούδια με τη σειρά που γράφονται στη λίστα
        for (String song : playList) {  // Διατρέχει το playlist χωρίς counter για κάθε τραγούδι song στη λίστα playList
            play(song);  // Καλεί την μέθοδο play με παράμετρο song (παίζει το τραγούδι)
        }
    }

    // παίζει όλα τα τραγούδια της λίστας με τυχαία σειρά
    @Override
    public void random() {
        SecureRandom rand = new SecureRandom();
        ArrayList<String> newPlayList = new ArrayList<>();  // νέα λίστα τραγουδίων
        int max = playList.size();

        // όσο η λίστα playList δεν είναι άδεια
        for (int i = 0; i < max; i++) {
            // πάρε ένα τυχαίο τραγούδι
            String song = playList.get(rand.nextInt(playList.size()));
            playList.remove(song);  // διέγραψε το από την playList
            newPlayList.add(song);  // και πρόσθεσέ το στην νέα λίστα
        }

        // για κάθε τραγούδι song στην νέα λίστα newPlayList
        for (String song : newPlayList) {
            play(song);  // παίξε το τραγούδι
        }

        // αποθήκευσε στην playList την αρχική λίστα
        playList = parser.getPlayList();
    }

    @Override
    public void closePlayer() {
        player.close();  // Κλείνει ο player
    }

    
// Δεύτερο Μέρος Εργασίας   
    
    
    public String getStatus() {
        return player.getStatus().name();  // Επιστρέφει την κατάσταση που βρίσκεται ο player
    }

    public void stop() {
        player.stop();  // Σταματάει ο player
    }

    public void pause() {
        player.pause();  // Γίνεται παύση ο player
    }

    public void resume() {
        player.resume();  // Επαναφέρεται ο player
    }

    
    
    
    
     public void startPlaying(String Name) {  // αρχίζει να παίζει το τραγούδι

        try {  // Με την try μπορούμε να ορίσουμε ένα κομμάτι κώδικα που θέλουμε να ελεγχθεί για τυχόν σφάλματα κατά την εκτέλεσή του
            File file = new File(Name);  // Δημιουργεί νέo αντικείμενο file που είναι στιγμιότυπο της κλάσης File με παράμετρο Name
            if (file.exists()) {  // Αν υπάρχει το αρχείο
                System.out.println(">>> CURRENT SONG: " + file.getName());  // Κάνει print στο τερματικό την φράση ">>> CURRENT SONG: " και την τιμή που επιστρέφει η μέθοδος getName() της κλάσης File (το όνομα του τραγουδιού που παίζει)
                String text = file.getName();  // θέτει το string text ίση με το όνομα του αρχείου
                if (text.length() > 15) {  // αν το string text έχει μήκος μεγαλύτερο του 15
                    text = text.substring(0, 12);  // παίρνει τους πρώτους 12 χαρακτήρες από το string text
                    text = String.format("%s...", text);  // προσθέτει ... στο τέλος του νέου string text
                }
                jukebox.jukebox.Window.jLabel4.setText(text);  

            }
            stream = (InputStream) new FileInputStream(file.getAbsoluteFile());  // Δημιουργεί νέο stream
            player.startPlaying(stream);  // Και το τρέχει με την μέθοδο startPlaying (ανοίγει ο player)
        }
        catch (PlayerException | FileNotFoundException e) {  // Με την catch ορίζουμε ένα κομμάτι κώδικα που εκτελείται αν εντοπιστεί σφάλμα PlayerException ή FileNotFoundException κατά την εκτέλεση του κώδικα που βρίσκεται στο try
            logger.severe(e.getMessage());
        }
    }


    
    
    
    
    public void playGUI(String Name) {  // η αντίστοιχη μέθοδος play για το γραφικό περιβάλλον χρήστη
        new Thread(new Runnable() {  // δημιουργία νέου thread και νέας διεργασίας
            @Override
            public void run() {
                Threadcount++;  // Αυξάνει το πλήθος των threads κατά ένα
                startPlaying(Name);  // Καλεί την μέθοδο startPlaying με παράμετρο Name (παίζει το τραγούδι)
                jukebox.jukebox.Window.jTextArea1.setText("Player Status : Playing"); // Ενημέρωσε την ετικέτα
                while (!"IDLE".equals(player.getStatus().name())) {  // Όσο ο player δεν είναι idle
                    try {
                        Thread.sleep(1);  // βάζει μια μικρή καθυστέρηση 
                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (Threadcount > 1) {  // Άν το πλήθος των threads είναι μεγαλύτερος από ένα, δηλαδή αν πατήσουμε σε κάποιο άλλο τραγούδι της λίστας καθώς παίζει ήδη ένα τραγούδι 
                        //Για debugging, System.out.println(Threadcount);    Πάντα 2, υπάρχουν δυο threads οπότε σταματάει ο προηγούμενος
                        Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                        return;  // Σταματάει τη διεργασία
                    }
                }
                Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                //Για debugging, System.out.println(Threadcount);    Πάντα 0, δεν υπάρχουν threads
                currentSongIndex = 0;  // θέτει την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή ίση με 0
                jukebox.jukebox.Window.jTextArea1.setText("Player Status : Stream Ended");
            }
        }).start();
    }

    
    
    public void orderGUI() {  // παίζει όλα τα τραγούδια με τη σειρά που γράφονται στη λίστα
        new Thread(new Runnable() {  // δημιουργία νέου thread και νέας διεργασίας
            @Override
            public void run() {
                Threadcount++;  // Αυξάνει το πλήθος των threads κατά ένα
                for (; currentSongIndex < playList.size(); currentSongIndex++) {  // Διατρέχει το playlist χωρίς αρχικοποίηση για κάθε ακέραιο currentSongIndex μικρότερου της λίστας playList
                    startPlaying(playList.get(currentSongIndex));  // Καλεί την μέθοδο startPlaying με παράμετρο playList.get(currentSongIndex) (παίζει το τραγούδι με βάση την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή)
                    jukebox.jukebox.Window.jTextArea1.setText("Player Status : Playing");
                    while (!"IDLE".equals(player.getStatus().name())) {  // Όσο ο player δεν είναι idle
                        try {
                            Thread.sleep(1);  // βάζει μια μικρή καθυστέρηση 
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (Threadcount > 1) {  // Άν το πλήθος των threads είναι μεγαλύτερος από ένα, δηλαδή αν πατήσουμε σε κάποιο άλλο τραγούδι της λίστας καθώς παίζει ήδη ένα τραγούδι 
                            Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                            return;  // Σταματάει τη διεργασία
                        }
                    }
                }
                Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                jukebox.jukebox.Window.jTextArea1.setText("Player Status : Stream Ended");
                currentSongIndex = 0;  // θέτει την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή ίση με 0
            }
        }).start();
    }

    
    
    public void loopGUI() {  // παίζει τη λίστα ή το τραγούδι κατ' επανάληψη
        new Thread(new Runnable() {  // δημιουργία νέου thread και νέας διεργασίας
            @Override
            public void run() {
                while (true) {  // Ατέρμων βρόχος
                    Threadcount++;  // Αυξάνει το πλήθος των threads κατά ένα
                    checkArguments Arg = new checkArguments();  // Δημιουργεί νέo αντικείμενο Arg που είναι στιγμιότυπο της κλάσης checkArguments  
                    if (Arg.checkFileType(path)) {  // Αν το path πρόκειται για mp3 αρχείο 
                        startPlaying(absolutePath);  // Καλεί την μέθοδο startPlaying με παράμετρο absolutePath (παίζει το τραγούδι)
                        jukebox.jukebox.Window.jTextArea1.setText("Player Status : Playing");
                        while (!"IDLE".equals(player.getStatus().name())) {  // Όσο ο player δεν είναι idle
                            try {
                                Thread.sleep(1);  // βάζει μια μικρή καθυστέρηση
                            }
                            catch (InterruptedException ex) {
                                Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (Threadcount > 1) {  // Άν το πλήθος των threads είναι μεγαλύτερος από ένα, δηλαδή αν πατήσουμε σε κάποιο άλλο τραγούδι της λίστας καθώς παίζει ήδη ένα τραγούδι 
                                Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                                return;  // Σταματάει τη διεργασία
                            }
                        }
                    }
                    else if (Arg.checkListType(path) || Arg.checkDirectory(path)) {  // Αλλιώς αν το path πρόκειται για m3u λίστα ή φάκελο
                        for (; currentSongIndex < playList.size(); currentSongIndex++) {  // Διατρέχει το playlist χωρίς αρχικοποίηση για κάθε ακέραιο currentSongIndex μικρότερου της λίστας playList
                            startPlaying(playList.get(currentSongIndex));  // Καλεί την μέθοδο startPlaying με παράμετρο playList.get(currentSongIndex) (παίζει το τραγούδι με βάση την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή)
                            jukebox.jukebox.Window.jTextArea1.setText("Player Status : Playing");
                            while (!"IDLE".equals(player.getStatus().name())) {  // Όσο ο player δεν είναι idle
                                try {
                                    Thread.sleep(1);  // βάζει μια μικρή καθυστέρηση
                                }
                                catch (InterruptedException ex) {
                                    Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if (Threadcount > 1) {  // Άν το πλήθος των threads είναι μεγαλύτερος από ένα, δηλαδή αν πατήσουμε σε κάποιο άλλο τραγούδι της λίστας καθώς παίζει ήδη ένα τραγούδι 
                                    Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                                    return;  // Σταματάει τη διεργασία
                                }
                            }
                        }
                    }
                    Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                    jukebox.jukebox.Window.jTextArea1.setText("Player Status : Stream Ended");
                    currentSongIndex = 0;  // θέτει την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή ίση με 0
                }
            }
        }).start();
    }

    
    
    public void randomGUI() {  // παίζει όλα τα τραγούδια της λίστας με τυχαία σειρά
        new Thread(new Runnable() {  // δημιουργία νέου thread και νέας διεργασίας
            @Override
            public void run() {
                Threadcount++;  // Αυξάνει το πλήθος των threads κατά ένα
                SecureRandom rand = new SecureRandom();
                ArrayList<String> newPlayList = new ArrayList<>();  // νέα λίστα τραγουδίων newPlaylist
                ArrayList<String> testPlayList = new ArrayList<>();  // νέα λίστα τραγουδίων testPlaylist

                // όσο η μεταβλητή currentSongIndex δεν έχει φτάσει στο τέλος της playlist
                for (currentSongIndex = 0; currentSongIndex < playList.size(); currentSongIndex++) {
                    // πάρε το τρέχων τραγούδι
                    String song = playList.get(currentSongIndex);
                    testPlayList.add(song);  // και πρόσθεσέ το στην test λίστα
                }

                // όσο η μεταβλητή currentSongIndex δεν έχει φτάσει στο τέλος της playlist
                for (currentSongIndex = 0; currentSongIndex < playList.size(); currentSongIndex++) {
                    // πάρε ένα τυχαίο τραγούδι
                    String song = testPlayList.get(rand.nextInt(testPlayList.size()));
                    testPlayList.remove(song);  // διέγραψε το από την testPlayList
                    newPlayList.add(song);  // και πρόσθεσέ το στην νέα λίστα newPlaylist
                }

                // για κάθε τραγούδι song στην νέα λίστα newPlayList
                for (String song : newPlayList) {
                    startPlaying(song);  // παίξε το τραγούδι
                    jukebox.jukebox.Window.jTextArea1.setText("Player Status : Playing");
                    while (!"IDLE".equals(player.getStatus().name())) {  // Όσο ο player δεν είναι idle
                        try {
                            Thread.sleep(1);  // βάζει μια μικρή καθυστέρηση
                        }
                        catch (InterruptedException ex) {
                            Logger.getLogger(Song.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (Threadcount > 1) {  // Άν το πλήθος των threads είναι μεγαλύτερος από ένα, δηλαδή αν πατήσουμε σε κάποιο άλλο τραγούδι της λίστας καθώς παίζει ήδη ένα τραγούδι 
                            Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                            return;  // Σταματάει τη διεργασία
                        }
                    }
                }
                Threadcount--;  // Μειώνεται κατά ένα το πλήθος των threads
                jukebox.jukebox.Window.jTextArea1.setText("Player Status : Stream Ended");
                currentSongIndex = 0;  // θέτει την μεταβλητή που κρατάει το τρέχων τραγούδι προς αναπαραγωγή ίση με 0
            }
        }).start();
    }

    
    
    public void loggers() {  // μέθοδος για να δημιουργηθούν οι loggers
        Handler handlerObj = new ConsoleHandler();  // δημιουργία χειριστή καταγραφέων για καταγραφή των γεγονότων στην κονσόλα
        handlerObj.setLevel(Level.INFO);  // FINE για να φανει και το progress (δευτερόλεπτα), αλλιως INFO
        logger.addHandler(handlerObj);
        logger.setLevel(Level.INFO);  // FINE για να φανει και το progress (δευτερόλεπτα), αλλιως INFO
        logger.setUseParentHandlers(false);

        player.addPlayerListener(new PlayerListener() {  // δημιουργία νέου PlayerListener
            @Override
            public void statusUpdated(PlayerEvent e) {
                logger.info("Status changed to " + e.getStatus());  // κάνει προώθηση στον χειριστή καταγραφέων το string "Status changed to " ακολουθόμενο από την κατάσταση του player

            }
        });
        player.addProgressListener(new ProgressListener() {  // δημιουργία νέου ProgressListener
            @Override
            public void progress(ProgressEvent e) {
                float seconds = e.getMicroseconds() * 1.0f / 1000000;
                logger.fine("Seconds: " + seconds);  // κάνει προώθηση στον χειριστή καταγραφέων το string "Seconds: " ακολουθόμενο από τα δευτερόλεπτα

            }
        });
    }
}
