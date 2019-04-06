package UI;

import javafx.beans.property.SimpleStringProperty;

public class Objectt {
   private SimpleStringProperty firstCol;
   private SimpleStringProperty secondCol;

    public Objectt() {
            this("", "");
        }
 
    public Objectt(String firstCol, String secondCol) {
        this.firstCol=new SimpleStringProperty(firstCol);
        this.secondCol=new SimpleStringProperty(secondCol);
    }

    public String getFirstCol() {
        return firstCol.get();
    }
 
    public void setFirstCol(String fName) {
        firstCol.set(fName);
    }
        
    public String getSecondCol() {
        return secondCol.get();
    }
    
    public void setSecondCol(String fName) {
        secondCol.set(fName);
    }
}
