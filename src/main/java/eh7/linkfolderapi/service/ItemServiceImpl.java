package eh7.linkfolderapi.service;

import eh7.linkfolderapi.model.Item;
import eh7.linkfolderapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void moveItemToOtherFolder(Long itemId, Long newFolderId) {
        itemRepository.moveItemToOtherFolder(itemId, newFolderId);
    }

    @Override
    public void editItemName(Long itemId, String newItemName) {
        itemRepository.updateItemName(itemId, newItemName);
    }

    @Override
    public void editItemPrice(Long itemId, Integer newItemPrice) {
        itemRepository.updateItemPrice(itemId, newItemPrice);
    }

    @Override
    public void editItemUrl(Long itemId, String newItemUrl) {
        itemRepository.updateItemUrl(itemId, newItemUrl);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.delete(itemId);
    }

    @Override
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> findItemsByFolderId(Long folderId) {
        return itemRepository.findItemsByFolderId(folderId);
    }
}
