package jukebox.jukebox;

// δηλώνει μεθόδους για τον έλεγχο των ορισμάτων
public interface Arguments {
    
    boolean checkArgNumb(int Numb);  // ελέγχει αν το πλήθος των arguments είναι έγκυρο
    
    boolean checkFileType(String FileInput);  // ελέγχει αν το αρχείο FileInput υποστηρίζεται απ' το πρόγραμμα
    
    String checkStrat(String StratInput);  // ελέγχει αν η στραγητική που έδωσε ο χρήστης είναι έγκυρη
    
    boolean checkListType(String ListInput);  // ελέγχει αν η λίστα ListInput υποστηρίζεται απ' το πρόγραμμα
    
    void other(String StratInput,String FileInput);  // εμφανίζει μήνυμα για κάθε ειδική περίπτωση
    
}
