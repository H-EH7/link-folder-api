package eh7.linkfolderapi.service;

import eh7.linkfolderapi.AutoAppConfig;
import eh7.linkfolderapi.model.Folder;
import eh7.linkfolderapi.model.Item;
import eh7.linkfolderapi.model.Member;
import eh7.linkfolderapi.repository.ItemRepository;
import eh7.linkfolderapi.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    static ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    ItemService itemService = ac.getBean(ItemService.class);
    ItemRepository itemRepository = ac.getBean(ItemRepository.class);

    @BeforeAll
    static void beforeAll() {
        MemberService memberService = ac.getBean(MemberService.class);
        FolderService folderService = ac.getBean(FolderService.class);

        // 가상의 회원 가입
        Member member = new Member("memberA", "email");
        memberService.join(member);

        // 가상의 회원에 종속된 폴더 2개 생성
        Folder folder1 = new Folder(1L, "folderA");
        Folder folder2 = new Folder(1L, "folderB");
        folderService.createFolder(folder1);
        folderService.createFolder(folder2);
    }

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("아이템 추가")
    void addItem() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");

        //when
        itemService.addItem(item);
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    @DisplayName("아이템을 다른 폴더로 옮기기")
    void moveItemToOtherFolder() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");
        itemService.addItem(item);

        //when
        itemService.moveItemToOtherFolder(1L, 2L);
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem.getFolderId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("아이템 이름 수정")
    void editItemName() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");
        itemService.addItem(item);

        //when
        itemService.editItemName(1L, "newItemName");
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem.getName()).isEqualTo("newItemName");
    }

    @Test
    @DisplayName("아이템 가격 수정")
    void editItemPrice() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");
        itemService.addItem(item);

        //when
        itemService.editItemPrice(1L, 99999);
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem.getPrice()).isEqualTo(99999);
    }

    @Test
    @DisplayName("아이템 URL 수정")
    void editItemUrl() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");
        itemService.addItem(item);

        //when
        itemService.editItemUrl(1L, "newUrl");
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem.getUrl()).isEqualTo("newUrl");
    }

    @Test
    @DisplayName("아이템 삭제")
    void deleteItem() {
        //given
        Item item1 = new Item(1L, "itemA", 10000, "url");
        Item item2 = new Item(1L, "itemB", 20000, "url");
        Item item3 = new Item(1L, "itemC", 30000, "url");
        itemService.addItem(item1);
        itemService.addItem(item2);
        itemService.addItem(item3);

        //when
        itemService.deleteItem(2L);
        Item findItem = itemService.findItem(2L);

        //then
        assertThat(findItem).isNull();
    }

    @Test
    @DisplayName("아이템 조회")
    void findItem() {
        //given
        Item item = new Item(1L, "itemA", 10000, "url");
        itemService.addItem(item);

        //when
        Item findItem = itemService.findItem(1L);

        //then
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    @DisplayName("폴더 내 아이템 모두 조회")
    void findItemsByFolderId() {
        //given
        Item item1 = new Item(1L, "itemA", 10000, "url");
        Item item2 = new Item(1L, "itemB", 20000, "url");
        Item item3 = new Item(1L, "itemC", 30000, "url");
        itemService.addItem(item1);
        itemService.addItem(item2);
        itemService.addItem(item3);

        //when
        List<Item> findItems = itemService.findItemsByFolderId(1L);

        //then
        assertThat(findItems).contains(item1, item2, item3);
    }
}