package br.com.williangiovanni.springbootrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.williangiovanni.springbootrestapi.data.PersonVO;
import br.com.williangiovanni.springbootrestapi.services.PersonService;
import br.com.williangiovanni.springbootrestapi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for managing people")
public class PersonController {

        @Autowired
        private PersonService personService;

        @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML })
        @Operation(summary = "Finds all People", description = "Finds all People", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public List<PersonVO> findByAll() {
                return personService.findAll();
        }

        // @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML })
        @Operation(summary = "Finds a Person", description = "Finds a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
                return personService.findById(id);
        }

        // @CrossOrigin(origins = {"http://localhost:8080", ""})
        @PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        @Operation(summary = "Add a new Person", description = "Add a new Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public PersonVO createPerson(@RequestBody PersonVO person) throws Exception {
                return personService.createPerson(person);
        }

        @PutMapping(value = "/update", produces = { MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        @Operation(summary = "Updates a Person", description = "Updates a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public PersonVO updatePerson(@RequestBody PersonVO person) throws Exception {
                return personService.updatePerson(person);
        }

        @DeleteMapping(value = "/delete/{id}")
        @Operation(summary = "Delete a Person", description = "Delete a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) throws Exception {
                personService.deletePerson(id);
                return ResponseEntity.noContent().build();
        }
}
