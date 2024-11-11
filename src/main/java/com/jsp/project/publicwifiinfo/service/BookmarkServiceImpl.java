package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Bookmark;
import com.jsp.project.publicwifiinfo.repository.BookmarkRepository;

import java.util.List;

public class BookmarkServiceImpl implements BookmarkService {
    BookmarkRepository repository = new BookmarkRepository();

    @Override
    public List<Bookmark> getBookmarkList() {
        return null;
    }

    @Override
    public Bookmark getBookmark(Bookmark bookmark) {
        return null;
    }

    @Override
    public int saveBookmark(Bookmark bookmark) {
        String groupName = bookmark.getWifiMngrNo();
        int groupCode = bookmark.getGroupCode();

        // validation check
        if(groupName == null || groupName.trim().equals("")) {
            return -5;
        }

        if(groupCode <= 0) {
            return -5;
        }

        // 동일 북마크 중복 저장 불가
        int duplCnt = repository.selectDuplBookmarkCnt(bookmark);
        if(duplCnt > 0) {
            return -10;
        }

        return repository.insertBookmark(bookmark);
    }

    @Override
    public int deleteBookmark(Bookmark bookmark) {
        return 0;
    }
}
