package u.utd.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import u.utd.dto.MatchDto;
import u.utd.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("api/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchDto> addMatch(@RequestBody MatchDto matchDto) {
        MatchDto savedMatch = matchService.addMatch(matchDto);
        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") Long id) {
        MatchDto matchDto = matchService.getMatch(id);
        return new ResponseEntity<>(matchDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        List<MatchDto> matchDtos = matchService.getAllMatches();
        return ResponseEntity.ok(matchDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<MatchDto> updateMatch(@RequestBody MatchDto matchDto, @PathVariable Long id) {
        MatchDto updatedMatch = matchService.updateMatch(id, matchDto);
        return new ResponseEntity<>(updatedMatch, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable("id") Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Match deleted successfully!");
    }
}
