package shlak;

/**
 * Created by ipredybaylo on 18-Apr-17.
 */
public interface FileUtilWriter {
    // пишем в файл с помощью OutputStream
    public void writeUsingOutputStream(String data);

    // пишем в файл с помощью Files
    public void writeUsingFiles(String data);

    // пишем в файл с помощью BufferedWriter
    public void writeUsingBufferedWriter(String data, int noOfLines);

    // пишем в файл с помощью FileWriter
    public void writeUsingFileWriter(String data);

}
