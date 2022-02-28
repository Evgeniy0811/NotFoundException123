package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductRepositoryTest {
    private final ProductRepository repository = new ProductRepository();
    private final Product book1 = new Book(1, "Сказки", 100, "Иванов");
    private final Product book2 = new Book(2, "Ужасы", 150, "Пупкин");
    private final Product phone1 = new Smartphone(11, "Pro", 5000, "Honor");
    private final Product phone2 = new Smartphone(22, "Classic ", 7000, "Nokia");

    @Test
    void shouldThrowAnException() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(44));
        Product[] expected = new Product[]{book1, book2, phone1, phone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDOneProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1, phone2};
        repository.removeById(1);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDTwoProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1,};
        repository.removeById(1);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldStayEmpty() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{};
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(11);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddAllProducts() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book1, book2, phone1, phone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSaveAnything() {
        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}