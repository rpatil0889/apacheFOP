package com.idigi.cotroller;

import com.idigi.entity.UserDTO;
import com.idigi.service.FOPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class PDFController {
    @Autowired
    private FOPService fopService;

    @GetMapping("/generate/{id}")
    public ResponseEntity<String> generatePDF(@PathVariable int id) throws JAXBException, FileNotFoundException {

        fopService.generateWelcomePDF(id);
        return ResponseEntity.ok("pdf generated successfully");
    }
    @PostMapping(path = "/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(fopService.addUser(userDTO));
    }
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<UserDTO> addUser(@PathVariable int id) {
        return ResponseEntity.ok(fopService.getUser(id));
    }
}
