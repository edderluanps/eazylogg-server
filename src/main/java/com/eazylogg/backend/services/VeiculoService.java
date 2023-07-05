package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo getVeiculo(Long id){
        return veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado!"));
    }

    public List<Veiculo> getListaVeiculos(){
        return veiculoRepository.findAll();
    }

    public Veiculo salvarVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void atualizarVeiculo(Long id, Veiculo veiculo) {
        veiculoRepository.findById(id).map(obj -> {
            veiculo.setId(obj.getId());
            return veiculoRepository.save(veiculo);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado!"));
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.findById(id).map(obj -> {
            veiculoRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado!"));
    }

}
