package banquemisr.challenge05.Task.Management.System.Dto.Page;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int currentPage;

}
