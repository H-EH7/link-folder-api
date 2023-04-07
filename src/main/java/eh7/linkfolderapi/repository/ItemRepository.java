package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Item;

import java.util.List;


public interface ItemRepository {
    void save(Item item);

    void delete(Long itemId);

    void moveItemToOtherFolder(Long itemId, Long newFolderId);

    void updateItemName(Long itemId, String newItemName);

    void updateItemPrice(Long itemId, int newItemPrice);

    void updateItemUrl(Long itemId, String newItemUrl);

    Item findById(Long itemId);

    List<Item> findItemsByFolderId(Long folderId);

    // 테스트용
    void clearStore();
}
