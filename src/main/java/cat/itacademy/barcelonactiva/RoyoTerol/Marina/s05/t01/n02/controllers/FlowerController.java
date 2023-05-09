package cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.dto.FlowerDto;
import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.exception.NoContentException;
import cat.itacademy.barcelonactiva.RoyoTerol.Marina.s05.t01.n02.model.service.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flowers")
@Tag(name = "Flowers Controller")
public class FlowerController {

    private FlowerService flowerService;

    private ResponseEntity<?> responseEntity;

    public FlowerController(FlowerService flowerService) {
        super();
        this.flowerService = flowerService;
    }

    @PostMapping("/create")
    @Operation(
            operationId = "Create a Flower",
            summary = "Fill up all the information necessary to create a flower into the database",
            description = "Enter the flower's name and country",
            responses = {
                    @ApiResponse(
                            responseCode = "201 - CREATED",
                            description = "Flower correctly created Response",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {@ExampleObject(name = "Flower Creation", value = "Flower")})
                    ),
                    @ApiResponse(
                            responseCode = "204 - NO_CONTENT",
                            description = "Wrong attributes for name or country fields Response."
                    ),
                    @ApiResponse(
                            responseCode = "500 - INTERNAL_SERVER_ERROR",
                            description = "Error creating flower Response."
                    )}
    )
    public ResponseEntity<?> createFlower(@RequestBody FlowerDto flowerDto){
        FlowerDto flowerResponse;

        try {
            if ((flowerDto == null) || (!flowerService.checkString(flowerDto.getFlowerName())) || (!flowerService.checkString(flowerDto.getFlowerCountry()))){
                responseEntity = new ResponseEntity<>(new NoContentException("Please, enter an acceptable name and country values"), HttpStatus.NO_CONTENT);
            } else {
                flowerResponse = flowerService.createFlower(flowerDto);
                responseEntity = new ResponseEntity<>(flowerResponse, HttpStatus.CREATED);
            }
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Error creating flower", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/getAll")
    @Operation(
            operationId = "Get all the flowers",
            summary = "Visualize all flowers and their information fields.",
            description = "Get all the flowers stored into the Database",
            responses = {
                    @ApiResponse(
                            responseCode = "200 - OK",
                            description = "Flowers list correctly retrieved from the Database Response",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {@ExampleObject(name = "Flowers list", value = "Flowers list")})
                    ),
                    @ApiResponse(
                            responseCode = "204 - NO_CONTENT",
                            description = "No flowers stored into the Database Response."
                    ),
                    @ApiResponse(
                            responseCode = "500 - INTERNAL_SERVER_ERROR",
                            description = "Error retrieving flowers Response."
                    )}
    )
    public ResponseEntity<?> getAllFlowers(){
        List<FlowerDto> flowers = null;

        try{
            flowers = flowerService.getAllFlowers();

            if (flowers.isEmpty()){
                responseEntity = new ResponseEntity<>(ResponseEntity.ofNullable(flowers), HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity<>(flowers, HttpStatus.OK);
            }
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(ResponseEntity.ofNullable(flowers), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/getOne/{id}")
    @Operation(
            operationId = "See the information about a certain flower",
            summary = "Enter the ID's flower to see its information",
            description = "Type the flowers' ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200 - OK",
                            description = "Specific flower correctly retrieved from the Database Response",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {@ExampleObject(name = "Flower", value = "Flower")})
                    ),
                    @ApiResponse(
                            responseCode = "404 - NOT_FOUND",
                            description = "Specific flower not found into the Database Response."
                    ),
                    @ApiResponse(
                            responseCode = "500 - INTERNAL_SERVER_ERROR",
                            description = "Error retrieving specific flower from the Database Response."
                    )}
    )
    public ResponseEntity<?> getOneFlower(@PathVariable("id") Integer id){
        FlowerDto flowerDtoRequest;

        try {
            flowerDtoRequest = flowerService.getOneFlower(id);

            if(flowerDtoRequest != null){
                responseEntity = new ResponseEntity<>(flowerDtoRequest, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Flower not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping("/update/{id}")
    @Operation(
            operationId = "Update a Flower",
            summary = "Update the information of an existing flower",
            description = "Type an {ID} to update Flower's name and country of any particular one.",
            responses = {
                    @ApiResponse(
                            responseCode = "202 - ACCEPTED",
                            description = "Flower updated Response",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                examples = {@ExampleObject(name = "Updated Flower", value = "Flower")})
                    ),
                    @ApiResponse(
                            responseCode = "404 - NOT_FOUND",
                            description = "Flower not found Response"
                    ),
                    @ApiResponse(
                            responseCode = "500 - INTERNAL_SERVER_ERROR",
                            description = "An Internal Server Error occurred Response."
                    )}
    )
    public ResponseEntity<?> updateOneFlower(@PathVariable("id") Integer id, @RequestBody FlowerDto flowerDtoUpdate){
        FlowerDto flowerDto;

        try {
            flowerDto = flowerService.updateFlower(flowerDtoUpdate, id);

            if (flowerDto != null){
                responseEntity = new ResponseEntity<>(flowerDto, HttpStatus.ACCEPTED);
            } else {
                responseEntity = new ResponseEntity<>("Flower not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("An Internal Server Error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            operationId = "Delete a Flower",
            summary = "Delete a specific flower from the Database",
            description = "Type an {ID} to delete an stored flower from the Database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200 - OK",
                            description = "Specific flower correctly deleted from the Database Response"
                    ),
                    @ApiResponse(
                            responseCode = "404 - NOT_FOUND",
                            description = "Flower not found Response"
                    ),
                    @ApiResponse(
                            responseCode = "500 - INTERNAL_SERVER_ERROR",
                            description = "An Internal Server Error occurred Response."
                    )}
    )
    public ResponseEntity<?> deleteOneFlower(@PathVariable("id") Integer id){
        boolean deleted;

        try {
            deleted = flowerService.deleteFlower(id);

            if (deleted){
                responseEntity = new ResponseEntity<>("Fruit deleted!", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Fruit not found!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}














