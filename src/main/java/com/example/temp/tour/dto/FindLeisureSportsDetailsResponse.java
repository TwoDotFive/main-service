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
public class FindLeisureSportsDetailsResponse {
    private Response response;

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

                    @JsonProperty("openperiod")
                    private String openPeriod;          // 개장기간

                    private String reservation;         // 예약 안내

                    @JsonProperty("infocenterleports")
                    private String infoCenter;           // 문의 및 안내

                    @JsonProperty("scaleleports")
                    private String scale;                 //규모

                    @JsonProperty("accomcountleports")
                    private String accomCount;              // 수용 가능 인원

                    @JsonProperty("restdateleports")
                    private String restDate;                  // 쉬는 날

                    @JsonProperty("usetimeleports")
                    private String useTime;                  // 이용 시간

                    @JsonProperty("usefeeleports")
                    private String useFee;                   // 입장료

                    @JsonProperty("expagerangeleports")
                    private String expAgeRange;            // 체험 가능 연령

                    @JsonProperty("parkingleports")
                    private String parking;                 // 주차 시설

                    @JsonProperty("parkingfeeleports")
                    private String parkingFee;               // 주차 요금

                    @JsonProperty("chkbabycarriageleports")
                    private String chkBabyCarriage;         // 유모차 대여 정보

                    @JsonProperty("chkpetleports")
                    private String chkPet;                 // 애완동물 동반 가능 정보

                    @JsonProperty("chkcreditcardleports")
                    private String chkCreditCard;           // 신용카드 가능 정보
                }
            }
        }
    }
}