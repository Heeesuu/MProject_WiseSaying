package org.example.wiseSaying.controller;

import org.example.Container;
import org.example.Rq;
import org.example.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {

    private static long lastWiseSayingId; //모든 항목에서 사용할 명언 번호
    private static List<WiseSaying> wiseSayings; //목록항목에서 사용할 리스트


    public WiseSayingController(){ //기본템
        lastWiseSayingId = 0;
        wiseSayings = new ArrayList<>();

    }
    public void write() {
        long id = lastWiseSayingId + 1; //등록하면 무조건 1추가
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.print("작가 : ");
        String authorName = Container.getScanner().nextLine().trim();
        //위에서 적은 항목을 저장해야함 > 목록 수정 삭제에서 사용해야하기때문에
        //저장을 할 클래스를 생성 > 그 클래스안에는 list가 있어야 저장하기 편함

        WiseSaying wiseSaying = new WiseSaying(id, content, authorName);
        //위에서 만든 변수를 WiseSaying 클래스로 옮긴다.
        //옮기면 WiseSaying 클래스에서 각각 항목들을 저장할 수 있도록

        wiseSayings.add(wiseSaying);
        //위에서 저장한 항목들을 다시 받아와서 wiseSayings라는 리스트에 저장한다.

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
        lastWiseSayingId = id;
        // 다음에 또 등록하면 id가 하나 더 늘어나야하기 때문에
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("-".repeat(30));

        for (int i = 0; i < wiseSayings.size(); i++) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            //i의 숫자가 순서를 의미할듯 ? ??? .. ?

            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthorName(), wiseSaying.getContent());
        }
    }
    private WiseSaying findById(int id) { // findById 메소드는 id를 매개변수로 전달받고, wiseSayings 리스트에서
        //해당 WiseSayings 객체를 wiseSaying에 반환한다. wiseSaying은 여기서 만든 객체
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.getId() == id) {
                return wiseSaying;
                //입력한 id값이 getId와 같은값을 찾아 wiseSaying을 반환한다.
            }
        }
        return null; //왜 리턴 눌이 필요한지 모르겠다.
    }
    public void remove(Rq rq) {  //연결할때는 Rq 클래스네임 rq 객체네임
        int id = rq.getIntParam("id", -1);

        if (id == -1) { //rq에서 예외가 발생했을때 무조건 -1값을 반환하도록함
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        wiseSayings.remove(wiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }



    public void modify(Rq rq) {
        int id = rq.getIntParam("id", -1);

        if (id == -1) {
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언 (기존) : %s\n",wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.printf("작가 (기존) : %s\n", wiseSaying.getAuthorName());
        System.out.print("작가 : ");
        String authorName = Container.getScanner().nextLine().trim();

        wiseSaying.setContent(content);
        wiseSaying.setAuthorName(authorName);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }
}
