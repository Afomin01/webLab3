import javax.persistence.*;

@Entity
@Table(name = "LAB3_HITS")
public class EntryEntity {

    @Id @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    @Column(name = "R")
    private double r;

    @Column(name = "HIT")
    private int hit;

    public EntryEntity() {
    }

    public EntryEntity(double x, double y, double r, int hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public int getHit() {
        return hit;
    }
}
