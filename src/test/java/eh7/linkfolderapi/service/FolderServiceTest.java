package eh7.linkfolderapi.service;

import eh7.linkfolderapi.AutoAppConfig;
import eh7.linkfolderapi.model.Folder;
import eh7.linkfolderapi.model.Member;
import eh7.linkfolderapi.repository.FolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FolderServiceTest {

    static ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    FolderService folderService = ac.getBean(FolderService.class);
    FolderRepository folderRepository = ac.getBean(FolderRepository.class);

    @BeforeAll
    static void beforeAll() {
        // 폴더를 종속시킬 가상의 회원 가입
        MemberService memberService = ac.getBean(MemberService.class);
        Member memberA = new Member("memberA", "email");
        memberService.join(memberA);
    }

    @AfterEach
    void afterEach() {
        folderRepository.clearStore();
    }

    @Test
    @DisplayName("새 폴더 생성")
    void createFolder() {
        //given
        Folder folder = new Folder(1L, "folderA");

        //when
        folderService.createFolder(folder);
        Folder findFolder = folderService.findFolder(1L);

        //then
        assertThat(findFolder.getName()).isEqualTo("folderA");
    }

    @Test
    @DisplayName("폴더명 변경")
    void editFolderName() {
        //given
        Folder folder = new Folder(1L, "folderA");

        folderService.createFolder(folder);

        //when
        folderService.editFolderName(1L, "newFolderName");
        Folder findFolder = folderService.findFolder(1L);

        //then
        assertThat(findFolder.getName()).isEqualTo("newFolderName");
    }

    @Test
    @DisplayName("폴더 삭제")
    void deleteFolder() {
        //given
        Folder folder1 = new Folder(1L, "folderA");
        Folder folder2 = new Folder(1L, "folderB");
        Folder folder3 = new Folder(1L, "folderC");


        folderService.createFolder(folder1);
        folderService.createFolder(folder2);
        folderService.createFolder(folder3);

        //when
        folderService.deleteFolder(2L);
        Folder findFolder = folderService.findFolder(2L);

        //then
        assertThat(findFolder).isNull();
    }

    @Test
    @DisplayName("폴더 조회")
    void findFolder() {
        //given
        Folder folder = new Folder(1L, "folderA");
        folderService.createFolder(folder);

        //when
        Folder findFolder = folderService.findFolder(1L);

        //then
        assertThat(findFolder.getName()).isEqualTo("folderA");
    }

    @Test
    @DisplayName("회원의 모든 폴더 조회")
    void findFoldersByMemberId() {
        //given
        Folder folder1 = new Folder(1L, "folderA");
        Folder folder2 = new Folder(1L, "folderB");

        folderService.createFolder(folder1);
        folderService.createFolder(folder2);

        //when
        List<Folder> findFolders = folderService.findFoldersByMemberId(1L);
        Folder folderA = folderService.findFolder(1L);
        Folder folderB = folderService.findFolder(2L);

        //then
        assertThat(findFolders).contains(folderA, folderB);
    }
}