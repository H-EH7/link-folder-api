package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
    }

    @Override
    public void delete(Long itemId) {
        store.remove(itemId);
    }

    @Override
    public void moveItemToOtherFolder(Long itemId, Long newFolderId) {
        Item item = store.get(itemId);
        item.setFolderId(newFolderId);
    }

    @Override
    public void updateItemName(Long itemId, String newItemName) {
        Item item = store.get(itemId);
        item.setName(newItemName);
    }

    @Override
    public void updateItemPrice(Long itemId, int newItemPrice) {
        Item item = store.get(itemId);
        item.setPrice(newItemPrice);
    }

    @Override
    public void updateItemUrl(Long itemId, String newItemUrl) {
        Item item = store.get(itemId);
        item.setUrl(newItemUrl);
    }

    @Override
    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    @Override
    public List<Item> findItemsByFolderId(Long folderId) {
        List<Item> items = new ArrayList<>();
        for (Item item : store.values()) {
            if (item.getFolderId() == folderId) {
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public void clearStore() {
        sequence = 0L;
        store.clear();
    }
}
