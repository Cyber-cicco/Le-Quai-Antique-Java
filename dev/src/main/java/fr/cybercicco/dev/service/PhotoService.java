package fr.cybercicco.dev.service;

import fr.cybercicco.dev.entity.Photo;
import fr.cybercicco.dev.entity.Plat;
import fr.cybercicco.dev.repository.PhotoRepository;
import fr.cybercicco.dev.repository.PlatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class PhotoService {

    private final PlatRepository platRepository;

    private final PhotoRepository photoRepository;

    public void uploadPhoto(Integer id, MultipartFile file) {


    }

}
