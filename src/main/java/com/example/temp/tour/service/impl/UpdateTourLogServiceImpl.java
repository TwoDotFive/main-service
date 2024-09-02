package com.example.temp.tour.service.impl;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.domain.*;
import com.example.temp.tour.dto.*;
import com.example.temp.tour.service.UpdateTourLogService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateTourLogServiceImpl implements UpdateTourLogService {

    private final EntityManager entityManager;
    private final TourLogRepository tourLogRepository;
    private final TourPlaceRepository tourPlaceRepository;

    @Override
    @Transactional
    public void doService(Long userId, UpdateTourLogRequest request) {
        TourLog tourLog = tourLogRepository.findByIdWithAllAssociationsForUpdate(request.tourLogId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Tour Log Id"));

        verifyPermission(userId, tourLog);

        List<TourSchedule> schedules = new ArrayList<>();
        for (TourScheduleView scheduleView : request.schedules()) {
            // 기존 일정의 수정
            if (StringUtils.hasLength(scheduleView.id())) {
                TourSchedule updatedTourSchedule = updateTourSchedule(scheduleView);
                schedules.add(updatedTourSchedule);
            }
            // 새로운 일정의 추가
            else {
                TourSchedule newTourSchedule = createTourSchedule(userId, tourLog, scheduleView);
                schedules.add(newTourSchedule);
            }
        }

        tourLog.updateTitle(request.title());
        tourLog.updateTourSchedules(schedules);
    }

    private void verifyPermission(Long userId, TourLog tourLog) {
        if (!userId.equals(tourLog.getUser().getId())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Permission Denied");
        }
    }

    // 기존 일정의 수정
    private TourSchedule updateTourSchedule(TourScheduleView scheduleView) {
        TourSchedule tourSchedule = entityManager.find(TourSchedule.class, Long.valueOf(scheduleView.id()));
        TourScheduleMemoView memoView = scheduleView.memo();

        // 메모가 없거나 삭제된 경우
        if (memoView == null) {
            tourSchedule.setMemo(null);
        }
        // 기존 메모의 수정
        else if (StringUtils.hasLength(memoView.id())) {
            updateTourScheduleMemo(memoView);
        }
        // 새로운 메모의 추가
        else {
            TourScheduleMemo memo = createTourScheduleMemo(tourSchedule, memoView);
            tourSchedule.setMemo(memo);
        }
        return tourSchedule;
    }

    // 기존 메모의 수정
    private void updateTourScheduleMemo(TourScheduleMemoView memoView) {
        TourScheduleMemo memo = entityManager.find(TourScheduleMemo.class, Long.valueOf(memoView.id()));
        memo.updateContent(memoView.content());

        Hibernate.initialize(memo.getImages());

        List<TourScheduleMemoImage> images = new ArrayList<>();
        for (TourScheduleMemoImageView imageView : memoView.images()) {
            // 기존 메모 이미지의 수정
            TourScheduleMemoImage memoImage;
            if (StringUtils.hasLength(imageView.id())) {
                memoImage = entityManager.find(TourScheduleMemoImage.class, Long.valueOf(imageView.id()));
                memoImage.update(imageView.url(), imageView.sequence());
            }
            // 새로운 메모 이미지의 추가
            else {
                memoImage = new TourScheduleMemoImage(memo, imageView.url(), imageView.sequence());
            }
            images.add(memoImage);
        }
        memo.updateImages(images);
    }

    // 새로운 일정의 생성
    private TourSchedule createTourSchedule(Long userId, TourLog tourLog, TourScheduleView scheduleView) {
        TourPlace tourPlace = findOrRegisterTourPlace(scheduleView.place());
        TourSchedule tourSchedule = new TourSchedule(userId, tourLog,
                scheduleView.day(), scheduleView.sequence(), tourPlace);
        TourScheduleMemo tourScheduleMemo = createTourScheduleMemo(tourSchedule, scheduleView.memo());
        tourSchedule.setMemo(tourScheduleMemo);
        return tourSchedule;
    }

    // 새로운 메모의 생성
    private TourScheduleMemo createTourScheduleMemo(TourSchedule tourSchedule, TourScheduleMemoView memoView) {
        TourScheduleMemo memo = new TourScheduleMemo(tourSchedule, memoView.content());

        // 메모에 이미지 추가
        memoView.images().forEach(imageDto -> {
            TourScheduleMemoImage memoImage = new TourScheduleMemoImage(memo, imageDto.url(), imageDto.sequence());
            memo.addImage(memoImage);
        });

        return memo;
    }

    private TourPlace findOrRegisterTourPlace(TourPlaceView placeView) {
        int contentId = Integer.parseInt(placeView.contentId());
        int contentTypeId = Integer.parseInt(placeView.contentType());
        return tourPlaceRepository.findByContentIdAndContentTypeId(contentId, contentTypeId)
                .orElseGet(() -> buildTourPlace(placeView));
    }

    private TourPlace buildTourPlace(TourPlaceView placeView) {
        return TourPlace.builder()
                .contentId(Integer.valueOf(placeView.contentId()))
                .contentTypeId(Integer.valueOf(placeView.contentType()))
                .name(placeView.name()).address(placeView.address())
                .x(Double.valueOf(placeView.x()))
                .y(Double.valueOf(placeView.y()))
                .thumbnail(placeView.thumbnail())
                .build();
    }
}
