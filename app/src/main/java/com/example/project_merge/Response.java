package com.example.project_merge;

import java.util.List;

public class Response {
    private List<Person> body;
    private Header header;

    // Getter 메서드는 필요에 따라 추가
    public List<Person> getBody() {
        return body;
    }

    public Header getHeader() {
        return header;
    }
}

