package com.example.temp.common.util;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;

public class IdUtil {

	// long 타입 식별자 생성
	public static long create() {
		return TsidCreator.getTsid().toLong();
	}

	// Base62 인코더
	public static String toString(Long id) {
		return Tsid.from(id).encode(62);
	}

	// Base62 디코더
	public static long toLong(String id) {
		return Tsid.decode(id, 62).toLong();
	}

}
