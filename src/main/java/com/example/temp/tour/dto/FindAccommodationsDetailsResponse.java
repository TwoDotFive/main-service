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
public class FindAccommodationsDetailsResponse {
    private Response response;

    public FindTourInformationDetailsResponse trans() {
        List<Response.Body.Items.Item> list = response.body.items.item;
        if (list == null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. ContentId, ContentTypeId를 다시 확인해 주세요");
        Response.Body.Items.Item item = list.get(0);
        return FindTourInformationDetailsResponse.builder()
                .contentId(item.contentId)
                .contentTypeId(item.contentTypeId)
                .reservationPageUrl(item.reservationUrl)
                .infoCenter(item.infoCenter)
                .parking(item.parking)
                .checkInTime(item.checkInTime)
                .checkOutTime(item.checkOutTime)
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

                    @JsonProperty("goodstay")
                    private String goodStay;            // 굿스테이 여부

                    @JsonProperty("benikia")
                    private String benikia;             // 베니키아 여부

                    @JsonProperty("hanok")
                    private String hanok;               // 한옥 여부

                    private String pickup;              // 픽업서비스


                    @JsonProperty("roomcount")
                    private String roomCount;           // 객실수

                    @JsonProperty("roomtype")
                    private String roomType;            // 객실 유형

                    @JsonProperty("refundregulation")
                    private String refundRegulation;        // 환불 규정

                    @JsonProperty("checkintime")
                    private String checkInTime;             // 체크인 타임

                    @JsonProperty("checkouttime")
                    private String checkOutTime;            // 체크 아웃 타임

                    @JsonProperty("chkcooking")
                    private String chkCooking;          //

                    private String seminar;                 // 세미나실 여부
                    private String sports;                  // 스포츠시설 여부
                    private String sauna;                   // 사우나실 여부
                    private String beauty;                  // 뷰티시설 정보
                    private String beverage;                // 식음료장 여부
                    private String karaoke;                 // 노래방 여부
                    private String barbecue;                // 바비큐장 여부
                    private String campfire;                // 캠프파이어 여부
                    private String bicycle;                 // 자전거 대여 여부
                    private String fitness;                 // 휘트니스센터 여부

                    @JsonProperty("publicpc")
                    private String publicPc;                // 공용 PC 여부

                    @JsonProperty("publicbath")
                    private String publicBath;               // 공용 샤워실 여부

                    @JsonProperty("subfacility")
                    private String subFacility;                 // 부대시설

                    @JsonProperty("foodplace")
                    private String foodPlace;                   //

                    @JsonProperty("reservationurl")
                    private String reservationUrl;              // 예약 안내 홈페이지

                    @JsonProperty("infocenterlodging")
                    private String infoCenter;           // 문의 및 안내

                    @JsonProperty("parkinglodging")
                    private String parking;              // 주차시설

                    @JsonProperty("reservationlodging")
                    private String reservation;          // 예약안내

                    @JsonProperty("scalelodging")
                    private String scale;                    // 규모

                    @JsonProperty("accomcountlodging")
                    private String accomCount;           // 수용 가능 인원
                }
            }
        }
    }
}