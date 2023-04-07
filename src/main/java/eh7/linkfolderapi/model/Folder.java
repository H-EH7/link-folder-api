package eh7.linkfolderapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Folder {
    private Long id;
    private Long memberId;
    private String name;

    // 생성자
    public Folder(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
