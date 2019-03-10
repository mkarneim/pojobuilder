package net.karneim.pojobuilder;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Little bit of everything, for use with <a href="">JMH</a> benchmarks.
 */
public class Book {

    public final String title;
    public final String author;
    public final int words;
    public final int pages;
    public final Optional<String> publisher;

    private Book(String title, String author, int words, int pages, Optional<String> publisher) {
        this.title = title;
        this.author = author;
        this.words = words;
        this.pages = pages;
        this.publisher = publisher;
    }

    // Define builders of varying complexities

    @GeneratePojoBuilder(
    withCopyMethod = true,
    withBuilderInterface = Supplier.class
    )
    public @interface Builder {
    }

    @Builder
    public static Book book1(String title, String author, int words, int pages, Optional<String> publisher) {
        return new Book(title, author, words, pages, publisher);
    }

    @Builder
    @GeneratePojoBuilder(
    withOptionalProperties = Optional.class,
    withName = "*OptionalBuilder"
    )
    public static Book book2(String title, String author, int words, int pages, Optional<String> publisher) {
        return new Book(title, author, words, pages, publisher);
    }

    @Builder
    @GeneratePojoBuilder(
    withBuilderProperties = true,
    withName = "*PropBuilder"
    )
    public static Book book3(String title, String author, int words, int pages, Optional<String> publisher) {
        return new Book(title, author, words, pages, publisher);
    }

    @Builder
    @GeneratePojoBuilder(
    withOptionalProperties = Optional.class,
    withBuilderProperties = true,
    withName = "*OptionalPropBuilder"
    )
    public static Book book4(String title, String author, int words, int pages, Optional<String> publisher) {
        return new Book(title, author, words, pages, publisher);
    }

}
