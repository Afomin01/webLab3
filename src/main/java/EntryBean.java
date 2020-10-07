import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="entryBean")
@SessionScoped
public class EntryBean {
    Entry entry;

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

