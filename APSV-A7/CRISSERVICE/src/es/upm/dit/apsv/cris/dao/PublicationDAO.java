package es.upm.dit.apsv.cris.dao;

import java.util.List;

import es.upm.dit.apsv.cris.model.Publication;

public interface PublicationDAO {
	public Publication create( Publication Publication );
	public Publication read( String PublicationId );
	public Publication update( Publication Publication );
	public Publication delete( Publication Publication );

	public List<Publication> readAll();
	public List<Publication> readAllPublications(String researcherId);
}