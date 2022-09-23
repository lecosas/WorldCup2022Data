package br.com.lecosas.WorldCup2022Data.dto;

import org.springframework.data.domain.Page;

import br.com.lecosas.WorldCup2022Data.model.Team;
import lombok.Getter;

@Getter
public class TeamDto {

    private Integer id; 
    private String name;
    private String flagURL;
    
    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.flagURL = team.getFlagURL();
    }

    public static Page<TeamDto> convert(Page<Team> teams) {
        return teams.map(TeamDto::new);        
    }
    
}
