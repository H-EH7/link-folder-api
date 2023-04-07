package eh7.linkfolderapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private Long folderId;
    private String name;
    private Integer price;
    private String url;

    // 생성자
    public Item(Long folderId, String name, Integer price, String url) {
        this.folderId = folderId;
        this.name = name;
        this.price = price;
        this.url = url;
    }
}
