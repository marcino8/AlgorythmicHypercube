import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.poi.hdf.extractor.WordDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;



public class WordsCube {
    public static long wordCount(String word, String textToSearch) {
        return Arrays.stream(textToSearch.split(" "))
                .filter(s -> s.equals(word))
                .count();
    }

    public static void saveTextToTxtFile(String text, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.concat(".txt")));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            System.err.print("Error creating file named: " + path + ".txt");
        }
    }

    public static String replaceNumericValuesNotation(char currentDelimiter, char wantedDelimiter, String text) {
        return Arrays.stream(text.split(" "))
                .map(s -> s.indexOf(currentDelimiter) > 0 ? s.replace(currentDelimiter, wantedDelimiter) : s)
                .collect(Collectors.joining());
    }

    public static void wordDocsImport() {
        try {
            HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("SampleData/Hello.doc"));
            WordExtractor extractor= new WordExtractor(hwpfDocument);
            System.out.println(extractor.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
