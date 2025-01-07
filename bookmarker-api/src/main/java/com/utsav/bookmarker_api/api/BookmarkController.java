package com.utsav.bookmarker_api.api;

import com.utsav.bookmarker_api.domain.Bookmark;
import com.utsav.bookmarker_api.domain.BookmarkService;
import com.utsav.bookmarker_api.domain.BookmarksDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return bookmarkService.getBookmarks(page);
    }
}
