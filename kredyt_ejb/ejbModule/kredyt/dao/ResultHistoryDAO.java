package kredyt.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import kredyt.entities.ResultHistory;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ResultHistoryDAO {
	private final static String UNIT_NAME = "kredytDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(ResultHistory history) {
		em.persist(history);
	}

	public ResultHistory merge(ResultHistory history) {
		return em.merge(history);
	}

	public void remove(ResultHistory history) {
		em.remove(em.merge(history));
	}

	public ResultHistory find(Object id) {
		return em.find(ResultHistory.class, id);
	}

	public List<ResultHistory> getFullList() {
		List<ResultHistory> list = null;

		Query query = em.createQuery("select h from ResultHistory h");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ResultHistory> getList() {
		List<ResultHistory> list = null;

		// 1. Build query string with parameters
		String select = "select h ";
		String from = "from ResultHistory h ";
		String where = "";
		String orderby = "order by h.data";

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
