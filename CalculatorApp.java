// Χρήση της βιβλιοθήκης swing
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


    // Η κλάση CalculatorApp επεκτείνει την κλάση JFrame και υλοποιεί τη διεπαφή ActionListener. 

    // Αυτό σημαίνει ότι η κλάση CalculatorApp μπορεί να λειτουργεί ως ένα παράθυρο της γραφικής 
    // διεπαφής χρήστη (GUI) και μπορεί να ακούει γεγονότα (events) και να αντιδράσει σε αυτά.

    // Η υλοποίηση της διεπαφής ActionListener απαιτεί την υλοποίηση της μεθόδου actionPerformed(ActionEvent e) 
    // που περιέχει τον κώδικα που θα εκτελείται κατά την εκτέλεση ενός συμβάντος.


public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public CalculatorApp() {
        // Ορισμός του τίτλου του παραθύρου ως "Αριθμομηχανή"
        setTitle("Αριθμομηχανή");
        // Απενεργοποίηση της δυνατότητας αλλαγής μεγέθους του παραθύρου
        setResizable(false);
        // Ορισμός της λειτουργίας κλεισίματος του παραθύρου όταν πατιέται το κουμπί "Κλείσιμο"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ορισμός του μεγέθους του παραθύρου ως 300 pixels πλάτος και 450 pixels ύψος
        setSize(300, 450);
        // Απενεργοποίηση του διαχειριστή διάταξης (layout manager) και χρήση απολύτως θέσεων (absolute positioning)
        setLayout(null);

        // Δημιουργία ενός νέου αντικειμένου JTextField για την εισαγωγή και εμφάνιση των αριθμών
        // Αρχική τιμή κειμένου είναι κενή
        textField = new JTextField();
        // Ορισμός της θέσης και του μεγέθους του πεδίου κειμένου στο παράθυρο
        // Οι αριθμοί που περνούν στην μέθοδο setBounds είναι οι συντεταγμένες (x, y) και το πλάτος και ύψος 
        textField.setBounds(30, 25, 240, 50);
        // Απενεργοποίηση της δυνατότητας επεξεργασίας του πεδίου κειμένου από τον χρήστη
        textField.setEditable(false);

        // Δημιουργία και αρχικοποίηση των κουμπιών με τα νούμερα της αριθμομηχανής
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
             // Το κουμπί έχει το κείμενο String.valueOf(i), δηλαδή τον αριθμό i σε μορφή κειμένου
            numberButtons[i] = new JButton(String.valueOf(i));
            // Προσθήκη ActionListener για αντίδραση στο κλικ από τον χρήστη
            numberButtons[i].addActionListener(this);
        }

        // Δημιουργία και αρχικοποίηση των κουμπιών λειτουργιών της αριθμομηχανής
        functionButtons = new JButton[8];
        // Το addButton δημιουργεί ένα κουμπί για την πρόσθεση
        addButton = new JButton("+");
        // Το subButton δημιουργεί ένα κουμπί για την αφαίρεση
        subButton = new JButton("-");
        // Το mulButton δημιουργεί ένα κουμπί για τον πολλαπλασιασμό
        mulButton = new JButton("*");
        // Το divButton δημιουργεί ένα κουμπί για τη διαίρεση
        divButton = new JButton("/");
        // Το decButton δημιουργεί ένα κουμπί για το δεκαδικό σημείο
        decButton = new JButton(".");
        // Το equButton δημιουργεί ένα κουμπί για τον υπολογισμό του αποτελέσματος
        equButton = new JButton("=");
        // Το delButton δημιουργεί ένα κουμπί για τη διαγραφή του τελευταίου ψηφίου
        delButton = new JButton("Delete");
        // Το clrButton δημιουργεί ένα κουμπί για την εκκαθάριση του πεδίου κειμένου
        clrButton = new JButton("Clear");


        // Αντιστοίχιση των κουμπιών λειτουργιών στον πίνακα functionButtons
        // Το addButton αντιστοιχεί στο functionButtons[0]
        functionButtons[0] = addButton;
        // Το subButton αντιστοιχεί στο functionButtons[1]
        functionButtons[1] = subButton;
        // Το mulButton αντιστοιχεί στο functionButtons[2]
        functionButtons[2] = mulButton;
        // Το divButton αντιστοιχεί στο functionButtons[3]
        functionButtons[3] = divButton;
        // Το decButton αντιστοιχεί στο functionButtons[4]
        functionButtons[4] = decButton;
        // Το equButton αντιστοιχεί στο functionButtons[5]
        functionButtons[5] = equButton;
        // Το delButton αντιστοιχεί στο functionButtons[6]
        functionButtons[6] = delButton;
        // Το clrButton αντιστοιχεί στο functionButtons[7]
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
        }

        // Δημιουργία του panel με GridLayout 4x4 και ανάμεσα στα στοιχεία απόσταση 10 pixels
        // Δημιουργία ενός νέου JPanel
        panel = new JPanel();
        // Ορισμός της τοποθεσίας και του μεγέθους του panel στο παράθυρο
        // Αυτη η γραμμή κώδικα ορίζει τις συντεταγμένες (x, y) και τις διαστάσεις (πλάτος, ύψος) του panel στο παράθυρο.
        panel.setBounds(30, 100, 240, 240);
        // Ορισμός της διάταξης του panel με χρήση του GridLayout (4 γραμμές, 4 στήλες, απόσταση 10 pixels)
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        
        // Προσθέστε τα κουμπιά με τα νούμερα και το κουμπί πρόσθεσης στο panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        
        // Προσθέστε τα κουμπιά με τα νούμερα και το κουμπί αφαίρεσης στο panel
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        
        // Προσθέστε τα κουμπιά με τα νούμερα και το κουμπί πολλαπλασιασμού στο panel
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        
        // Προσθέστε το κουμπί με το δεκαδικό σημείο, το κουμπί με το μηδέν και το κουμπί ισούς στο panel
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        // Ορισμός της θέσης και του μεγέθους του κουμπιού "Delete" (x=30, y=350, πλάτος=120, ύψος=30)
        delButton.setBounds(30, 350, 120, 30);
        // Ορισμός της θέσης και του μεγέθους του κουμπιού "Clear" (x=150, y=350, πλάτος=120, ύψος=30)
        clrButton.setBounds(150, 350, 120, 30);

        // Προσθήκη του panel στο παράθυρο της αριθμομηχανής
        add(panel);
        // Προσθήκη του textField (πεδίο κειμένου) στο παράθυρο της αριθμομηχανής
        add(textField);
        // Προσθήκη του κουμπιού "Delete" στο παράθυρο της αριθμομηχανής
        add(delButton);
        // Προσθήκη του κουμπιού "Clear" στο παράθυρο της αριθμομηχανής
        add(clrButton);
        // Ορισμός της ορατότητας του παραθύρου και των συστατικών του ως ορατά
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }

    // Η μέθοδος actionPerformed(ActionEvent e) είναι μια υλοποίηση της διεπαφής ActionListener 
    // και καλείται όταν συμβαίνει ένα συμβάν (event) που έχει ακροατή το αντικείμενο της κλάσης CalculatorApp.
    @Override
    public void actionPerformed(ActionEvent e) {
        // Η μέθοδος e.getSource() χρησιμοποιείται για να ανακτήσει το αντικείμενο που πυροδότησε ένα συμβάν (event) σε έναν ακροατή (listener) συμβάντος.
        // Η μέθοδος textField.getText() επιστρέφει το τρέχον περιεχόμενο του πεδίου κειμένου ως συμβολοσειρά.
        // H μέθοδος setText() θέτει το νέο περιεχόμενο του πεδίου κειμένου.

        // Ελέγχει αν πατήθηκε ένα από τα κουμπιά με τα νούμερα
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                // η μέθοδος concat() συνενώνει αυτήν τη συμβολοσειρά με την αναπαράσταση του αριθμού i σε συμβολοσειρά χρησιμοποιώντας τη μέθοδο String.valueOf(i)
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το decButton
        if (e.getSource() == decButton) {
            // όταν ο χρήστης πατάει το κουμπί της τελείας, θα προστίθεται μια τελεία στο τέλος του πεδίου κειμένου, επιτρέποντας την εισαγωγή δεκαδικών τιμών.
            textField.setText(textField.getText().concat("."));
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το decButton
        if (e.getSource() == addButton) {
            // Ανάθεση τιμής από το πεδίο κειμένου στη μεταβλητή num1
            // Μετατροπή του περιεχομένου του πεδίου κειμένου σε δεκαδικό αριθμό χρησιμοποιώντας την μέθοδο Double.parseDouble()
            num1 = Double.parseDouble(textField.getText());
            // Ανάθεση του τελεστή '+' στη μεταβλητή operator
            operator = '+';
            textField.setText("");
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το subButton
        if (e.getSource() == subButton) {
            // Ανάθεση τιμής από το πεδίο κειμένου στη μεταβλητή num1
            // Μετατροπή του περιεχομένου του πεδίου κειμένου σε δεκαδικό αριθμό χρησιμοποιώντας την μέθοδο Double.parseDouble()
            num1 = Double.parseDouble(textField.getText());
             // Ανάθεση του τελεστή '-' στη μεταβλητή operator
            operator = '-';
            textField.setText("");
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το mulButton
        if (e.getSource() == mulButton) {
            // Ανάθεση τιμής από το πεδίο κειμένου στη μεταβλητή num1
            // Μετατροπή του περιεχομένου του πεδίου κειμένου σε δεκαδικό αριθμό χρησιμοποιώντας την μέθοδο Double.parseDouble()
            num1 = Double.parseDouble(textField.getText());
             // Ανάθεση του τελεστή '*' στη μεταβλητή operator
            operator = '*';
            textField.setText("");
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το divButton
        if (e.getSource() == divButton) {
            // Ανάθεση τιμής από το πεδίο κειμένου στη μεταβλητή num1
            // Μετατροπή του περιεχομένου του πεδίου κειμένου σε δεκαδικό αριθμό χρησιμοποιώντας την μέθοδο Double.parseDouble()
            num1 = Double.parseDouble(textField.getText());
             // Ανάθεση του τελεστή '/' στη μεταβλητή operator
            operator = '/';
            textField.setText("");
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το equButton (κουμπί "=")
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            // Ορισμός της τιμής του αποτελέσματος στο πεδίο κειμένου
            // Η μέθοδος String.valueOf(result) μετατρέπει το αποτέλεσμα σε συμβολοσειρά

            textField.setText(String.valueOf(result));
            // Ανανέωση της μεταβλητής num1 με την τιμή του αποτελέσματος
            // Αυτό επιτρέπει τη χρήση του αποτελέσματος στις επόμενες πράξεις
            num1 = result;
        }

        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το clrButton (Clear)
        if (e.getSource() == clrButton) {
            // εκκαθαρίζει το πεδίο κειμένου (textField), δηλαδή θέτει το περιεχόμενό του σε κενό.
            textField.setText("");
        }
        // Ελέγχει εάν το αντικείμενο που πυροδότησε το συμβάν είναι το delButton (Delete)
        if (e.getSource() == delButton) {
            //Με αυτόν τον τρόπο, η γραμμή κώδικα αφαιρεί τον τελευταίο χαρακτήρα από το πεδίο κειμένου, 
            // επιτρέποντας στον χρήστη να διορθώσει μια εισαγωγή ή να διαγράψει τον τελευταίο αριθμό ή χαρακτήρα που πληκτρολόγησε.

            // Ανάθεση του τρέχοντος περιεχομένου του πεδίου κειμένου στη μεταβλητή currentText
            String currentText = textField.getText();
            // Ορισμός του πεδίου κειμένου με το υποπεδίο του currentText από τον χαρακτήρα 0 έως ένα χαρακτήρα πριν το τέλος
            textField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }
}
