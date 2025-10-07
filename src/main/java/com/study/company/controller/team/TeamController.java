package com.study.company.controller.team;

import com.study.company.dto.team.request.TeamCreateRequest;
import com.study.company.dto.team.request.response.TeamResponse;
import com.study.company.service.team.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team")
    public void saveTeam(@RequestBody TeamCreateRequest request) {
        teamService.saveTeam(request);
    }

    @GetMapping("/team")
    public List<TeamResponse> getTeams() {
        return teamService.getTeams();
    }
}
