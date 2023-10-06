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

    public Veiculo buscarVeiculoPorId(Long id){
        return veiculoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Veículo não encontrado!"));
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    public List<Veiculo> buscarVeiculoPorUsuarioId(Long id){
        return veiculoRepository.buscarVeiculoPorUsuarioId(id);
    }


    public Veiculo salvarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        veiculo = buscarVeiculoPorId(id);
        if (veiculo == null){
            return veiculoRepository.save(veiculo);
        }else{
            throw new ObjectNotFoundException("Veículo não encontrado!");
        }
    }

    public void deletarVeiculo(Long id) {
        buscarVeiculoPorId(id);
        try{
            veiculoRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Veículo: Item Ativo.");
        }
    }

}
