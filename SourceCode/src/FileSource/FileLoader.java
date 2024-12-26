package FileSource;
import java.io.*;

public class FileLoader {

    public void saveFragen(String fileName){

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
        String tempPath = "C:\\Users\\Socac\\Desktop\\TGM\\2024_25\\SEW\\IntelliJ\\ITP_Projekt\\SourceCode\\src\\FileSource\\";
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
