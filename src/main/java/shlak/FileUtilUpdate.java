package shlak;

import java.io.*;

/**
 * Created by ipredybaylo on 18-Apr-17.
 */
public interface FileUtilUpdate {

    // обновляем файл с помощью FileWriter
    void appendUsingOutputStream(String fileName, String data);

    // обновляем файл с помощью BufferedWriter
    void appendUsingBufferedWriter(String filePath, String text, int noOfLines);

    // добавляем информацию в файл с помощью FileWriter
    void appendUsingFileWriter(String filePath, String text);

}
