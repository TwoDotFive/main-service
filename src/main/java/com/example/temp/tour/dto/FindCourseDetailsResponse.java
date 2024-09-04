package com.example.temp.tour.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindCourseDetailsResponse {
    private Response response;

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;

        @Getter
        @NoArgsConstructor
        private static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @NoArgsConstructor
        private static class Body {
            private int numOfRows;
            private int pageNo;
            private int totalCount;


            @JsonDeserialize(using = Body.ItemsDeserializer.class)
            private Items items = new Body.Items();

            private static class ItemsDeserializer extends JsonDeserializer<Items> {
                @Override
                public Items deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                    JsonNode node = p.getCodec().readTree(p);

                    if (node.isTextual() && node.asText().isEmpty()) {
                        return new Items();
                    }

                    return p.getCodec().treeToValue(node, Items.class);
                }
            }

            @Getter
            @NoArgsConstructor
            private static class Items {
                private List<Item> item;

                @Getter
                @NoArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                private static class Item {
                    @JsonProperty("contentid")
                    private String contentId;
                    @JsonProperty("contenttypeid")
                    private String contentTypeId;

                    @JsonProperty("infocentertourcourse")
                    private String infoCenterTourCourse;    // 문의 및 안내
                    @JsonProperty("taketime")
                    private String takeTime;                 // 코스 총 소요 시간

                    private String distance;                 // 코스 총 거리
                    private String schedule;                  // 코스일정
                    private String theme;                      // 코스 테마
                }
            }
        }
    }
}