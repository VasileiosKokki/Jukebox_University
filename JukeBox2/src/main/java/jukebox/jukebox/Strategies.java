package jukebox.jukebox;

// δηλώνει μεθόδους που αντιπροσωπεύουν βασικές λειτουργιές και στρατηγικές επιλογής
public interface Strategies {
    
    void play(String name);  // παίζει το τραγούδι

    void loop();  // παίζει τη λίστα ή το τραγούδι κατ' επανάληψη
    
    void order();  // παίζει όλα τα τραγούδια με τη σειρά που γράφονται στη λίστα
    
    void random();  // παίζει όλα τα τραγούδια της λίστας με τυχαία σειρά
    
    void closePlayer();  // κλείνει τον player
    
}
