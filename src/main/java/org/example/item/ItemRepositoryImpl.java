package org.example.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public List<Item> findByUserId(Long userId) {
        return items.values().stream()
                .filter(item -> Objects.equals(item.getUserId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public Item save(Item item) {
        log.info("Запрос на добавление item:{}", item);
        item.setId(getNextId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public void deleteByUserIdAndItemId(Long userId, Long itemId) {
        items.values().removeIf(item ->
                Objects.equals(item.getUserId(), userId) &&
                Objects.equals(item.getId(), itemId));
    }

    private Long getNextId() {
        int currentMaxId = (int) items.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        currentMaxId++;
        return (long) currentMaxId;
    }
}
