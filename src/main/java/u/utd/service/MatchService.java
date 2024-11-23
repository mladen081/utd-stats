package u.utd.service;

import u.utd.dto.MatchDto;

import java.util.List;

public interface MatchService {
    MatchDto addMatch(MatchDto matchDto);

    MatchDto getMatch(Long id);

    List<MatchDto> getAllMatches();

    MatchDto updateMatch(Long id, MatchDto matchDto);

    void deleteMatch(Long id);
}
