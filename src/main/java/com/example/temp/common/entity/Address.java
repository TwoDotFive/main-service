package com.example.temp.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 참고 - 행정안전부 주소기반산업지원서비스
 * https://business.juso.go.kr/addrlink/elctrnMapProvd/geoDBDwldPubList.do?cPath=99JD
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {
    private String zipNo;
    private String dongCd;      // 법정동 코드(6) = 읍면동일련번호(2) + 읍면동구분(1, 0:읍면, 1:동, 2:미부여) + 법정동기준읍면동코드(3)
    private String sido;        // 시도명
    private String sigungu;     // 시군구명
    private String dong;        // 법정동명
    private String eupmyun;     // 법정읍면명
    private String doroCd;      // 도로명 코드 = 시군구번호(5) + 도로명번호(7)
    private String doro;        // 도로명
    private String buildNo;     // 건물번호
    private String buildName;   // 건물이름
    private String detailAddr;  // 상세 주소

    private String latitude;    // 위도
    private String longitude;   // 경도
}
