package personal.wifi.service.history;

import personal.wifi.dto.history.HistoryResponseDto;

import java.util.List;

public interface HistoryService {

    void saveHistory(double latitude, double longitude);

    List<HistoryResponseDto> getAllHistories();

    void deleteHistory(Long id);


}
