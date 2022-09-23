package br.com.lecosas.WorldCup2022Data.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.lecosas.WorldCup2022Data.dto.TeamDto;
import br.com.lecosas.WorldCup2022Data.dto.TeamFormDto;
import br.com.lecosas.WorldCup2022Data.model.Team;
import br.com.lecosas.WorldCup2022Data.repository.TeamRepository;
import br.com.lecosas.WorldCup2022Data.service.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @GetMapping
    @Cacheable(value = "getTeams")
    public Page<TeamDto> getTeams(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Team> team = teamRepository.findAll(pageable);
        return TeamDto.convert(team);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "getTeams", allEntries = true)
    public ResponseEntity<TeamDto> createTeam(@RequestBody @Valid TeamFormDto teamCreateDto, UriComponentsBuilder uriComponentsBuilder) {
        Team team = teamCreateDto.convert();
        team = teamService.save(team);
        URI uri = uriComponentsBuilder.path("/teams/{id}").buildAndExpand(team.getId()).toUri();
        return ResponseEntity.created(uri).body(new TeamDto(team));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "getTeams", allEntries = true)
    public ResponseEntity<TeamDto> updateTeam(@PathVariable Integer id, @RequestBody @Valid TeamFormDto teamFormDto) {
        Optional<Team> teamOpt = teamRepository.findById(id);
        if (teamOpt.isPresent()) {
            Team team = teamService.update(teamOpt.get(), teamFormDto);
            return ResponseEntity.ok(new TeamDto(team));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @CacheEvict(value = "getTeams", allEntries = true)
    public ResponseEntity<TeamDto> getTeam(@PathVariable Integer id) {
        Optional<Team> teamOpt = teamRepository.findById(id);
        if (teamOpt.isPresent()) {
            return ResponseEntity.ok(new TeamDto(teamOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }
    
}
