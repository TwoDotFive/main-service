package com.example.temp.tour.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@DisplayName("국문 관광정보 조회 API")
@ExtendWith(SoftAssertionsExtension.class)
class TourInformationBasedLocationResponseTest {

    private FindTourInformationByLocationHttpResponse response;

    @InjectSoftAssertions
    SoftAssertions softAssertions;

    @BeforeEach
    public void setUp() throws Exception {
        String json = """
                {
                  "response": {
                    "header": {
                      "resultCode": "0000",
                      "resultMsg": "OK"
                    },
                    "body": {
                      "items": {
                        "item": [
                          {
                            "addr1": "서울특별시 중구 을지로9길 6",
                            "addr2": "",
                            "areacode": "1",
                            "cat1": "A05",
                            "cat2": "A0502",
                            "cat3": "A05020400",
                            "contentid": "2685273",
                            "contenttypeid": "39",
                            "createdtime": "20201124213059",
                            "dist": "651.403118549015",
                            "firstimage": "http://tong.visitkorea.or.kr/cms/resource/42/2685142_image2_1.jpg",
                            "mapx": "126.9885339952",
                            "mapy": "37.5665544247",
                            "modifiedtime": "20210315185719",
                            "sigungucode": "24",
                            "tel": "02-2267-3939",
                            "title": "가야성"
                          }
                        ]
                      },
                      "numOfRows": 10,
                      "pageNo": 1,
                      "totalCount": 468
                    }
                  }
                }""";

        ObjectMapper objectMapper = new ObjectMapper();
        response = objectMapper.readValue(json, FindTourInformationByLocationHttpResponse.class);
    }

    @Test
    @DisplayName("HTTP 응답 객체 매핑 테스트")
    void test() {
        List<FindTourInformationByLocationHttpResponse.Response.Body.Items.Item> foundItems = response.getItems();

        // 조회 결과
        softAssertions.assertThat(foundItems.size())
                .as("조회된 관광지의 개수")
                .isEqualTo(1);

        FindTourInformationByLocationHttpResponse.Response.Body.Items.Item foundItem = foundItems.get(0);

        softAssertions.assertThat(foundItem.getAreacode())
                .as("지역 코드")
                .isEqualTo("1");

        softAssertions.assertThat(foundItem.getSigungucode())
                .as("시군구 코드")
                .isEqualTo("24");

        softAssertions.assertThat(foundItem.getTitle())
                .as("제목")
                .isEqualTo("가야성");

        softAssertions.assertThat(foundItem.getAddr1())
                .as("주소")
                .isEqualTo("서울특별시 중구 을지로9길 6");

        softAssertions.assertThat(foundItem.getTel())
                .as("연락처")
                .isEqualTo("02-2267-3939");

        softAssertions.assertThat(foundItem.getDist())
                .as("거리")
                .isEqualTo("651.403118549015");

        softAssertions.assertThat(foundItem.getRepresentativeImageUrl())
                .as("대표 이미지 주소")
                .isEqualTo("http://tong.visitkorea.or.kr/cms/resource/42/2685142_image2_1.jpg");
    }
}