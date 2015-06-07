package fr.iotqvt.master.modele.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface JdbcInterface {
	/**
     * Connexion � la base de donn�es
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void connecter() throws ClassNotFoundException, SQLException   ;

    /**
     * D�connexion � la base de donn�es
     * @throws SQLException 
     */
    public void deconnecter() throws SQLException   ;

    /**
     * D�marrer une transaction JDBC sur la base de donn�es
     * @throws SQLException 
     */
    public void debuterTransaction()  throws SQLException  ;

    /**
     * Fin de la transaction en validant les modifications depuis 
     * le d�but de la transaction
     * @throws SQLException 
     */
    public void validerTransaction()  throws SQLException  ;

    /**
     * Fin de la transaction en annulant les modifications depuis 
     * le d�but de la transaction
     * @throws SQLException 
     */
    public void annulerTransaction()  throws SQLException ;

    /**
     * Ex�cution d'une requ�te SQL SELECT non param�tr�e
     * @param requete texte de la requ�te
     * @return jeu d'enregistrements r�sultant
     * @throws SQLException 
     */
    public ResultSet consulter(String requete) throws SQLException ;

    
    /**
     * Ex�cution d'une requ�te SQL SELECT param�tr�e
     * @param requete texte de la requ�te param�tr�e
     * @param param liste des valeurs des param�tres
     * @return jeu d'enregistrements r�sultant
     * @throws SQLException 
     */
    public ResultSet consulter(String requete, List param) throws SQLException ;

    /**
     * Ex�cution d'une requ�te SQL UPDATE, INSERT ou DELETE
     * @param requete texte de la requ�te non param�tr�e
     * @return nombre d'enregistrements mis � jour
     * @throws SQLException 
     */
    public int mettreAJour(String requete) throws SQLException ;
    
     /**
     * Ex�cution d'une requ�te SQL UPDATE, INSERT ou DELETE param�tr�e
     * @param requete texte de la requ�te param�tr�e
     * @param param liste des valeurs des param�tres
     * @return nombre d'enregistrements mis � jour
     * @throws SQLException 
     */
    public int mettreAJour(String requete, List param) throws SQLException;

     /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef g�n�r�e automatiquement
     * utile par exemple pour ins�rer simultan�ment dans deux tables li�es par une 
     * r�f�rence sur cette clef
     * @param String requete : texte de la requ�te SQL non param�tr�e
     * @return Resultset contenant le dernier ID g�n�r�
     *
     */
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete) throws SQLException;

     /**
     * mettreAJourAvecClefsGenereesRetournees()
     * idem mettreAJour, mais retourne la valeur de la clef g�n�r�e automatiquement
     * utile par exemple pour ins�rer simultan�ment dans deux tables li�es par une 
     * r�f�rence sur cette clef
     * @param String requete : texte de la requ�te SQL param�tr�e
     * @param List param : liste des valeurs des param�tres
     * @return Resultset contenant le dernier ID g�n�r�
     *
     */
    public ResultSet mettreAJourAvecClefsGenereesRetournees(String requete, List param) throws SQLException;

	/** Fournit la liste des noms de champs du r�sultat d'une requ�te 
	*@param ResultSet rs r�sultat d'une requ�te sur la BD associ�e � la connexion
	*@return List : liste des noms de colonnes d'apr�s les m�ta  donn�es de la BD
	*/
    public  List getNomColonnes (ResultSet rs )  throws SQLException;
}
