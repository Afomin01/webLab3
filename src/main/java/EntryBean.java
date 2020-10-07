import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="entryBean", eager = true)
@SessionScoped
public class EntryBean {
    private Entry entry;

    private ArrayList<Entry> entries = new ArrayList<>();

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void addCurrentEntry(){
        entry.check();
        entries.add(entry);
        entry = new Entry();
    }

    public EntryBean() {
        this.entry = new Entry();
    }

    public double getX() {
        return entry.getX();
    }

    public void setX(double x) {
        entry.setX(x);
    }

    public double getY() {
        return entry.getY();
    }

    public void setY(double y) {
        entry.setY(y);
    }

    public double getR() {
        return entry.getR();
    }

    public void setR(double r) {
        entry.setR(r);
    }

    public boolean isResult() {
        return entry.isResult();
    }

    public void setResult(boolean result) {
        entry.setResult(result);
    }


}

