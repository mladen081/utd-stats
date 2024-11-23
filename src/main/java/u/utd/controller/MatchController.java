package u.utd.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import u.utd.dto.MatchDto;
import u.utd.service.MatchService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/matches")
@AllArgsConstructor
public class MatchController {

    private MatchService matchService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MatchDto> addMatch(@RequestBody MatchDto matchDto) {
        MatchDto savedMatch = matchService.addMatch(matchDto);
        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") Long id) {
        MatchDto matchDto = matchService.getMatch(id);
        return new ResponseEntity<>(matchDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        List<MatchDto> matchDtos = matchService.getAllMatches();
        return ResponseEntity.ok(matchDtos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<MatchDto> updateMatch(@RequestBody MatchDto matchDto, @PathVariable Long id) {
        MatchDto updatedMatch = matchService.updateMatch(id, matchDto);
        return new ResponseEntity<>(updatedMatch, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable("id") Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Match deleted successfully!");
    }
}
