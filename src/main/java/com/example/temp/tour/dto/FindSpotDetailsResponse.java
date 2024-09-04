package com.example.temp.tour.dto;

import com.example.temp.common.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@Getter
@NoArgsConstructor
public class FindSpotDetailsResponse {
    private Response response;

    public FindTourInformationDetailsResponse trans() {
        List<Response.Body.Items.Item> list = response.body.items.item;
        if (list == null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. ContentId, ContentTypeId를 다시 확인해 주세요");
        Response.Body.Items.Item item = list.get(0);
        return FindTourInformationDetailsResponse.builder()
                .contentId(item.contentId)
                .contentTypeId(item.contentTypeId)
                .parking(item.parking)
                .infoCenter(item.expGuide)
                .openTime(item.useTime)
                .restDate(item.restDate)
                .build();
    }

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
            @JsonDeserialize(using = ItemsDeserializer.class)
            private Items items = new Items();

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

                    private String heritage1;       // 세계문화유산 유무
                    private String heritage2;       // 세계자연유산 유무
                    private String heritage3;       // 세계기록유산 유무

                    @JsonProperty("infocenter")
                    private String infoCenter;      //문의 및 안내

                    @JsonProperty("opendate")
                    private String openDate;        // 개장일

                    @JsonProperty("restdate")
                    private String restDate;        // 쉬는날

                    @JsonProperty("expguide")
                    private String expGuide;        //체험 안내

                    @JsonProperty("expagerange")
                    private String expAgeRange;     //체험 가능 연령

                    @JsonProperty("accomcount")
                    private String accomCount;      //수용인원

                    @JsonProperty("useseason")
                    private String useSeason;       // 이용시기

                    @JsonProperty("usetime")
                    private String useTime;         //이용시간

                    private String parking;          // 주차시설

                    @JsonProperty("chkbabycarriage")
                    private String chkBabyCarriage;         // 유모차 대여 정보

                    @JsonProperty("chkpet")
                    private String chkPet;          // 애완동물 동반 가능 정보

                    @JsonProperty("chkcreditcard")
                    private String chkCreditCard;     //신용카드 가능 정보
                }
            }
        }
    }

}

