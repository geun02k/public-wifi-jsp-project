package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.BookmarkGroup;
import com.jsp.project.publicwifiinfo.repository.BookmarkGroupRepository;

import java.util.List;

public class BookmarkGroupServiceImpl implements BookmarkGroupService {
    BookmarkGroupRepository groupRepository = new BookmarkGroupRepository();

    @Override
    public List<BookmarkGroup> getGroupList() {
        return groupRepository.selectBookmarkGroupList();
    }

    @Override
    public BookmarkGroup getGroup(BookmarkGroup group) {
        return groupRepository.selectBookmarkGroupByCode(group);
    }

    @Override
    public int saveGroup(BookmarkGroup group) {
        String groupName = group.getName();
        int groupSeq = group.getSeq();

        // validation check
        if(groupName == null || groupName.trim().equals("")) {
            return -1;
        }

        if(groupSeq <= 0) {
            return -1;
        }

        //순서중복제거
        int duplCnt = groupRepository.selectBookmarkGroupCntBySeq(groupSeq);
        if(duplCnt > 0) {
            return -1;
        }

        return groupRepository.insertBookmarkGroup(group);
    }

    @Override
    public int updateGroup(BookmarkGroup group) {
        String groupName = group.getName();
        int groupSeq = group.getSeq();
        int groupCode = group.getCode();

        // validation check
        if(groupName == null || groupName.trim().equals("")) {
            return -5;
        }

        if(groupSeq <= 0) {
            return -5;
        }

        if(groupCode <= 0) {
            return -5;
        }

        //순서중복제거
        int duplCnt = groupRepository.selectBookmarkGroupCntBySeq(groupSeq);
        if(duplCnt > 0) {
            return -10;
        }

        return groupRepository.updateBookmarkGroup(group);
    }

    @Override
    public int deleteGroup(BookmarkGroup group) {
        return 0;
    }
}
