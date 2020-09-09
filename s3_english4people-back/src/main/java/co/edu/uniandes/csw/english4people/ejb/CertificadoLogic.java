/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.english4people.ejb;

import co.edu.uniandes.csw.english4people.entities.CertificadoEntity;
import co.edu.uniandes.csw.english4people.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.english4people.persistence.CertificadoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Andres Santiago
 */
@Stateless
public class CertificadoLogic {
    
    @Inject
    CertificadoPersistence persistence;
    
    public CertificadoEntity createCertificado(Long profesoresId, CertificadoEntity certificado) throws BusinessLogicException{
        if (certificado.getNombre() == null || certificado.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del certificado esta vacío");
        }
        if (certificado.getIdioma() == null || certificado.getIdioma().isEmpty()) {
            throw new BusinessLogicException("El espacio de idioma esta vacío");
        }
        persistence.create(certificado);
        return certificado;
    }
    
     public List<CertificadoEntity> getCertificados(Long profesoresId) {
        List<CertificadoEntity> certificados = persistence.findAll();
        return certificados;
    }

    public CertificadoEntity getCertificado(Long profesoresId, Long certificadoId) {
        CertificadoEntity certificadoEntity = persistence.find(profesoresId,certificadoId);
        return certificadoEntity;
    }
    
    public CertificadoEntity updateCertificado(Long certificadoId, CertificadoEntity certificado) throws BusinessLogicException {
        if (certificado.getNombre() == null || certificado.getNombre().isEmpty()) {
            throw new BusinessLogicException("El nombre del certificado esta vacío");
        }
        if (certificado.getIdioma() == null || certificado.getIdioma().isEmpty()) {
            throw new BusinessLogicException("El espacio de idioma esta vacío");
        }
        
        CertificadoEntity newEntity = persistence.update(certificado);
        return newEntity;
    }
    
    public void deleteCertificado(Long profesoresId, Long certificadoId) throws BusinessLogicException{
        CertificadoEntity old = getCertificado(profesoresId, certificadoId);
        if(old == null){
            throw new BusinessLogicException("El certificado con id ="+certificadoId+"no esta asociado a el profesor con id="+profesoresId);
        }
        persistence.delete(old.getId());
    }
    
    
}
