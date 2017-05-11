package model;

import org.springframework.stereotype.Component;


@Component
public class BookLib {

    private String bookId;
    private String valAuthor;
    private String valTitle;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getValAuthor() {
        return valAuthor;
    }

    public void setValAuthor(String valAuthor) {
        this.valAuthor = valAuthor;
    }

    public String setValTitle() {
        return valTitle;
    }

    public void setValTitle(String valTitle) {
        this.valTitle = valTitle;
    }


}
