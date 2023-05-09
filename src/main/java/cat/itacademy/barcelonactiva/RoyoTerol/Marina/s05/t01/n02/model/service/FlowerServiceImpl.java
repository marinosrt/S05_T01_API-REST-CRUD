package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.dto.FlowerDto;
import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.exception.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.repository.FlowerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private ModelMapper modelMapper;

    private FlowerRepository flowerRepository;

    static private Flower flowerRequest;
    //region SERVICE

    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        super();
        this.flowerRepository = flowerRepository;
    }

    public FlowerDto convertToDTO(Flower flower) {
        return modelMapper.map(flower, FlowerDto.class);
    }

    public Flower convertToEntity(FlowerDto flowerDto) {
        return modelMapper.map(flowerDto, Flower.class);
    }

    @Override
    public FlowerDto createFlower(FlowerDto flowerDto) {

        flowerRequest = flowerRepository.save(convertToEntity(flowerDto));

        return convertToDTO(flowerRequest);

    }

    @Override
    public List<FlowerDto> getAllFlowers() {
        return flowerRepository.findAll().stream()
                .map(flower -> modelMapper.map(flower, FlowerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlowerDto getOneFlower(Integer id) {

        flowerRequest = flowerRepository.findById(id).orElseThrow(() -> new FlowerNotFoundException("Flower", id));

        return convertToDTO(flowerRequest);
    }

    @Override
    public FlowerDto updateFlower(FlowerDto flowerDto, Integer id) {

        flowerRequest = flowerRepository.findById(id).orElseThrow(() -> new FlowerNotFoundException("Flower", id));
        flowerRequest.setFlowerName(flowerDto.getFlowerName());
        flowerRequest.setFlowerCountry(flowerDto.getFlowerCountry());

        flowerRepository.save(flowerRequest);

        return convertToDTO(flowerRequest);
    }

    @Override
    public boolean deleteFlower(Integer id) {
        boolean found;

        try {
            flowerRepository.deleteById(id);
            found = true;
        } catch (FlowerNotFoundException e) {
            throw new FlowerNotFoundException("Flower", id);
        }

        return found;

    }

    //endregion SERVICE

    //region USEFUL

    public boolean checkString(String string){
        return ((string != null) && (!string.trim().isEmpty()));
    }

    //endregion USEFUL
}
