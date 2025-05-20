package org.example.item;

import java.util.List;

public interface ItemRepository {
    List<Item> findByUserId(Long userId);
    Item save(Item item);
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}
