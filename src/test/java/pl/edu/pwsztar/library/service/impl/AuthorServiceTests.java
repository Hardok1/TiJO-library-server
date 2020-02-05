package pl.edu.pwsztar.library.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.edu.pwsztar.library.model.Author;
import pl.edu.pwsztar.library.repository.AuthorRepository;
import pl.edu.pwsztar.library.service.AccountService;
import pl.edu.pwsztar.library.service.AuthorService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AuthorServiceTests {

    AuthorRepository authorRepository = new AuthorRepository() {
        final Map<Long, Author> authors = new ConcurrentHashMap<>();
        Long nextId = 0L;

        @Override
        public List<Author> findAll() {
            return null;
        }

        @Override
        public List<Author> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Author> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public <S extends Author> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Author> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Author> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Author getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Author> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Author> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Author> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Author> S save(S s) {
            Long id = s.getId();
            if (id == null) {
                id = nextId++;
                s.setId(id);
            }
            authors.put(id, s);
            return s;
        }

        @Override
        public Optional<Author> findById(Long aLong) {
            for (Map.Entry<Long, Author> entry : authors.entrySet()){
                if (entry.getValue().getId().equals(aLong)){
                    return Optional.ofNullable(entry.getValue());
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Author author) {

        }

        @Override
        public void deleteAll(Iterable<? extends Author> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Author> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Author> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Author> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Author> boolean exists(Example<S> example) {
            return false;
        }
    };

    AccountService accountService = mock(AccountService.class);

    AuthorService authorService = new AuthorServiceImpl(authorRepository, accountService);

    @Test
    public void addAuthorByAdminTest() {
        when(accountService.isAdmin(1L)).thenReturn(true);
        assertEquals("Add author", true, authorService.addAuthor("Jan Kowalski", 1L));
    }

    @Test
    public void addAuthorByUserTest() {
        when(accountService.isAdmin(1L)).thenReturn(false);
        assertEquals("Fail to add author", false, authorService.addAuthor("Jan Kowalski", 1L));
    }

    @Test
    public void getAuthorNameTest(){
        Author author = new Author();
        author.setAuthorName("Jan Kowalski");
        author.setId(3L);
        authorRepository.save(author);
        assertEquals("Get author name", "Jan Kowalski", authorService.getAuthorName(3L));
    }


}
