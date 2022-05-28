package online_store.component;

import lombok.NoArgsConstructor;
import online_store.domain.Product;
import online_store.domain.nonpersist.ShoppingCartEntry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@NoArgsConstructor
public class ShoppingCart {

    private final Map<Long, ShoppingCartEntry> entries = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void addProduct(Product product) {
        try {
            readWriteLock.writeLock().lock();
            entries.put(product.getId(),
                    entries.getOrDefault(product.getId(), new ShoppingCartEntry()).addProduct(product)
            );
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void removeProduct(Product product) {
        try {
            readWriteLock.writeLock().lock();
            ShoppingCartEntry shoppingCartEntry = entries.get(product.getId());
            if (shoppingCartEntry.decreaseAndIsOver()) {
                entries.remove(product.getId());
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int getCount() {
        return entries.size();
    }

    public Set<ShoppingCartEntry> getEntries() {
        try {
            readWriteLock.readLock().lock();
            Set<ShoppingCartEntry> newCart = new HashSet<>();
            entries.forEach((k,v) -> newCart.add(new ShoppingCartEntry(v)));
            return newCart;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
