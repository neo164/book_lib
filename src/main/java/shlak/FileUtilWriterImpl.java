package shlak;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by ipredybaylo on 18-Apr-17.
 */
public class FileUtilWriterImpl implements FileUtilWriter {

    // пишем в файл с помощью OutputStream
    public void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("library.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // пишем в файл с помощью Files
    public void writeUsingFiles(String data) {
        try {
            Files.write(Paths.get("library.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // пишем в файл с помощью BufferedWriter
    public void writeUsingBufferedWriter(String data, int noOfLines) {
        File file = new File("library.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine = data + System.getProperty("line.separator");
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for (int i = noOfLines; i > 0; i--) {
                br.write(dataWithNewLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // пишем в файл с помощью FileWriter
    public void writeUsingFileWriter(String data) {
        File file = new File("library.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
