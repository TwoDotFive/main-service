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
public class FindRestaurantDetailsResponse {
    private Response response;

    public FindTourInformationDetailsResponse trans() {
        List<Response.Body.Items.Item> list = response.body.items.item;
        if (list == null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. ContentId, ContentTypeId를 다시 확인해 주세요");
        Response.Body.Items.Item item = list.get(0);
        return FindTourInformationDetailsResponse.builder()
                .contentId(item.contentId)
                .contentTypeId(item.contentTypeId)
                .openTime(item.openTime)
                .restDate(item.restDate)
                .infoCenter(item.infoCenter)
                .parking(item.parking)
                .firstMenu(item.firstMenu)
                .treatMenu(item.treatMenu)
                .build();
    }

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;


        @Getter
        @NoArgsConstructor
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @NoArgsConstructor
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
                        return new Items();
                    }

                    return p.getCodec().treeToValue(node, Items.class);
                }
            }

            @Getter
            @NoArgsConstructor
            public static class Items {
                private List<Item> item;

                @Getter
                @NoArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Item {

                    @JsonProperty("contentid")
                    private String contentId;

                    @JsonProperty("contenttypeid")
                    private String contentTypeId;

                    @JsonProperty("kidsfacility")
                    private String kidsFacility;              // 어린이 놀이방 여부

                    @JsonProperty("firstmenu")
                    private String firstMenu;               // 대표메뉴

                    @JsonProperty("treatmenu")
                    private String treatMenu;               // 취급메뉴

                    private String smoking;                 // 금연/흡연여부
                    private String packing;                 // 포장가능
                    private String seat;                    // 좌석수

                    @JsonProperty("infocenterfood")
                    private String infoCenter;              // 문의 및 안내

                    @JsonProperty("scalefood")
                    private String scale;           // 규모

                    @JsonProperty("parkingfood")
                    private String parking;         // 주차시설

                    @JsonProperty("opendatefood")
                    private String openDate;        // 개업일

                    @JsonProperty("opentimefood")
                    private String openTime;        // 영업시간

                    @JsonProperty("restdatefood")
                    private String restDate;         // 쉬는날

                    @JsonProperty("discountinfofood")
                    private String discountInfo;       // 할인 정보

                    @JsonProperty("chkcreditcardfood")
                    private String chkCreditCard;       // 신용카드 가능 정보

                    @JsonProperty("reservationfood")
                    private String reservation;         // 예약 안내

                    @JsonProperty("lcnsno")
                    private String lcnsNo;                   // 인허가번호
                }
            }
        }
    }
}