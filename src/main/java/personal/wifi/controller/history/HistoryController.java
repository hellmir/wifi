package personal.wifi.controller.history;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.wifi.dto.history.HistoryResponseDto;
import personal.wifi.service.history.HistoryService;

import java.util.List;

@Controller
@RequestMapping("history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping()
    public String getAllHistories(Model model) {

        List<HistoryResponseDto> historyResponseDtoList = historyService.getAllHistories();

        model.addAttribute("histories", historyResponseDtoList);

        return "location/history";

    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteHistory(@RequestParam Long id) {

        historyService.deleteHistory(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
