package br.com.lecosas.WorldCup2022Data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.lecosas.WorldCup2022Data.model.Team;
import lombok.Getter;

@Getter
public class TeamCreateDto {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String flagURL;
    
    public Team convert() {
        return new Team(null, this.name, this.flagURL);
    }
   
}
