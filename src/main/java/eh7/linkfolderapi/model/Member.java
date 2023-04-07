package eh7.linkfolderapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String name;
    private String email;

    // 생성자
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
