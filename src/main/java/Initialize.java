import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import models.User;

public class Initialize {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersDB");
		EntityManager em = emf.createEntityManager();
		
		for (User user : (ArrayList<User>) em.createQuery("SELECT u FROM User u").getResultList()) {
			System.out.println(user.toString());
		}
		
		User user = new User();
		user.setUserName("Santiago");
		user.setUserLastname("Abascal");
		user.setUserNickname("santiago.abascal");
		user.setUserEmail("futuropresidente@vox.es");
		user.setUserPassword("Contraseña");
		user.setPermissionID(1);
		
//		em.getTransaction().begin();
//		em.persist(user);
//		em.getTransaction().commit();
		
		em.getTransaction().begin();
		StoredProcedureQuery q = em.createStoredProcedureQuery("insert_user")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN)
				.setParameter(1, user.getUserName())
				.setParameter(2, user.getUserLastname())
				.setParameter(3, user.getUserNickname())
				.setParameter(4, user.getUserPassword())
				.setParameter(5, user.getUserEmail())
				.setParameter(6, user.getPermissionID());
		q.execute();
		em.getTransaction().commit();
		
	}
	
}
