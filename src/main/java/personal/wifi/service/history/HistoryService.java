package personal.wifi.service.history;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.wifi.dto.history.HistoryResponseDto;
import personal.wifi.entity.history.History;
import personal.wifi.repository.history.HistoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    public void saveHistory(double latitude, double longitude) {

        History history = History.createHistory(latitude, longitude);

        historyRepository.save(history);

    }

    public List<HistoryResponseDto> getAllHistories() {

        List<History> historyList = historyRepository.findAll();

        if (historyList.isEmpty()) {
            throw new EntityNotFoundException("저장된 히스토리 데이터가 없습니다.");
        }

        Collections.reverse(historyList);

        return historyList.stream()
                .map(history -> modelMapper.map(history, HistoryResponseDto.class))
                .collect(Collectors.toList());

    }

    public void deleteHistory(Long id) {

        History history = historyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("해당 ID가 존재하지 않습니다. (historyId: " + id + ")"));

        historyRepository.delete(history);

    }

}
