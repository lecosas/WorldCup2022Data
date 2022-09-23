package br.com.lecosas.WorldCup2022Data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lecosas.WorldCup2022Data.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    
}
