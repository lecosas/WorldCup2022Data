package br.com.lecosas.WorldCup2022Data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lecosas.WorldCup2022Data.model.Team;
import br.com.lecosas.WorldCup2022Data.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team save(Team team) {
        return teamRepository.save(team);
    }
    
}
