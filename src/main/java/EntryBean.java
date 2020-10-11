import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ManagedBean(name="entryBean")
@SessionScoped
public class EntryBean {
    private Entry entry;

    private long clientId=0L;

    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void addCurrentEntry() {
        Entry newEntry = new Entry(entry.getX(), entry.getY(),entry.getR(), check(entry.getX(), entry.getY(), entry.getR()), clientId);
        EntryDao.addEntry(newEntry);

        entries.add(newEntry);
    }

    public void clearClientEntries(){
        entries.clear();
        EntryDao.deleteAllClientRows(clientId);
    }

    public boolean check(double x, double y, double r) {
        if (x > 0 && y > 0) return false;
        else if (x < 0 && y > 0) return y < 2 * x + r;
        else if (x < 0 && y < 0) return x * x + y * y < r * r;
        else if (x > 0 && y < 0) return x < r / 2 && y > -r;
        else if (x == 0) return y <= r && y >= -r;
        else if (y == 0) return x <= r / 2 && x >= -r;
        else return false;
    }

    public EntryBean() {
        this.entry = new Entry();
        entry.setR(1);

        if(clientId==0){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            try {
                clientId = Long.parseLong(request.getAttribute("clientID").toString());
            }catch (Exception e){
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                clientId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                Cookie cookie = new Cookie("web_lab3_client_id",Long.toString(clientId));
                cookie.setMaxAge(31536000);
                response.addCookie(cookie);
            }
        }

        entries = EntryDao.getAllClientRows(clientId);
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

