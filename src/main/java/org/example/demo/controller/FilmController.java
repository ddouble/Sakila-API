package org.example.demo.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.demo.dto.FilmDto;
import org.example.demo.dto.FilmFullDto;
import org.example.demo.dto.FilmInputDto;
import org.example.demo.mapper.FilmMapper;
import org.example.demo.model.Film;
import org.example.demo.repository.FilmRepository;
import org.example.demo.utils.PageQuery;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/films")
@Validated
public class FilmController {
    private final FilmRepository filmRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public ResponseEntity<Page<FilmDto>> index(
            @Min(1)
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int pageSize,
            @Size.List({
                    @Size(max = 20, message = "Title must be less than 20 characters")
            })
            @RequestParam(name = "title", defaultValue = "") String title,
            @Pattern(regexp = "^[0-9]{4}$", message = "Release year must be 4 digits")
            @RequestParam(name = "release_year") String releaseYear
    ) {

        if (page < 1) page = 1;

//        // the repository way to get a list of entities
//        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("id"), Sort.Order.asc("title")));
//        Page<Film> filmList = filmRepository.findAll(pageable);


        // the jpql way can do complex query
        Page<Film> filmList = (new PageQuery(entityManager, page, pageSize)).fetch(
                """
                        SELECT f FROM Film f
                        WHERE f.title LIKE :title AND f.releaseYear = :releaseYear
                        ORDER BY f.id DESC, f.title ASC""",
                Map.of("title", "%" + title + "%",
                        "releaseYear", releaseYear),
                Film.class
        );

        return ResponseEntity.ok(
                filmList.map(FilmMapper.INSTANCE::toDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmFullDto> show(@PathVariable int id) {

//        // the repository way to get an entity
//        Film film = filmRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("Film not found")
//        );

        // the jpql way can do complex query
        TypedQuery<Film> query = entityManager.createQuery(
                "SELECT f FROM Film f WHERE f.id = :id",
                Film.class);
        query.setParameter("id", id);
        Film film = query.getSingleResult();

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


