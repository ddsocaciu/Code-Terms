package FileSource;
import java.io.*;

/**
 * Klasse FileLoader ladet, speichert und fügt Fragen hinzu.
 * WICHTIG:
 * - Für die Fragen u. Antworten werden mehrdimensionale Arrays verwendet wobei:
 * -[0][i]: in dieser Reihe werden die Fragen gespeichert
 * -[1][i]: in dieser Reihe werden die Antworten gespeichert
 * @author David Socaciu
 * @version 2024-12-26
 */

public class FileLoader {
    /**
     * Methode saveFragen übernimmt einen Fragenpool sowie einen Dateinamen und speichert die Fragen u. Antworten in einer Datei
     * @param fileName Der Name der Datei
     * @param fragenAntworten Der Fragenpool mit Fragen u. Antworten
     */
    public void saveFragen(String fileName, String[][] fragenAntworten){
        BufferedWriter bw = null;
        File f = null;
        if(!fileName.equals("")){
            f = new File(fileName);
        }else{
            f = new File("Fragepool");
        }
        try{
            bw = new BufferedWriter(new FileWriter(f));
            bw.write("FRAGEPOOL - SEWFACHBEGRIFFE");
            bw.newLine();
            bw.write("++++++++++");
            bw.newLine();
            for(int i = 0; i < fragenAntworten[0].length; i++){
                if(fragenAntworten[0][i] != null && fragenAntworten[1][i] != null){
                    bw.write("Frage:"+fragenAntworten[0][i]);
                    bw.newLine();
                    bw.write("Antwort:"+fragenAntworten[1][i]);
                    bw.newLine();
                    bw.write("**********");
                    bw.newLine();
                }
                if(i == fragenAntworten[0].length - 1){
                    bw.write("++++++++++");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(bw!= null){
                try {
                    bw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Methode addFrage fügt eine Frage sowie die dazugehörige Antwort zum Fragenpool hinzu
     * @param fragenAntworten
     * @param frage
     * @param antwort
     */
    public void addFrage(String[][] fragenAntworten, String frage, String antwort){
        for(int i = 0; i < fragenAntworten.length; i++){
            if(fragenAntworten[0][i] == null && fragenAntworten[1][i] == null){
                fragenAntworten[0][i] = frage;
                fragenAntworten[1][i] = antwort;
            }
        }

    }
    public String[][] loadQuestions(String filePath) throws IOException {
        String[][] fragenAntworten = new String[50][2];     // Hier ist die Maximale Anzahl an Fragen und Antworten auf 50 festgelegt
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String question = null;
        String answer = null;
        int i = 0;

        // Lesen der Datei und Erstellen der Fragen-Antworten Paare
        while ((line = reader.readLine()) != null && i < 50) {
            if (line.startsWith("Frage:")) {
                question = line.substring(6).trim();    // Frage extrahieren
            } else if (line.startsWith("Antwort:")) {
                answer = line.substring(8).trim();     // Antwort extrahieren
            } else if (line.startsWith("**********")) {

                // Sobald ein Frage-Antwort Paar vollständig ist, in Array speichern
                if (question != null && answer != null) {
                    fragenAntworten[i][0] = question;
                    fragenAntworten[i][1] = answer;
                    i++;                                        // Der Index für die nächste Frage wird erhöht
                    question = null;
                    answer = null;
                } else if (question != null && answer == null) {
                    System.err.println("Keine Verfügbare Antwort");
                }else if (question == null && answer != null) {
                    System.err.println("Keine Verfügbare Frage");
                }
            }

        }
        reader.close();

        return fragenAntworten;
    }



        /**
         * Methode loadFragen ladet alle Fragen & Antworten aus der bestimmten Datei und speichert sie in einem mehrdimensionalen
         * String-Array und gibt sie anschließend zurück.
         * - ES DÜRFEN NICHT MEHR ALS 50 FRAGEN SEIN!
         * - JEGLICHE FEHLER WERDEN BEHANDELT!
         * - PATH noch nicht final!
         *
         * @param fileName
         * @return
         */
        public String[][] loadFragen(String fileName){
            String[][] fragenAntworten = new String[2][50];
            File f = null;
            String tempPath = "C:\\Users\\jades\\Documents\\GitHub\\ITP_Projekt\\SourceCode\\src\\FileSource\\Questions_Answer _QuizGame";
            BufferedReader br = null;
            int counterFragen = 0;
            int counterAntworten = 0;
            if(!fileName.equals("")){
                f = new File(fileName);
                try{
                    br = new BufferedReader(new FileReader(tempPath + f));
                    String line = "";
                    while((line = br.readLine()) != null){
                        if(line.startsWith("Frage:")){
                            String[] parts = line.split(":");
                            fragenAntworten[0][counterFragen] = parts[1];
                            counterFragen++;
                        }else if(line.startsWith("Antwort:")){
                            String[] parts = line.split(":");
                            fragenAntworten[1][counterAntworten] = parts[1];
                            counterAntworten++;
                        }
                    }

                }catch(IOException e){
                    e.printStackTrace();
                }finally {
                    if(br != null){
                        try {
                            br.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                System.err.println("File-Name ist nicht gültig!");
            }
            if(counterFragen != counterAntworten){
                if(counterFragen > counterAntworten){
                    System.err.println("Es sind mehr Fragen als Antworten! Bitte ausbessern");
                } else if (counterAntworten > counterFragen) {
                    System.err.println("Es sind mehr Antworten als Fragen! Bitte ausbessern!");
                }
            }
            return fragenAntworten;
        }
}
