package u.utd.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import u.utd.dto.MatchDto;
import u.utd.entity.Match;
import u.utd.exception.ResourceNotFoundException;
import u.utd.repository.MatchRepository;
import u.utd.service.MatchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private ModelMapper modelMapper;

    @Override
    public MatchDto addMatch(MatchDto matchDto) {

        System.out.println("MatchDto Attendance: " + matchDto.getAttendance());

        Match match = modelMapper.map(matchDto, Match.class);

        Match savedMatch =  matchRepository.save(match);

        System.out.println("Match Attendance: " + match.getAttendance());

        return modelMapper.map(savedMatch, MatchDto.class);
    }

    @Override
    public MatchDto getMatch(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id:" + id));

        return modelMapper.map(match, MatchDto.class);
    }

    @Override
    public List<MatchDto> getAllMatches() {
        List<Match> matches = matchRepository.findAll();

        return matches.stream()
                .map(match -> modelMapper.map(match, MatchDto.class)).collect(Collectors.toList());
    }

    @Override
    public MatchDto updateMatch(Long id, MatchDto matchDto) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id:" + id));

        match.setDate(matchDto.getDate());
        match.setOpponents(matchDto.getOpponents());
        match.setHa(matchDto.getHa());
        match.setResult(matchDto.getResult());
        match.setScorers(matchDto.getScorers());
        match.setAttendance(matchDto.getAttendance());
        match.setPosition(matchDto.getPosition());

        Match updatedMatch = matchRepository.save(match);

        return modelMapper.map(updatedMatch, MatchDto.class);
    }

    @Override
    public void deleteMatch(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id:" + id));

        matchRepository.deleteById(match.getId());
    }
}
