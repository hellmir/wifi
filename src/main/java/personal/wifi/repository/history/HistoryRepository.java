package personal.wifi.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.wifi.entity.history.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
