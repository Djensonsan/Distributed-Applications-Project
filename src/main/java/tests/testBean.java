package tests;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "testEJB")
public class testBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public testBean() {
    }

    public String sayHello(){
        return "Hello from bean!";
    }

    public List <TestEntity> findByName(String name){
      Query query = this.em.createNativeQuery("select * from test", TestEntity.class);
//      query.setParameter("personName", name);
      query.setMaxResults(10);
      return query.getResultList();
    }
}