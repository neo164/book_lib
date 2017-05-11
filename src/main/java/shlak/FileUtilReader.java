package shlak;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by ipredybaylo on 18-Apr-17.
 */
public interface FileUtilReader {
    void readUsingFileReader(String fileName) throws IOException;

    void readUsingBufferedReader(String fileName, Charset cs) throws IOException;

    void readUsingBufferedReaderJava7(String fileName, Charset cs) throws IOException;

    void readUsingBufferedReader(String fileName) throws IOException;

    void readUsingScanner(String fileName) throws IOException;

    void readUsingFiles(String fileName) throws IOException;


}
