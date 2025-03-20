package com.example.spring15.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;
// 응답에서 원하는 데이터만 뽑기위한 객체
// 타고 타고 안으로 들어가는 듯...
public class GeminiResponse {
    @SerializedName("candidates")
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public static class Candidate {
        @SerializedName("content")
        private Content content;

        public Content getContent() {
            return content;
        }
    }

    public static class Content {
        @SerializedName("parts")
        private List<Part> parts;

        public List<Part> getParts() {
            return parts;
        }
    }

    public static class Part {
        @SerializedName("text")
        private String text;

        public String getText() {
            return text;
        }
    }
}