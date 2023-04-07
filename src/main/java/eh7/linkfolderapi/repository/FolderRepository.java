package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Folder;

import java.util.List;

public interface FolderRepository {
    void save(Folder folder);

    void updateFolderName(Long folderId, String newFolderName);

    void delete(Long folderId);

    Folder findById(Long folderId);

    List<Folder> findFoldersByMemberId(Long memberId);

    // 테스트용
    void clearStore();
}
