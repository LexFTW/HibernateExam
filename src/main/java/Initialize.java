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
		
		// How to use SELECT with JPA:
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
		
		// How to use INSERT with JPA:
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
		// How to use STORED PROCEDURE with JPA:
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
		
		// How to use UPDATE with JPA:
		User userUpdate = em.find(User.class, 1);
		em.getTransaction().begin();
		userUpdate.setUserName("Nombre Actualizado");
		em.getTransaction().commit();
		
		
		// How to use DELETE with JPA:
		User userDelete = em.find(User.class, 9);
		em.getTransaction().begin();
		em.remove(userDelete);
		em.getTransaction().commit();
		
		// How to use SUBQUERY with JPA:
		ArrayList<User> users = (ArrayList<User>) em.createQuery("SELECT u FROM User u WHERE PermissionID = (SELECT p.permissionID FROM UserPermission p WHERE p.permissionName = 'Administrador')").getResultList();
		for (User u : users) {
			System.out.println(u.toString());
		}
		
		// How to use INNER JOIN with JPA:
	}
	
}
