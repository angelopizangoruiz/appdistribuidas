import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizango.denunciaservice.entity.Denuncia;
import com.pizango.denunciaservice.exceptions.GeneralServiceException;
import com.pizango.denunciaservice.exceptions.NoDataFoundException;
import com.pizango.denunciaservice.exceptions.ValidateServiceException;
import com.pizango.denunciaservice.repository.DenunciaRepository;
import com.pizango.denunciaservice.service.DenunciaService;

//import lombok.extern.slf4j.Slf4j;


@Service
public class DenunciaServiceImpl implements DenunciaService{

	@Autowired
	private DenunciaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Denuncia> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(NoDataFoundException e){
			//log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			//log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Denuncia> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
		}catch(ValidateServiceException | NoDataFoundException e){
			//log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			//log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Denuncia findById(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			//log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			//log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia save(Denuncia infraccion) {
		try {
			save(infraccion);
			if(repository.findByDni(infraccion.getDni())!=null) {
				throw new ValidateServiceException("Ya existe un registro con ese numero de documento " +infraccion.getDni());
			}
			Denuncia registro=repository.save(infraccion);
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia update(Denuncia denuncia) {
		try {
			save(denuncia);
			Denuncia registro=repository.findById(denuncia.getId()).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			Denuncia registroD=repository.findByDni(denuncia.getDni());
			if(registroD!=null && registroD.getId()!=denuncia.getId()) {
				throw new ValidateServiceException("Ya existe un registro con ese numero de documento " +denuncia.getDni());
			}
			registro.setDni(denuncia.getDni());
			registro.setFecha(denuncia.getFecha());
			registro.setTitulo(denuncia.getTitulo());
			registro.setDireccion(denuncia.getDireccion());
			registro.setDescripcion(denuncia.getDescripcion());
			repository.save(registro);
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			//log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			//log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			repository.delete(registro);
		}catch(ValidateServiceException | NoDataFoundException e){
			//log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			//log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}



}
