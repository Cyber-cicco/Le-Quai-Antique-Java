package fr.cybercicco.dev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/photos/")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class PhotoController {

    @GetMapping(value = "{photo}",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String photo) throws IOException {
        Resource resource = new FileSystemResource("/home/hijokaidan/Documents/Studi/Projets/ECF/Le-Quai-Antique-Java/dev/images/"+photo);
        return resource.getContentAsByteArray();
    }
}
