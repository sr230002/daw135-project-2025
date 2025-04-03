package com.daw135.dawFinalProyect.serviceImpl.eventos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.eventos.TipoEventoDTO;
import com.daw135.dawFinalProyect.mapper.eventos.EventoMapper;
import com.daw135.dawFinalProyect.repository.eventos.TipoEventoRepository;
import com.daw135.dawFinalProyect.service.eventos.TipoEventoService;

@Service
public class TipoEventoServiceImpl implements TipoEventoService {

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    @Override
    public List<TipoEventoDTO> findAll() {
        try {
            return tipoEventoRepository.findAll().stream().map(EventoMapper.INSTANCE::toTipoEventoDTO).toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
