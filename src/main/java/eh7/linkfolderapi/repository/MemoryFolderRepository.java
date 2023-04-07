package eh7.linkfolderapi.repository;

import eh7.linkfolderapi.model.Folder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryFolderRepository implements FolderRepository{

    private static Map<Long, Folder> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Folder folder) {
        folder.setId(++sequence);
        store.put(folder.getId(), folder);
    }

    @Override
    public void updateFolderName(Long folderId, String newFolderName) {
        Folder folder = store.get(folderId);
        folder.setName(newFolderName);
    }

    @Override
    public void delete(Long folderId) {
        store.remove(folderId);
    }

    @Override
    public Folder findById(Long folderId) {
        return store.get(folderId);
    }

    @Override
    public List<Folder> findFoldersByMemberId(Long memberId) {
        List<Folder> folders = new ArrayList<>();
        for (Folder folder : store.values()) {
            if (folder.getMemberId() == memberId) {
                folders.add(folder);
            }
        }
        return folders;
    }

    @Override
    public void clearStore() {
        store.clear();
        sequence = 0L;
    }
}
