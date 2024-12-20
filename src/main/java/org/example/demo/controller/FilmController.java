package org.example.demo.controller;

import jakarta.persistence.EntityManager;
import org.example.demo.dto.FilmDto;
import org.example.demo.dto.FilmFullDto;
import org.example.demo.dto.FilmInputDto;
import org.example.demo.mapper.FilmMapper;
import org.example.demo.model.Film;
import org.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    private final FilmRepository filmRepository;

    @Autowired
    private EntityManager entityManager;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public ResponseEntity<Page<FilmDto>> index(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(name = "page_size", defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id"), Sort.Order.asc("title")));

        Page<Film> list = filmRepository.findAll(pageable);

        return ResponseEntity.ok(
                list.map(FilmMapper.INSTANCE::toDto)
        );
    }

    private Film test(Integer id) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Film not found")
        );

        return film;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmFullDto> show(@PathVariable int id) {

        Film film = filmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Film not found")
        );

        return ResponseEntity.ok(
                FilmMapper.INSTANCE.toFullDto(film)
        );
    }

    @PostMapping
    public ResponseEntity<FilmDto> create(@RequestBody FilmInputDto filmInputDto) {
        Film filmInput = FilmMapper.INSTANCE.toEntity(filmInputDto);
        filmInput.setLastUpdate(Instant.now());
        Film filmCreated = filmRepository.save(filmInput);

        // !!!
        // detach entity
        // otherwise, the following entity fetched by same id will have no some relationship data except id
        entityManager.detach(filmCreated);
        // or detach all entities
        // entityManager.clear();

        Film filmOutput = filmRepository.findById(filmCreated.getId()).orElseThrow(
                () -> new RuntimeException("Film not found")
        );

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(FilmMapper.INSTANCE.toDto(filmOutput));
    }

    // update controller
    @PutMapping("/{id}")
    public ResponseEntity<FilmDto> update(@PathVariable int id, @RequestBody FilmInputDto filmInputDto) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Film not found")
        );

        Film filmInput = FilmMapper.INSTANCE.toEntity(filmInputDto);
        filmInput.setId(id);
        filmInput.setLastUpdate(Instant.now());
        Film filmUpdated = filmRepository.save(filmInput);

        // !!!
        // detach entity
        // otherwise, the following entity fetched by same id will have no some relationship data except id
        entityManager.detach(filmUpdated);
        // or detach all entities
        // entityManager.clear();

        Film filmOutput = filmRepository.findById(filmUpdated.getId()).orElseThrow(
                () -> new RuntimeException("Film not found")
        );
        return ResponseEntity.ok(
                FilmMapper.INSTANCE.toDto(filmOutput)
        );
    }

    // delete controller
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> destroy(@PathVariable int id) {
        Film film = filmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Film not found")
        );

        filmRepository.delete(film);

        return ResponseEntity.ok(Collections.singletonMap("message", "Film deleted successfully"));
    }

}


