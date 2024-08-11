package com.example.temp.tour.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class FindTourInformationByLocationHttpResponse {
    private Response response;

    public int getNumOfRows() {
        return response.body.numOfRows;
    }

    public int getTotalCount() {
        return response.body.totalCount;
    }

    public int getPageNo() {
        return response.body.pageNo;
    }

    @Getter
    public static class Response {
        private Header header;
        private Body body;

        @Getter
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Body {
            private int numOfRows;
            private int pageNo;
            private int totalCount;
            @JsonDeserialize(using = ItemsDeserializer.class)
            private Items items = new Items();

            private static class ItemsDeserializer extends JsonDeserializer<Items> {
                @Override
                public Items deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                    JsonNode node = p.getCodec().readTree(p);

                    if (node.isTextual() && node.asText().isEmpty()) {
                        return new Items(); // Return an empty Items object
                    }

                    // Otherwise, parse normally
                    return p.getCodec().treeToValue(node, Items.class);
                }
            }

            @Getter
            public static class Items {
                private final List<Item> item = new ArrayList<>();

                @Getter
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Item {
                    private int totalCnt;

                    @JsonProperty("contenttypeid")
                    private String contentTypeId;
                    private String areacode;
                    private String sigungucode;

                    private String cat1; // 대분류
                    private String cat2; // 중분류
                    private String cat3; // 소분류
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    private String addr1;   // 주소 전체
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    private String addr2; // 상세주소

                    @JsonProperty("firstimage")
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    private String representativeImageUrl;  // 대표이미지
                    @JsonProperty("firstimage2")
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    private String thumbnail; // 썸네일 이미지
                    @JsonProperty("mapx")
                    private String x;
                    @JsonProperty("mapy")
                    private String y;

                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    private String dist; // 거리
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    @JsonInclude(JsonInclude.Include.NON_EMPTY)
                    private String tel;
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    @JsonInclude(JsonInclude.Include.NON_EMPTY)
                    private String title;

                    /* 참고
                    createdTime, modifiedTime - 역직렬화 오류 발생, 현재 기획상 해당 정보는 필요하지 않으므로 우선 제외하고 개발 진행

                    @JsonProperty("createdtime")
                    @JsonFormat(pattern = "yyyyMMddHHmmss")
                    private LocalDateTime createdTime;

                    @JsonProperty("modifiedtime")
                    @JsonSetter(nulls = Nulls.AS_EMPTY)
                    @JsonFormat(pattern = "yyyyMMddHHmmss")
                    private LocalDateTime modifiedTime;

                     */
                }
            }
        }
    }

    public List<Response.Body.Items.Item> getItems() {
        return response.getBody().getItems().getItem();
    }

}
