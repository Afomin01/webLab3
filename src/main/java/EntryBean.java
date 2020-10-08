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
        double tempR = entry.getR();
        entry.setResult(check(entry.getX(), entry.getY(), entry.getR()));
        entries.add(entry);
        entry = new Entry();
        entry.setR(tempR);
    }

    public boolean check(double x, double y, double r){
        if(x>0 && y>0) return false;
        else if(x<0 && y>0) return y < 2*x+r;
        else if(x<0 && y<0) return x*x+y*y<r;
        else if(x>0 && y<0) return x<r/2 && y>-r;
        else if(x==0) return y<r && y>-r;
        else if(y==0) return x<r/2&&x>-r;
        else return false;
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

