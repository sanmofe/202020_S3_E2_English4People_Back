/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.IdiomaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@Stateless
public class IdiomaLogic
{
    @Inject
    private IdiomaPersistence persistence;
    
    public IdiomaEntity createIdioma(IdiomaEntity idioma) throws BusinessLogicException
    {
        if(idioma.getAleman() || idioma.getCoreano() || idioma.getEspanol() || idioma.getFrances() || idioma.getIngles()
                || idioma.getItaliano() || idioma.getJapones() || idioma.getMandarin() || idioma.getPortugues())
        {
            idioma = persistence.create(idioma);
            return idioma;
        }
        else
        {
            throw new BusinessLogicException("No hay ningun idioma seleccionado");
        }
    }
}
