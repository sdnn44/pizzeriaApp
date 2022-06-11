package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzeria.model.Size;
import pizzeria.repository.SizeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    public List<Size> getAllSizes(){
        return sizeRepository.getAllSizes();
    }
    public Optional<Size> getSizeById(int id) {return sizeRepository.getSizeById(id);}
}
