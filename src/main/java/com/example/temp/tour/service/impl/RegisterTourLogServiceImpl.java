package com.example.temp.tour.service.impl;

import com.example.temp.tour.domain.*;
import com.example.temp.tour.dto.RegisterTourLogRequest;
import com.example.temp.tour.dto.TourPlaceView;
import com.example.temp.tour.dto.TourScheduleDto;
import com.example.temp.tour.dto.TourScheduleMemoDto;
import com.example.temp.tour.service.RegisterTourLogService;
import com.example.temp.user.domain.User;
import com.example.temp.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterTourLogServiceImpl implements RegisterTourLogService {

    private final UserRepository userRepository;
    private final TourLogRepository tourLogRepository;
    private final TourPlaceRepository tourPlaceRepository;
    private final TourLogStadiumRepository tourLogStadiumRepository;

    @Override
    @Transactional
    public Long doService(Long userId, RegisterTourLogRequest request) {
        User user = userRepository.findByIdOrElseThrow(userId);
        TourLogStadium stadium = tourLogStadiumRepository.findByIdOrElseThrow(Long.valueOf(request.stadiumId()));

        TourLog tourLog = new TourLog(user, stadium, request.title(), request.image());

        // 투어 일정 추가
        if (request.schedules() != null && !request.schedules().isEmpty()) {
            addTourSchedulesToTourLog(user.getId(), tourLog, request.schedules());
        }

        return tourLogRepository.save(tourLog).getId();
    }

    private void addTourSchedulesToTourLog(Long userId, TourLog tourLog, List<TourScheduleDto> schedules) {
        for (TourScheduleDto dto : schedules) {
            // 투어 장소가 등록되어 있지 않으면 등록
            TourPlace tourPlace = registerTourPlaceIfNotFound(dto.place());

            TourSchedule tourSchedule = new TourSchedule(userId, tourLog, dto.day(), dto.sequence(), tourPlace);

            // 메모가 존재한다면 일정 장소에 메모 추가
            if (dto.memo() != null) {
                TourScheduleMemo memo = buildMemoEntity(tourSchedule, dto.memo());
                tourSchedule.setMemo(memo);
            }

            // 투어 로그에 일정 추가
            tourLog.addTourSchedule(tourSchedule);
        }
    }

    private TourPlace registerTourPlaceIfNotFound(TourPlaceView place) {
        int contentId = Integer.parseInt(place.contentId());
        int contentTypeId = Integer.parseInt(place.contentType());

        return tourPlaceRepository.findByContentIdAndContentTypeId(contentId, contentTypeId)
                .orElseGet(() -> registerTourPlace(place));
    }

    private TourScheduleMemo buildMemoEntity(TourSchedule tourSchedule, TourScheduleMemoDto memoDto) {
        TourScheduleMemo memoEntity = new TourScheduleMemo(tourSchedule, memoDto.content());

        // 메모에 이미지 추가
        memoDto.images().forEach(imageDto -> memoEntity.addImage(
                new TourScheduleMemoImage(memoEntity, imageDto.url(), imageDto.sequence())));

        return memoEntity;
    }

    private TourPlace registerTourPlace(TourPlaceView info) {
        TourPlace tourPlace = new TourPlace(
                Integer.parseInt(info.contentId()),
                Integer.parseInt(info.contentType()),
                info.name(),
                info.address(),
                Double.parseDouble(info.x()),
                Double.parseDouble(info.y()),
                info.thumbnail());

        return tourPlaceRepository.save(tourPlace);
    }
}
