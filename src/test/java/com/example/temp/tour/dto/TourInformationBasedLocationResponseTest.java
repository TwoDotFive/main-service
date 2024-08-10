package com.example.temp.tour.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourInformationBasedLocationResponseTest {
    private TourInformationBasedLocationResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        String json = "{\n" +
                "  \"response\": {\n" +
                "    \"header\": {\n" +
                "      \"resultCode\": \"0000\",\n" +
                "      \"resultMsg\": \"OK\"\n" +
                "    },\n" +
                "    \"body\": {\n" +
                "      \"items\": {\n" +
                "        \"item\": [\n" +
                "          {\n" +
                "            \"addr1\": \"서울특별시 중구 을지로9길 6\",\n" +
                "            \"addr2\": \"\",\n" +
                "            \"areacode\": \"1\",\n" +
                "            \"cat1\": \"A05\",\n" +
                "            \"cat2\": \"A0502\",\n" +
                "            \"cat3\": \"A05020400\",\n" +
                "            \"contentid\": \"2685273\",\n" +
                "            \"contenttypeid\": \"39\",\n" +
                "            \"createdtime\": \"20201124213059\",\n" +
                "            \"dist\": \"651.403118549015\",\n" +
                "            \"firstimage\": \"http://tong.visitkorea.or.kr/cms/resource/42/2685142_image2_1.jpg\",\n" +
                "            \"mapx\": \"126.9885339952\",\n" +
                "            \"mapy\": \"37.5665544247\",\n" +
                "            \"modifiedtime\": \"20210315185719\",\n" +
                "            \"sigungucode\": \"24\",\n" +
                "            \"tel\": \"02-2267-3939\",\n" +
                "            \"title\": \"가야성\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"numOfRows\": 10,\n" +
                "      \"pageNo\": 1,\n" +
                "      \"totalCount\": 468\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        response = objectMapper.readValue(json, TourInformationBasedLocationResponse.class);
    }

    @Test
    void testGetAreacode() {
        String areacode = response.getAreacode(0);
        assertNotNull(areacode);
        assertEquals("1", areacode);
    }

    @Test
    void testGetSigungucode() {
        String sigungucode = response.getSigungucode(0);
        assertNotNull(sigungucode);
        assertEquals("24", sigungucode);
    }

    @Test
    void testGetTitle() {
        String title = response.getTitle(0);
        assertNotNull(title);
        assertEquals("가야성", title);
    }

    @Test
    void testGetAddr1() {
        String addr1 = response.getAddr1(0);
        assertNotNull(addr1);
        assertEquals("서울특별시 중구 을지로9길 6", addr1);
    }

    @Test
    void testGetTel() {
        String tel = response.getTel(0);
        assertNotNull(tel);
        assertEquals("02-2267-3939", tel);
    }

    @Test
    void testGetDist() {
        String dist = response.getDist(0);
        assertNotNull(dist);
        assertEquals("651.403118549015", dist);
    }

    @Test
    void testGetRepresentativeImageUrl() {
        String representativeImageUrl = response.getRepresentativeImageUrl(0);
        assertNotNull(representativeImageUrl);
        assertEquals("http://tong.visitkorea.or.kr/cms/resource/42/2685142_image2_1.jpg", representativeImageUrl);
    }
}