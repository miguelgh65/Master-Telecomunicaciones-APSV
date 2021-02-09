package es.upm.dit.apsv.cris.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.apsv.cris.model.Publication;

public class PublicationDAOImplementation implements PublicationDAO {

	private static PublicationDAOImplementation instance = null;
	private PublicationDAOImplementation () {}
	public static PublicationDAOImplementation getInstance() {
		if (null == instance)
			instance = new PublicationDAOImplementation();
		return instance;
	}
	
	@Override
	public Publication create(Publication Publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(Publication);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally{
			session.close();
		}
		return Publication;
	}

	@Override
	public Publication read(String PublicationId) {
		Session session = SessionFactoryService.get().openSession();
		Publication r = null;
		try {
			session.beginTransaction();
			r = session.get(Publication.class, PublicationId);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally{
			session.close();
		}
		return r;
	}

	@Override
	public Publication update(Publication Publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(Publication);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally{
			session.close();
		}
		return Publication;
	}

	@Override
	public Publication delete(Publication Publication) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(Publication);
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally{
			session.close();
		}
		return Publication;
	}

	@Override
	public List<Publication> readAll() {
		Session session = SessionFactoryService.get().openSession();
		List<Publication> l = null;
		try {
			session.beginTransaction();
			l = (List<Publication>) session.createQuery("from Publication").getResultList();
			session.getTransaction().commit();
		}catch(Exception e) {
			
		}finally{
			session.close();
		}
		return l;
	}

	@Override
	public List<Publication> readAllPublications(String researcherId) {
		List<Publication> l = new ArrayList<Publication>();
		for (Publication r : this.readAll())
			if(r.getAuthors().indexOf(researcherId) > -1)
				l.add(r);
		return l;
	}

}

