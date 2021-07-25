package net.reusche.backend.apirest.service;

import net.reusche.backend.apirest.entity.Empresa;
import net.reusche.backend.apirest.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getEmpresas(){
        return empresaRepository.findAll();
    }

    public void createEmpresa(Empresa empresa){
        empresaRepository.save(empresa);
    }

    public void updateEmpresa(Empresa empresa){
        empresaRepository.save(empresa);
    }

    public void changeEmpresaActivo(Empresa empresa){
        empresa.setActivo(!empresa.isActivo());
        empresaRepository.save(empresa);
    }

    public Optional<Empresa> getByIdEmpresa(int idEmpresa){
        return empresaRepository.findById(idEmpresa);
    }
}
