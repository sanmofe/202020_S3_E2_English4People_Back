/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.IdiomaEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.IdiomaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Diego Gonzalez Gomez
 */
@Stateless
public class IdiomaLogic {

    @Inject
    private IdiomaPersistence persistence;

    public IdiomaEntity createIdioma(IdiomaEntity idioma) throws BusinessLogicException {
        if (idioma.getAleman() || idioma.getCoreano() || idioma.getEspanol() || idioma.getFrances() || idioma.getIngles()
                || idioma.getItaliano() || idioma.getJapones() || idioma.getMandarin() || idioma.getPortugues()) {
            idioma = persistence.create(idioma);
            return idioma;
        } else {
            throw new BusinessLogicException("No hay ningún idioma seleccionado");
        }
    }

    public List<IdiomaEntity> getIdiomas() {
        List<IdiomaEntity> idiomas = persistence.findAll();
        return idiomas;
    }

    public IdiomaEntity getIdioma(Long idiomaId) {
        IdiomaEntity idiomaEntity = persistence.find(idiomaId);
        return idiomaEntity;
    }

    public IdiomaEntity updateIdioma(Long idiomaId, IdiomaEntity idiomaEntity) throws BusinessLogicException {
        if (idiomaEntity.getAleman() || idiomaEntity.getCoreano() || idiomaEntity.getEspanol() || idiomaEntity.getFrances() || idiomaEntity.getIngles()
                || idiomaEntity.getItaliano() || idiomaEntity.getJapones() || idiomaEntity.getMandarin() || idiomaEntity.getPortugues()) {
            IdiomaEntity newEntity = persistence.update(idiomaEntity);
            return newEntity;
        } else {
            throw new BusinessLogicException("No hay ningún idioma seleccionado");
        }
    }

    public void deleteIdioma(Long idiomaId) {
        persistence.delete(idiomaId);
    }
}
