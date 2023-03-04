package jukebox.jukebox;

import java.io.*;
import java.util.*;
import java.util.regex.*;

// επεξεργάζεται λίστες m3u
public class Parser {

    // πίνακας με τα absolute paths των τραγουδίων της λίστας m3u
    private final ArrayList<String> playList;
    // μεταβλητή που αντιπροσωπεύει το path της λίστας m3u
    private final String filename;
    private final checkArguments Arg;

    public Parser(String filename, checkArguments Arg) {
        // αρχικοποίησε την playList με άδειο πίνακα
        playList = new ArrayList<>();
        // αρχικοποίησε το filename με το path του m3u
        this.filename = filename;
        this.Arg = Arg;
    }

    // επιστρέφει τα absolute paths των τραγουδιών που περιέχονται στον φάκελο ή στην λίστα m3u
    public ArrayList<String> getPlayList() {
        try {
            // δήλωση τοπικών μεταβλητών
            File f = new File(filename);
            File[] contentsOfDirectory = f.listFiles();
            String directoryFilePath;
            if (Arg.checkDirectory(filename)) { // αν το αρχείο είναι φάκελος
                for (File temp : contentsOfDirectory) { // για κάθε αρχείο στον φάκελο
                    directoryFilePath = temp.getPath(); // πάρε το path του αρχείου
                    if (Arg.checkFileType(directoryFilePath)) { // αν το αρχείο είναι τύπου mp3
                        playList.add(directoryFilePath); // πρόσθεσέ το στην λίστα
                    }
                }
            }
            else if (Arg.checkListType(filename)) { // αν το αρχείο είναι λίστα m3u
                String fParent = f.getParent(), line;
                BufferedReader br = new BufferedReader(new FileReader(f));
                // όσο το stream είναι ανοιχτό
                while ((line = br.readLine()) != null) {
                    // πρόσθεσε το επόμενο mp3 της λίστας στον πίνακα playList
                    addMP3(line, fParent);
                }
                br.close(); // κλείσε το stream
            }
        }
        catch (IOException e) {
            System.err.println(">>> Something's wrong with the file: "
                    + e.getMessage());
        }
        return playList; // επέστρεψε το playList
    }

    // προσθέτει το absolute path του mp3 στο playList
    private void addMP3(String line, String parent) {
        File path = new File(line); // αντιπροσωπεύει μια γραμμή του m3u
        String filePath = path.getPath(); // περιέχει το path της γραμμής
        // αν η γραμμή δεν είναι κενή
        if (!line.isEmpty() && !line.startsWith("#")) {
            // και περιέχει μόνο το όνομα του mp3
            if (!line.contains("/") && !line.contains("\\")) {
                // συνένωσε το όνομα με τον parent
                playList.add(parent + "/" + line);
            }
            // αν περιέχει το relative path του mp3
            else if (!Pattern.compile("[:]|^[/|\\\\]").matcher(line).find()) {
                // συνένωσε το relative path με τον parent
                playList.add(parent + "/" + filePath);
            }
            else { // αν περιέχει το absolute path
                playList.add(line); // απλά πρόσθεσέ το στο playList
            }
        }
    }
}
