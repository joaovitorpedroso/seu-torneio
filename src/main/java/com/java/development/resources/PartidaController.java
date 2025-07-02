package com.java.development.resources;

import com.java.development.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

}
