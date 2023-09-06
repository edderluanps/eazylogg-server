package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.repositories.VeiculoRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo getVeiculo(Long id){
        return veiculoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Veículo não encontrado!"));
    }

    public List<Veiculo> getListaVeiculos(){
        return veiculoRepository.findAll();
    }

    public List<Veiculo> getVeiculoByUserId(Long id){
        return veiculoRepository.getVeiculoByUserId(id);
    }


    public Veiculo salvarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void atualizarVeiculo(Long id, Veiculo veiculo) {
        veiculoRepository.findById(id).map(obj -> {
            veiculo.setId(obj.getId());
            return veiculoRepository.save(veiculo);
        }).orElseThrow(() -> new ObjectNotFoundException("Veículo não encontrado!"));
    }

    public void deletarVeiculo(Long id) {
        getVeiculo(id);
        try{
            veiculoRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Veículo: Item Ativo.");
        }
    }

}
