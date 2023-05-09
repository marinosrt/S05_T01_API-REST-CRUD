package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowerDto {

    private Integer id;

    private String flowerName;

    private String flowerCountry;

    private String typeFlower;

    private final List<String> countries = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus", "Czech Republic",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta",
            "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"));


    public FlowerDto (String flowerName, String flowerCountry){
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.typeFlower = setTypeCountry(flowerCountry);
    }

    public String setTypeCountry(String flowerCountry){
        return this.typeFlower = countries.stream().anyMatch(country -> country.equalsIgnoreCase(flowerCountry)) ? "UE" : "Not UE";
    }
}
