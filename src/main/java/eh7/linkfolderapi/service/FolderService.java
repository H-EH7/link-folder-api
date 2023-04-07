package eh7.linkfolderapi.service;

import eh7.linkfolderapi.model.Folder;

import java.util.List;

public interface FolderService {
    void createFolder(Folder folder);

    void editFolderName(Long folderId, String newFolderName);

    void deleteFolder(Long folderId);

    Folder findFolder(Long folderId);

    List<Folder> findFoldersByMemberId(Long memberId);
}
