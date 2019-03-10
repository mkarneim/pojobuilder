package net.karneim.pojobuilder;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Warmup(time = 10, timeUnit = TimeUnit.SECONDS, batchSize = 100_000)
@Measurement(time = 10, timeUnit = TimeUnit.SECONDS, iterations = 1, batchSize = 100_000)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class PojobuilderPerformance {

    private BookBuilder builderTemplate;
    private Book pojoTemplate;

    private String title;
    private String author;
    private int words;
    private int pages;
    private String publisher;

    private Optional<String> titleO;
    private Optional<String> authorO;
    private Optional<Integer> wordsO;
    private Optional<Integer> pagesO;
    private Optional<String> publisherO;

    private Supplier<String> titleS;
    private Supplier<String> authorS;
    private Supplier<Integer> wordsS;
    private Supplier<Integer> pagesS;
    private Supplier<String> publisherS;

    @Setup
    // Always initialise test material under @Setup
    public void createTemplates() {
        title = "A Fire Upon The Deep";
        author = "Vernor Vinge";
        pages = 613;
        words = 50_000;
        publisher = "Tor";

        titleO = Optional.of(title);
        authorO = Optional.of(author);
        pagesO = Optional.of(pages);
        wordsO = Optional.of(words);
        publisherO = Optional.of(publisher);

        titleS = () -> title;
        authorS = () -> author;
        wordsS = () -> words;
        pagesS = () -> pages;
        publisherS = () -> publisher;

        builderTemplate = new BookBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisherO);

        pojoTemplate = builderTemplate.get();
    }

    /**
     * Baseline, nothing should be faster than this!
     */
    @Benchmark
    public Book constructManually() {
        return Book.book1(title, author, words, pages, publisherO);
    }

    /**
     * PojoBuilder used within one compilation scope. Counter to many JMH docs,
     * this is actually a common scenario for PB.
     */
    @Benchmark
    public Book constructViaBuilder_private() {
        return new BookBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisherO)
            .get();
    }

    /**
     * PojoBuilder being passed between methods.
     */
    @Benchmark
    public Book constructViaBuilder_shared(Blackhole bh) {
        BookBuilder builder = new BookBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisherO);
        bh.consume(builder); // This stops the argument being shortcut by the JIT
        return builder.get();
    }

    @State(Scope.Benchmark)
    public static class LocalBuilder {
        ThreadLocal<BookBuilder> localBuilder = ThreadLocal.withInitial(BookBuilder::new);
    }

    /**
     * PojoBuilder instance being held in a ThreadLocal. Be *very* certain no fields can bleed from one call to another if doing this!
     */
    @Benchmark
    public Book constructViaBuilder_threadlocal(LocalBuilder state) {
        return state.localBuilder.get()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisherO)
            .get();
    }

    /**
     * PojoBuilder copy-from-existing-then-amend being passed between methods.
     */
    @Benchmark
    public Book constructViaCopyMethod_shared(Blackhole bh) {
        BookBuilder builder = new BookBuilder().copy(pojoTemplate);
        bh.consume(builder); // This stops the argument being shortcut by the JIT
        return builder.get();
    }

    /**
     * PojoBuilder copy-from-existing-then-amend used within one compilation scope.
     */
    @Benchmark
    public Book constructViaCopyMethod_private() {
        return new BookBuilder().copy(pojoTemplate).get();
    }

    /**
     * PojoBuilder clone-builder-then-amend being passed between methods.
     */
    @Benchmark
    public Book constructViaButMethod_shared(Blackhole bh) {
        BookBuilder builder = builderTemplate.but();
        bh.consume(builder); // This stops the argument being shortcut by the JIT
        return builder.get();
    }

    /**
     * PojoBuilder clone-builder-then-amend used within one compilation scope.
     */
    @Benchmark
    public Book constructViaButMethod_private() {
        return builderTemplate.but().get();
    }

    @Benchmark
    public Book constructViaOptionalBuilder_withRealProperties() {
        return new BookOptionalBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisher)
            .get();
    }

    @Benchmark
    public Book constructViaOptionalBuilder_withOptionalProperties() {
        return new BookOptionalBuilder()
            .withAuthor(authorO)
            .withTitle(titleO)
            .withPages(pagesO)
            .withWords(wordsO)
            .withPublisher(publisherO)
            .get();
    }

    @Benchmark
    public Book constructViaSupplierBuilder_withSuppliedProperties() {
        return new BookPropBuilder()
            .withAuthor(authorS)
            .withTitle(titleS)
            .withPages(pagesS)
            .withWords(wordsS)
            .withPublisher(() -> publisherO)
            .get();
    }

    @Benchmark
    public Book constructViaSupplierBuilder_withRealProperties() {
        return new BookPropBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisherO)
            .get();
    }

    @Benchmark
    public Book constructViaOptionalSupplierBuilder_withRealProperties() {
        return new BookOptionalPropBuilder()
            .withAuthor(author)
            .withTitle(title)
            .withPages(pages)
            .withWords(words)
            .withPublisher(publisher)
            .get();
    }

    @Benchmark
    public Book constructViaOptionalSupplierBuilder_withOptionalProperties() {
        return new BookOptionalPropBuilder()
            .withAuthor(authorO)
            .withTitle(titleO)
            .withPages(pagesO)
            .withWords(wordsO)
            .withPublisher(publisherO)
            .get();
    }

    @Benchmark
    public Book constructViaOptionalSupplierBuilder_withSuppliedProperties() {
        return new BookOptionalPropBuilder()
            .withAuthor(authorS)
            .withTitle(titleS)
            .withPages(pagesS)
            .withWords(wordsS)
            .withPublisher(publisherS)
            .get();
    }

}
