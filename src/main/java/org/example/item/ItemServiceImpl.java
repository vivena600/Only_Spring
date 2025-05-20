package org.example.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getItems(Long userId) {
        return itemRepository.findByUserId(userId);
    }

    @Override
    public Item addNewItem(Long userId, Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long userId, Long itemId) {
        itemRepository.deleteByUserIdAndItemId(userId, itemId);
    }
}
