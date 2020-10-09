import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntryDao {

    private static EntityManager em = HibernateUtil.getEm();

    public static List<Entry> getAllClientRows(long clientId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entry> cq = cb.createQuery(Entry.class);
        Root<Entry> c = cq.from(Entry.class);
        cq.where(cb.equal(c.get("clientId"), clientId));
        return em.createQuery(cq).getResultList();
    }

    public static void addEntry(Entry entry) {
        em.getTransaction().begin();
        em.persist(entry);
        em.getTransaction().commit();
    }
}
