package com.example.temp.baseball.service;

import com.example.temp.baseball.dto.FindGamesByTeamCommand;
import com.example.temp.baseball.dto.FindGamesByTeamResponse;

public interface FindGamesByTeamService {
    FindGamesByTeamResponse doService(FindGamesByTeamCommand command);
}
