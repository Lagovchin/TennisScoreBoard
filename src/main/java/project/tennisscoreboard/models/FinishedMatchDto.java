package project.tennisscoreboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.tennisscoreboard.entity.Match;

import java.util.List;

@Data
@AllArgsConstructor
public class FinishedMatchDto {

    private List<Match> matches;
    private int currentPage;
    private int pageSize;
    private Long matchesCount;
    private int pagesCount;

    public boolean hasNext() {
        return currentPage < pagesCount;
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }
}
