package org.example;

import org.example.system.controller.SystemController;
import org.example.wiseSaying.controller.WiseSayingController;

public class App {
    public void run() {
        //Container는 스태틱이였는데 왜 run은 퍼블릭이지?

        //여기에서 클래스 메서드를 사용하고 싶으면 클래스 객체를 생성해줘야함
        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController();


        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령 ) ");
            String command = Container.getScanner().nextLine().trim();
            Rq rq = new Rq(command); //String command를 갖는 생성자를 Rq에 만든다

            switch (rq.getActionCode()) { //첫번째 들어오는 값은 명령어 getActionCode는 첫번째 단어만 인식하게 해주는것
                case "종료" :
                    SystemController.exit();
                    //왜 또 static이지 ?
                    return; //return을 해줘야 아래 케이스들을 실행하지 않고 아예 함수가 끝난다.

                case "등록" :
                    wiseSayingController.write();
                    break;
                    // 등록후에 함수가 종료되는 것이 아니기 때문에 break를 해준다
                case "목록" :
                    wiseSayingController.list();
                    break;

                case "삭제" :
                    wiseSayingController.remove(rq);
                    //항목 split이 필요하기 때문에 rq를 연결시켜준다.

                case "수정" :
                    wiseSayingController.modify(rq);

            }
        }


    }
}
