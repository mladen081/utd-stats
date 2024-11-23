package u.utd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private Long id;
    private String date;
    private String opponents;
    private String ha;
    private String result;
    private String scorers;
    private String attendance;
    private String position;
}
