package eh7.linkfolderapi.service;

import eh7.linkfolderapi.model.Folder;
import eh7.linkfolderapi.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public void createFolder(Folder folder) {
        folderRepository.save(folder);
    }

    @Override
    public void editFolderName(Long folderId, String newFolderName) {
        folderRepository.updateFolderName(folderId, newFolderName);
    }

    @Override
    public void deleteFolder(Long folderId) {
        folderRepository.delete(folderId);
    }

    @Override
    public Folder findFolder(Long folderId) {
        return folderRepository.findById(folderId);
    }

    @Override
    public List<Folder> findFoldersByMemberId(Long memberId) {
        return folderRepository.findFoldersByMemberId(memberId);
    }
}
