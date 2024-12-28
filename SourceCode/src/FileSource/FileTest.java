package FileSource;

public class FileTest {
    public static void main(String[] args) {
        FileLoader fl = new FileLoader();
        String[][] fragen = fl.loadFragen("Questions_Answer _QuizGame");
        System.out.println(fragen[0][0]);
        System.out.println(fragen[1][0]);

    }
}
