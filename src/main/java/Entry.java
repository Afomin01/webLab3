import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "LAB3_HITS")
public class Entry {

    @Id @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LAB3_HITS_SEQ")
    @SequenceGenerator(name="LAB3_HITS_SEQ", sequenceName="LAB3_HITS_SEQ", allocationSize=1)
    private int id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    @Column(name = "R")
    private double r;

    @Column(name = "RESULT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean result;

    @Column(name = "CLIENT_ID")
    private long clientId;

    public Entry() {
    }

    public Entry(double x, double y, double r, boolean result, long clientId) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.clientId = clientId;
    }


    public long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
