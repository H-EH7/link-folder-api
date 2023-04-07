package eh7.linkfolderapi.service;

import eh7.linkfolderapi.model.Item;

import java.util.List;

public interface ItemService {
    void addItem(Item item);

    void moveItemToOtherFolder(Long itemId, Long newFolderId);

    void editItemName(Long itemId, String newItemName);

    void editItemPrice(Long itemId, Integer newItemPrice);

    void editItemUrl(Long itemId, String newItemUrl);

    void deleteItem(Long itemId);

    Item findItem(Long itemId);

    List<Item> findItemsByFolderId(Long folderId);
}
