package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pizzeria.model.Size;
import pizzeria.service.SizeService;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class SizeRestController {

    private final SizeService sizeService;

    @GetMapping("/sizes")
    public ResponseEntity<List<Size>> getAllSizes(){
        return new ResponseEntity<>(sizeService.getAllSizes(), HttpStatus.OK);
    }
}
