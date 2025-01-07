package com.utsav.bookmarker_api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookmarkService {
    private final BookmarkRepository repository;

    public BookmarkService(BookmarkRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarks(Integer page) {
        int pageNo = page < 1 ? 0 : page -1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = repository.findBy(pageable);
        return new BookmarksDTO(bookmarkPage);
    }
}

