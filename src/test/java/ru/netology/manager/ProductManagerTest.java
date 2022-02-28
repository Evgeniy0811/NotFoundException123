package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private final ProductRepository repository = new ProductRepository();
    private final ProductManager manager = new ProductManager(repository);
    private final Product book1 = new Book(1, "Сказки", 100, "Иванов");
    private final Product book2 = new Book(2, "Ужасы", 150, "Пупкин");
    private final Product book3 = new Book(3, "Ужасы", 160, "Пупкина");
    private final Product book4 = new Book(4, "Фантастика", 200, "Сидоров");
    private final Product phone1 = new Smartphone(11, "Pro", 5000, "Honor");
    private final Product phone2 = new Smartphone(22, "Classic ", 7000, "Nokia");

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(phone1);
        manager.add(phone2);
    }

    @Test
    void shouldGetAll() {
        Product[] expected = new Product[]{book1, book2, book3, book4, phone1, phone2};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByTitle() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("Сказки");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldToFindSomeBooksByTitle() {
        Product[] expected = new Product[]{book2, book3};
        Product[] actual = manager.searchBy("Ужасы");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByTitle() {
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy("Classic");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFind() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Поэзия");
        assertArrayEquals(expected, actual);
    }
}