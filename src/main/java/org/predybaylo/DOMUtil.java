package org.predybaylo;

import org.w3c.dom.NodeList;

/**
 * Created by Neo_notebook on 19.04.2017.
 */
public interface DOMUtil {
    void showDom(String fileName,NodeList book);
    void addToLib(String fileName,String valTitle,String valName, String valDateModify,String valCost,String valPath,String id);
    void searchBook(String fileName, String valTitle, String valNameAuth, String valId);
    void removeBook(String fileName, String valTitle, String valNameAuth, String valId);
    int getIdMax(String fileName);

}
