import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ManagedBean(name="entryBean", eager = true)
@SessionScoped
public class EntryBean {
    private Entry entry;

    private long clientId;

    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void addCurrentEntry() {
        double tempR = entry.getR();

        Entry newEntry = new Entry();
        newEntry.setX(entry.getX());
        newEntry.setY(entry.getY());
        newEntry.setR(tempR);
        newEntry.setResult(check(entry.getX(), entry.getY(), entry.getR()));
        newEntry.setClientId(clientId);
        EntryDao.addEntry(newEntry);

        entries.add(newEntry);
    }

    public boolean check(double x, double y, double r) {
        if (x > 0 && y > 0) return false;
        else if (x < 0 && y > 0) return y < 2 * x + r;
        else if (x < 0 && y < 0) return x * x + y * y < r * r;
        else if (x > 0 && y < 0) return x < r / 2 && y > -r;
        else if (x == 0) return y < r && y > -r;
        else if (y == 0) return x < r / 2 && x > -r;
        else return false;
    }

    public EntryBean() {
        this.entry = new Entry();
        entry.setR(1);

        Cookie clientIdCookie = CookieHelper.getCookie("web_lab3_client_id");
        if (clientIdCookie == null) {
            clientId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            CookieHelper.setCookie("web_lab3_client_id",
                    String.valueOf(clientId), 31536000);
        } else {
            try {
                clientId = Long.parseLong(clientIdCookie.getValue());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
        }

        entries = EntryDao.getAllClientRows(clientId);
        System.out.println(entries.size());
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

