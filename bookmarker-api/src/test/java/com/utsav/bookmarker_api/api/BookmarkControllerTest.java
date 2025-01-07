package com.utsav.bookmarker_api.api;

import com.utsav.bookmarker_api.domain.Bookmark;
import com.utsav.bookmarker_api.domain.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:17-alpine:///bookmarks"
})
class BookmarkControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();

        bookmarks.add(new Bookmark("SivaLabs", "https://sivalabs.in", Instant.now()));
        bookmarks.add(new Bookmark("SpringBlog", "https://spring.io/blog", Instant.now()));
        bookmarks.add(new Bookmark("Quarkus", "https://quarkus.io/", Instant.now()));
        bookmarks.add(new Bookmark("Micronaut", "https://micronaut.io/", Instant.now()));
        bookmarks.add(new Bookmark("JOOQ", "https://www.jooq.org/", Instant.now()));
        bookmarks.add(new Bookmark("VladMihalcea", "https://vladmihalcea.com", Instant.now()));
        bookmarks.add(new Bookmark("Thoughts On Java", "https://thorben-janssen.com/", Instant.now()));
        bookmarks.add(new Bookmark("DZone", "https://dzone.com/", Instant.now()));
        bookmarks.add(new Bookmark("DevOpsBookmarks", "http://www.devopsbookmarks.com/", Instant.now()));
        bookmarks.add(new Bookmark("Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()));
        bookmarks.add(new Bookmark("Expressjs", "https://expressjs.com/", Instant.now()));
        bookmarks.add(new Bookmark("Marcobehler", "https://www.marcobehler.com", Instant.now()));
        bookmarks.add(new Bookmark("baeldung", "https://www.baeldung.com", Instant.now()));
        bookmarks.add(new Bookmark("devglan", "https://www.devglan.com", Instant.now()));
        bookmarks.add(new Bookmark("linuxize", "https://linuxize.com", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1,15,2,1,true,false,true,false",
            "2,15,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
                            boolean isFirst, boolean isLast,
                            boolean hasNext, boolean hasPrevious) throws Exception {
        mvc.perform(get("/api/bookmarks?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
        ;
    }
}