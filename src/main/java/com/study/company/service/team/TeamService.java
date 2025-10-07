package com.study.company.service.team;

import com.study.company.domain.Team.Team;
import com.study.company.domain.Team.TeamRepository;
import com.study.company.dto.team.request.TeamCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void saveTeam(TeamCreateRequest request) {
        teamRepository.save(new Team(request.getName()));
    }

}
