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
            String defPath = "./";
            BufferedReader br = null;
            int counterFragen = 0;
            int counterAntworten = 0;
            if(!fileName.equals("")){
                f = new File(fileName);
                try{
                    br = new BufferedReader(new FileReader(defPath + f));
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

    /**
     * Methode addFrage fügt eine Frage sowie die dazugehörige Antwort zum Fragenpool hinzu
     * @param fragenAntworten
     * @param frage
     * @param antwort
     */
    public void addFrage(String[][] fragenAntworten, String frage, String antwort) {
        for (int i = 0; i < fragenAntworten.length; i++) {
            if (fragenAntworten[0][i] == null && fragenAntworten[1][i] == null) {
                fragenAntworten[0][i] = frage;
                fragenAntworten[1][i] = antwort;
            }
        }
    }

    /**
     * Methode textFormat speichert den Fragepool aus einem mehrdimensionalen Array in einem String
     * - WIRD NUR FÜR DIE DATEIVERWALTUNG VERWENDET
     *
     * @param fileName
     * @return
     */
    public String textFormat(String fileName){
        String text = "";
        String[][] fragenAntworten = loadFragen(fileName);
        for (int i = 0; i < fragenAntworten[0].length; i++) {
            if (fragenAntworten[0][i] != null && fragenAntworten[1][i] != null) {
                text += "Frage:" +fragenAntworten[0][i] +"\n";
                text += "Antwort:" +fragenAntworten[1][i] +"\n\n";
            }
        }
        return text;
    }

    /**
     * METHODE saveFormat speichert einen String bzw. die vom Benutzer eingegebenen Fragen im Dateiverwaltung Panel in einem merhdimensionalen damit er in eine
     * Datei gespeichert werden kann
     * - WIRD NUR FÜR DIE DATEIVERWALTUNG VERWENDET
     * @param pool
     * @return
     */
    public String[][] saveFormat(String pool){
        String[][] fragepool = new String[2][50];

        String[] split = pool.split("\n");

        int frageIndex = 0;
        int antwortIndex = 0;

        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("Frage:")) {
                fragepool[0][frageIndex] = split[i];
                frageIndex++;
                System.out.println("Frage: " + split[i]);
            } else if (split[i].startsWith("Antwort:")) {
                fragepool[1][antwortIndex] = split[i];
                antwortIndex++;
                System.out.println("Antwort: " + split[i]);
            }
        }

        return fragepool;
    }
}