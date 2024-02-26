package at.example.backenddemo.service;


import at.example.backenddemo.model.db.Item;
import at.example.backenddemo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item loadItem(Long id) {
        return this.itemRepository.findById(id).orElse(null);
    }
}
