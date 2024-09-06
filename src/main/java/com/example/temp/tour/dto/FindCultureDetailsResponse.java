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

@NoArgsConstructor
@Getter
public class FindCultureDetailsResponse {
    private Response response;

    public FindTourInformationDetailsResponse trans() {
        List<Response.Body.Items.Item> list = response.body.items.item;
        if (list == null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. ContentId, ContentTypeId를 다시 확인해 주세요");
        Response.Body.Items.Item item = list.get(0);
        return FindTourInformationDetailsResponse.builder()
                .contentId(item.contentId)
                .contentTypeId(item.contentTypeId)
                .infoCenter(item.infoCenter)
                .parking(item.parking)
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
                        return new Items(); // Return an empty Items object
                    }

                    // Otherwise, parse normally
                    return p.getCodec().treeToValue(node, Items.class);
                }
            }

            @Getter
            @NoArgsConstructor
            public static class Items {
                @JsonProperty("item")
                private List<Item> item;

                @Getter
                @NoArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Item {
                    @JsonProperty("contentid")
                    private String contentId;

                    @JsonProperty("contenttypeid")
                    private String contentTypeId;

                    private String scale;

                    @JsonProperty("usefee")
                    private String useFee;

                    @JsonProperty("discountinfo")
                    private String discountInfo;

                    @JsonProperty("spendtime")
                    private String spendTime;

                    @JsonProperty("parkingfee")
                    private String parkingFee;

                    @JsonProperty("infocenterculture")
                    private String infoCenter;

                    @JsonProperty("accomcountculture")
                    private String accomCount;

                    @JsonProperty("usetimeculture")
                    private String useTime;

                    @JsonProperty("restdateculture")
                    private String restDate;

                    @JsonProperty("parkingculture")
                    private String parking;

                    @JsonProperty("chkbabycarriageculture")
                    private String chkBabyCarriage;

                    @JsonProperty("chkpetculture")
                    private String chkPet;

                    @JsonProperty("chkcreditcardculture")
                    private String chkCreditCard;
                }
            }
        }
    }
}