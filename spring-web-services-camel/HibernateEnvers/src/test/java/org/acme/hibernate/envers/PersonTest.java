package org.acme.hibernate.envers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

	@Test
	public void testVersioning() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("manager1");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Project project = new Project("Project 1", "Hibernate", "Joe Blogs");

		entityManager.persist(project);

		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		Project project2 = entityManager.find(Project.class, project.getId());
		project2.setOwnerId("New Owner ID");
		entityManager.merge(project2);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		project2 = entityManager.find(Project.class, project.getId());
		project2.setOwnerId("New Owner ID 2");
		entityManager.merge(project2);
		entityManager.getTransaction().commit();
		
		entityManager.getTransaction().begin();
		project2 = entityManager.find(Project.class, project.getId());
		project2.setOwnerId("New Owner ID 3");
		entityManager.merge(project2);
		entityManager.getTransaction().commit();
		AuditReader reader = AuditReaderFactory.get(entityManager);
		Assert.assertEquals(4, reader.getRevisions(Project.class,project.getId()).size());
	}
}
