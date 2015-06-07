package fr.iotqvt.master.modele.dao;

import java.util.Collection;

public interface DaoInterface<TMetier, TIdMetier> {

	/**
	 * create
	 *
	 * @param objet
	 *            m�tier contenant les donn�es n�cessaires � l'ajout
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int create(TMetier objetMetier) throws Exception;

	/**
	 * getOne
	 *
	 * @param identifiant
	 *            m�tier de l'objet recherch�
	 * @return objet m�tier trouv�, ou null sinon
	 */
	public TMetier getOne(TIdMetier idMetier) throws Exception;

	/**
	 * getAll
	 *
	 * @return collection de l'ensemble des objets m�tier disponibles; elle peut
	 *         �tre vide
	 */
	public Collection<TMetier> getAll() throws Exception;

	// /**
	// * retrieveMany
	// * @param objet contenant les crit�res de recherche, null si aucun crit�re
	// (retourner tous les objets)
	// * @return collection des objets m�tier trouv�; elle peut �tre vide
	// */
	// public Collection<TMetier> retrieveMany(String criteres) throws
	// Exception;
	/**
	 * update
	 *
	 * @param identifiant
	 *            m�tier de l'objet � modifier
	 * @param objet
	 *            m�tier modifi�
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int update(TIdMetier idMetier, TMetier objetMetier) throws Exception;

	/**
	 * delete
	 *
	 * @param identifiant
	 *            m�tier de l'objet � d�truire
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public int delete(TIdMetier idMetier) throws Exception;
}
