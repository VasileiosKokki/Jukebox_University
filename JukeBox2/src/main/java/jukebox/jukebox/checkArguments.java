package jukebox.jukebox;

// Η κλάση checkArguments υλοποιεί τις μεθόδους του interface Arguments
public class checkArguments implements Arguments {

//  Οι τρείς πίνακες από String περιέχουν τα file extentions, τα list extentions και τα διάφορα strategies
//  Το int NumbOfArg περιέχει τον μέγιστο αριθμό των arguments που θέλουμε να έχει το πρόγραμμα
    private int NumbOfArg;
    private final String[] files = {"mp3"};
    private final String[] lists = {"m3u"};
    private final String[] strats = {"loop", "random", "order"};

//  Ο ένας constructor είναι υπεύθυνος για την αρχικοποίηση του NumbOfArg
//  Ο άλλος δεν κάνει τίποτα απλά υπάρχει για να καλείται η κλάση σε άλλες κλάσεις χωρίς να πρέπει να του δώσουμε αριθμό arguments
    public checkArguments(int NumbOfArg) {
        this.NumbOfArg = NumbOfArg;
    }

    public checkArguments() {

    }

//  Η checkArgNumb τσεκάρει αν ο αριθμός των arguments που έδωσε ο χρήστης είναι από 1 μέχρι και NumbOfArg,
//  όπου Numb ο αριθμός των arguments που έδωσε ο χρήστης, και επιστρέφει λογικό true or false
    public boolean checkArgNumb(int Numb) {
        return Numb >= 1 && Numb <= NumbOfArg;
    }

//  Η checkStrat τσεκάρει αν η στρατηγική που έδωσε ο χρήστης υπάρχει σαν στρατηγική,
//  όπου StratInput το δεύτερο argument που έδωσε ο χρήστης και strats ο πίνακας με τις διάφορες στρατηγικές,
//  και επιστρέφει σε String με lowercase την στρατηγική αν υπάρχει αλλιώς επιστρέφει ""
//  Η τοπική μεταβλητή Strat είναι για το πέρασμα όλου του πίνακα
    public String checkStrat(String StratInput) {
        for (String Strat : strats) {
            if (StratInput.toLowerCase().equals(Strat)) {
                return StratInput.toLowerCase();
            }
        }
        return "";
    }

//  Η checkFileType τσεκάρει αν αυτό που έδωσε ο χρήστης είναι στα υποστηριζόμενα αρχεία του προγράμματος,
//  όπου FileInput το πρώτο argument που έδωσε ο χρήστης και files ο πίνακας με τα υποστηριζόμενα αρχεία,
//  και επιστρέφει λογικό true or false
//  H τοπική μεταβλητή File είναι για το πέρασμα όλου του πίνακα
    public boolean checkFileType(String FileInput) {
        for (String File : files) {
            if (FileInput.lastIndexOf(".") != -1) {          // Βλέπει αν υπάρχει "." στο FileInput
                if (FileInput.substring(FileInput.lastIndexOf(".") + 1).contains(File)) {       // Βλέπει αν μετά την τελευταία "." που υπάρχει
                    return true;                                                                      // περιέχει κάποιο από τα υποστηριζόμενα αρχεία που
                }                                                                                     // βρίσκονται στον πίνακα
            }
        }
        return false;
    }

//  Η checkListType τσεκάρει αν αυτό που έδωσε είναι στις υποστηριζόμενες λίστες του προγράμματος,
//  όπου ListInput το πρώτο argument που έδωσε ο χρήστης και lists ο πίνακας με τις υποστηριζόμενες λίστες,
//  και επιστρέφει λογικό true or false
//  Η τοπική μεταβλητή List είναι για το πέρασμα όλου του πίνακα
    public boolean checkListType(String ListInput) {
        for (String List : lists) {
            if (ListInput.lastIndexOf(".") != -1) {          // Βλέπει αν υπάρχει "." στο ListInput
                if (ListInput.substring(ListInput.lastIndexOf(".") + 1).contains(List)) {       // Βλέπει αν μετά την τελευταία "." που υπάρχει
                    return true;                                                                      // περιέχει κάποιο από τις υποστηριζόμενες λίστες που
                }                                                                                     // βρίσκονται στον πίνακα
            }
        }
        return false;
    }

    public boolean checkDirectory(String Input) {
        return !Input.substring(Input.lastIndexOf("\\") + 1).contains(".");
    }

//  Η other εκτελεί ένα print αναλόγως την περίπτωση
//  Όπου Input και StratInput το πρώτο και δεύτερο argument που έδωσε ο χρήστης
    public void other(String StratInput, String Input) {
        if (checkStrat(StratInput).equals("")) {                              // Αν αυτό που επιστρέψει η checkStrat είναι ίσο με "", δηλαδή
            System.out.println(">>> Strategy not found");                        // αυτό που έδωσε ο χρήστης δεν υπάρχει σαν στρατηγική τότε
        }                                                                          // κάνει print ότι δεν υπάρχει η στρατηγική που έδωσε
        else if (checkFileType(Input)) {
            System.out.println(">>> Cannot use " + StratInput + " with file");     // Αλλιώς αν το πρώτο argument που έδωσε ο χρήστης έιναι αρχείο
        }                                                                          // κάνει print ότι δεν μπορεί να χρησιμοποιήσει την συγκεκριμένη
        else if (checkListType(Input)) {                                    // στρατηγική με file και αντίστοιχα για list
            System.out.println(">>> Cannot use " + StratInput + " with List");
        }
    }
}
