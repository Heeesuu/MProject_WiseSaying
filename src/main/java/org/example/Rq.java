package org.example;

import java.util.HashMap;
import java.util.Map;

public class Rq {

    private String actionCode;

    private Map<String, String> params;


    public Rq(String command) {
        String[] commandBits = command.split("\\?", 2);
        actionCode = commandBits[0];
        //이렇게 나누게 되면 command에 "등록" 이라는 값이 들어갔을때는 commandBits[0]은 "등록"
        //"삭제?id=2"라는 값이 들어가면 commandBits[0]은 "삭제" commandBits[1]에는"id=2"값이 들어간다.

        params = new HashMap<>();
        //삭제나 수정 값인 commandBits[0]값은 해시맵에 저장할 필요가 없기 때문에
        //위에서 액션코드로 나눠준후 해시맵 생성
        if (commandBits.length == 1) return;
        //등록이나 목록일땐 length ==1 이기 때문에 스플릿할 필요없이 return
        String[] paramBits = commandBits[1].split("&");
        //String[] 배열을 하나 더 만들어서 & 기준으로 나눈다. 요구사항엔 없었지만 여러조건을 저 문자로 엮어 한번에 명령하는 경우를 대비한 코드

        for (String paramStr : paramBits) {
            String[] paramStrBits = paramStr.split("=", 2);
            //paramStrBits 라는 배열을 또 만들어서 id=1 이라는 값을 = 기준으로 나누는역할을 한다.

            if (paramStrBits.length == 1) continue; //정상 입력했으면 크기가 1일수가 없다 이 경우는 컨티뉴해서 끝낸다.

            String key = paramStrBits[0]; //key에는 id를 저장하고
            String value = paramStrBits[1]; //value에는 1을 저장한다 예시 id =1 기준

            params.put(key, value);
            //params라는 해시맵에는 key = id , value = 1 이렇게 값이 들어갈까 ?
            //id가 1이라는 항목에 key = id, value = 1이라는 값이 들어간다.
        }

    }

    public String getActionCode() {
        return actionCode;
    }


    public int getIntParam(String name, int defaultValue) {//예외처리를 위한 메소드
        try {
            return Integer.parseInt(getParam(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String getParam(String name) {
        return params.get(name);
    }
}
