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
public class FindShoppingDetailsResponse {
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
                .restroom(item.restroom)
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

                    @JsonProperty("saleitem")
                    private String saleItem;                // 판매 품목

                    @JsonProperty("saleitemcost")
                    private String saleItemCost;             // 판매 품목별 가격

                    @JsonProperty("fairday")
                    private String fairDay;                 // 장서는날

                    @JsonProperty("opendateshopping")
                    private String openDateShopping;        // 개업날

                    @JsonProperty("shopguide")
                    private String shopGuide;                 // 매장 안내

                    @JsonProperty("culturecenter")
                    private String cultureCenter;               // 문화센터 바로가기

                    private String restroom;                   // 화장실설명

                    @JsonProperty("infocentershopping")
                    private String infoCenter;          // 문의 및 안내

                    @JsonProperty("scaleshopping")
                    private String scale;               // 규모

                    @JsonProperty("restdateshopping")
                    private String restDate;            // 쉬는날

                    @JsonProperty("parkingshopping")
                    private String parking;             // 주차시설

                    @JsonProperty("chkbabycarriageshopping")
                    private String chkBabyCarriage;         // 유모차 대여 정보

                    @JsonProperty("chkpetshopping")
                    private String chkPet;              // 애완동물 동반 가능 정보

                    @JsonProperty("chkcreditcardshopping")
                    private String chkCreditCard;         // 신용키드 가능 정보

                    @JsonProperty("opentime")
                    private String openTime;                        // 영업시간
                }
            }
        }
    }
}