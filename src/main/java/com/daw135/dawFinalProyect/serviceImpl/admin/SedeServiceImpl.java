package com.daw135.dawFinalProyect.serviceImpl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.dto.admin.SedeDTO;
import com.daw135.dawFinalProyect.mapper.admin.SedeMapper;
import com.daw135.dawFinalProyect.repository.admin.SedeRepository;
import com.daw135.dawFinalProyect.service.admin.SedeService;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public List<SedeDTO> findAll() {
        try {
            return sedeRepository.findAll().stream().map(SedeMapper.INSTANCE::toSedeDTO).toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
